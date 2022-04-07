package com.ssafy.unique.api.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
import com.ssafy.unique.db.entity.FileList;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.FileListRepository;
import com.ssafy.unique.db.repository.MemberRepository;
import com.ssafy.unique.db.repository.NftRepository;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
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
import com.ssafy.unique.db.entity.FileList;
import com.ssafy.unique.db.entity.Nft;
import com.ssafy.unique.db.repository.FileListRepository;
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
	private final FileListRepository fileRepository;

	public IPFSServiceImpl(IPFSConfig _ipfsConfig, NftRepository _nftRepository, MemberRepository _memberRepository,
						   FileListRepository _fileRepository) {
		this.ipfsConfig = _ipfsConfig;
		this.nftRepository = _nftRepository;
		this.memberRepository = _memberRepository;
		this.fileRepository = _fileRepository;
	}

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	String uploadFolder = "upload";
	String uploadPath = "C:" + File.separator + "SSAFY" + File.separator + "NFT";
	//	String uploadPath = "/usr" + File.separator + "share" + File.separator + "nginx" + File.separator + "html";


	@Override
	public NftRes saveFile(NftReq nftReq, MultipartHttpServletRequest request) {
		NftRes nftRes = new NftRes();

		try {
			MultipartFile file = request.getFile("file");

			if (file != null) {
				// 추가: 서버에 이미지를 저장

				// 파일 저장 위치 설정
				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if (!uploadDir.exists())
					uploadDir.mkdirs();

				// 실제 파일이름 저장
				String fileName = file.getOriginalFilename();

				// Random File Id + File Extension 으로 저장될 파일이름 설정
				UUID uuid = UUID.randomUUID();
				String extension = FilenameUtils.getExtension(fileName);
				String savingFileName = uuid + "." + extension;

				// 파일 저장
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

				// 파일 url
				String fileUrl = uploadFolder + File.separator + savingFileName;





				// IPFS Upload
				// file.getBytes() 코드는 파일을 저장한 다음 실행하면, 위치를 찾지 못해서 에러가 발생한다
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
				System.out.println(ownerAddress);
				// MetadataUri를 구한다
				String nftMetadataUri = merkleNode.hash.toBase58();

				// DB에 반영
				Nft nft = nftRepository.save(Nft.builder()
						.nftAuthorSeq(nftReq.getNftAuthorSeq())
						.nftAuthorName(nftReq.getNftAuthorName())
						.nftOwnerSeq(nftReq.getNftAuthorSeq())
						.nftOwnerAddress(ownerAddress)
						.nftWorkUri(nftReq.getNftWorkUri())
						.nftMetadataUri(nftMetadataUri)
						.nftName(nftReq.getNftName())
						.nftType(nftReq.getNftType())
						.nftDescription(nftReq.getNftDescription())
						.fileUrl(fileUrl)
						.build()
				);



				// 파일 저장 => 해당 코드가 앞에 존재하면 getBytes()에서 에러 발생
				file.transferTo(destFile);
				// FILE_LIST DB에 파일 기록 => NFT_SEQ의 값이 정해지고 나서 진행해야함
				fileRepository.save(FileList.builder()
						.nftSeq(nft.getNftSeq())
						.fileName(fileName)
						.fileSize(file.getSize())
						.fileContentType(file.getContentType())
						.fileUrl(fileUrl)
						.build());

				nftRes.setResult(SUCCESS);
				nftRes.setNftSeq(nft.getNftSeq());
				nftRes.setNftMetadataUri(nftMetadataUri);
			} else {
				System.out.println("null file");
				nftRes.setResult(FAIL);
			}

		} catch (Exception e) {
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
			// Security Context에서 nftCreatorSeq를 구한다
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Long nftSeq = Long.parseLong(authentication.getName());

			// Owner Address를 구한다
			String ownerAddress = memberRepository.findMemberAddressByMemberSeq(nftSeq);

			int result = nftRepository.updateNftByNftTokenIdAndNftOwnerAddress(
					nftUpdateReq.getTokenId(),
					nftUpdateReq.getContractAddress(),
					ownerAddress,
					nftUpdateReq.getMetadataUri());

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
