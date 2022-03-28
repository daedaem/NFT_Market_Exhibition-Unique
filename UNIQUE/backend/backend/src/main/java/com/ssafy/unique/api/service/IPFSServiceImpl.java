package com.ssafy.unique.api.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.unique.api.config.IPFSConfig;
import com.ssafy.unique.api.request.NftReq;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.NftRepository;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

@Service
public class IPFSServiceImpl implements IPFSService {
	
	private final IPFSConfig ipfsConfig;
	private final NftRepository nftRepository;
	
	public IPFSServiceImpl(IPFSConfig ipfsConfig, NftRepository nftRepository) {
		this.ipfsConfig = ipfsConfig;
		this.nftRepository = nftRepository;
	}
	
	@Override
	public String saveFile(NftReq nftReq, MultipartHttpServletRequest request) {
		
		try {
			MultipartFile file = request.getFile("file");
			
			if (file != null) {
				InputStream stream = new ByteArrayInputStream(file.getBytes());
				NamedStreamable.InputStreamWrapper inputStreamWrapper = new NamedStreamable.InputStreamWrapper(stream);
				IPFS ipfs = ipfsConfig.ipfs;
				
				MerkleNode merkleNode = ipfs.add(inputStreamWrapper).get(0);
				
				// MerkleNode에서 nftWorkUri를 구한다
				nftReq.setNftWorkUri(merkleNode.hash.toBase58());
				
				// nftWorkUri가 이미 존재하는지 DB에서 확인하고, 없다면 밑으로 진행
				
				
				
				// Security Context에서 nftCreatorSeq를 구한다
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				nftReq.setNftCreatorSeq(Long.parseLong(authentication.getName()));
				// Content-type
				nftReq.setNftType(file.getContentType());
				// 메타데이터를 생성
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = mapper.writeValueAsString(nftReq);
				System.out.println(jsonStr);
				
				stream = new ByteArrayInputStream(jsonStr.getBytes());
				inputStreamWrapper = new NamedStreamable.InputStreamWrapper(stream);
				merkleNode = ipfs.add(inputStreamWrapper).get(0);
				
				
				// DB에 반영
				nftRepository.save(Nft.builder()
						.nftOwnerSeq(nftReq.getNftCreatorSeq())
						.nftAuthorSeq(nftReq.getNftCreatorSeq())
						.nftName(nftReq.getNftName())
						.nftType(nftReq.getNftType())
						.nftWorkUri(nftReq.getNftWorkUri())
						.nftMetadataUri(merkleNode.hash.toBase58())
						.build()
				);
				
				return merkleNode.hash.toBase58();
			} else {
				System.out.println("null file");
				return null;
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RuntimeException("Error whilst communicating whit the IPFS node", e);
		}
	}

	@Override
	public byte[] LoadFile(String hash) {
		
		try {
			IPFS ipfs = ipfsConfig.ipfs;

			Multihash filePointer = Multihash.fromBase58(hash);
			return ipfs.cat(filePointer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error whilst communicating whit the IPFS node", e);
		}
		
	}
	
}
