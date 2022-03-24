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
import com.ssafy.unique.api.request.NftUpdateReq;
import com.ssafy.unique.api.response.NftRes;
import com.ssafy.unique.api.response.ResultRes;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.MemberRepository;
import com.ssafy.unique.db.repository.NftRepository;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

@Service
public class IPFSServiceImpl implements IPFSService {
	
	private final IPFSConfig ipfsConfig;
	private final NftRepository nftRepository;
	private final MemberRepository memberRepository;
	
	public IPFSServiceImpl(IPFSConfig _ipfsConfig, NftRepository _nftRepository, MemberRepository _memberRepository) {
		this.ipfsConfig = _ipfsConfig;
		this.nftRepository = _nftRepository;
		this.memberRepository = _memberRepository;
	}
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@SuppressWarnings("finally")
	@Override
	public NftRes saveFile(NftReq nftReq, MultipartHttpServletRequest request) {
		NftRes nftRes = new NftRes();
		
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
				nftReq.setNftAuthorSeq(Long.parseLong(authentication.getName()));
				// Content-type
				nftReq.setNftType(file.getContentType());
				// 메타데이터를 생성
				ObjectMapper mapper = new ObjectMapper();
				String jsonStr = mapper.writeValueAsString(nftReq);
				System.out.println(jsonStr);
				
				stream = new ByteArrayInputStream(jsonStr.getBytes());
				inputStreamWrapper = new NamedStreamable.InputStreamWrapper(stream);
				merkleNode = ipfs.add(inputStreamWrapper).get(0);
				
				// Owner Address를 구한다
				String ownerAddress = memberRepository.findMemberAddressByMemberSeq(nftReq.getNftAuthorSeq());
				
				// DB에 반영
				nftRepository.save(Nft.builder()
						.nftAuthorSeq(nftReq.getNftAuthorSeq())
						.nftAuthorName(nftReq.getNftAuthorName())
						.nftOwnerSeq(nftReq.getNftAuthorSeq())
						.nftOwnerAddress(ownerAddress)
						.nftWorkUri(nftReq.getNftWorkUri())
						.nftMetadataUri(merkleNode.hash.toBase58())
						.nftName(nftReq.getNftName())
						.nftType(nftReq.getNftType())
						.nftDescription(nftReq.getNftDescription())
						.build()
				);
				
				nftRes.setResult(SUCCESS);
				nftRes.setNftSeq(nftReq.getNftAuthorSeq());
				nftRes.setNftWorkUri(merkleNode.hash.toBase58());
			} else {
				System.out.println("null file");
				nftRes.setResult(FAIL);
			}
			
		} catch( Exception e ) {
			e.printStackTrace();
			throw new RuntimeException("Error whilst communicating whit the IPFS node", e);
		} finally {
			return nftRes;
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

	@SuppressWarnings("finally")
	@Override
	public ResultRes updateNFT(NftUpdateReq nftUpdateReq) {
		// DB에 저장되어 있는 NFT의 값을 변경한다
		
		ResultRes resultRes = new ResultRes();
		
		try {
			
			int result = nftRepository.updateNftByNftTokenIdAndNftOwnerAddress(
					nftUpdateReq.getTokenId(), 
					nftUpdateReq.getOwnerAddress(),
					nftUpdateReq.getMetadataUri()
			);
			
			if (result == SUCCESS) {
				resultRes.setResult(SUCCESS);
			} else {
				resultRes.setResult(FAIL);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resultRes.setResult(FAIL);
		} finally {
			return resultRes;
		}
	}
	
}
