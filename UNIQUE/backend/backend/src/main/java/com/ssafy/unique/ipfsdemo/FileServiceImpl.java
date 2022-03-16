package com.ssafy.unique.ipfsdemo;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImpl {

    String saveFile(MultipartFile file);

    byte[] lodaFile(String hash);
}
