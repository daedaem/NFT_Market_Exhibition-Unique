package com.ssafy.unique.ipfsdemo.controller;

import com.ssafy.unique.ipfsdemo.service.IPFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class IPFSController {

    @Autowired
    private IPFSService ipfsService;

    @PostMapping(value = "upload")
    public String saveFile(@RequestParam("file") MultipartFile file) {
        return ipfsService.saveFile(file);
    }

    @GetMapping(value = "file/{hash}")
    public ResponseEntity<byte[]> loadFile(@PathVariable("hash") String hash) {

        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add("content-type", MediaType.IMAGE_PNG_VALUE);
        byte[] bytes = ipfsService.lodaFile(hash);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(bytes);
    }
}
