package com.example.earlyEateries.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String upload( final String path ,MultipartFile file) throws IOException;
	
	InputStream download(String path ,String file) throws FileNotFoundException;

}
