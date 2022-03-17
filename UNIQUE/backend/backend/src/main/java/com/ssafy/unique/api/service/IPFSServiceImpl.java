package com.ssafy.unique.api.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.unique.api.config.IPFSConfig;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

@Service
public class IPFSServiceImpl implements IPFSService {

	@Autowired
	private IPFSConfig ipfsConfig;
	
	@Override
	public String saveFile(MultipartFile file) {
		
		try {
			InputStream stream = new ByteArrayInputStream(file.getBytes());
			NamedStreamable.InputStreamWrapper inputStreamWrapper = new NamedStreamable.InputStreamWrapper(stream);
			IPFS ipfs = ipfsConfig.ipfs;
			
			MerkleNode merkleNode = ipfs.add(inputStreamWrapper).get(0);
			
			return merkleNode.hash.toBase58();
			
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
