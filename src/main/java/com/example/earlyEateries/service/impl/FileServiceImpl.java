package com.example.earlyEateries.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.earlyEateries.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String upload(String path, MultipartFile file) throws IOException {
		 
		final String name = file.getOriginalFilename();
		final String randomId = UUID.randomUUID().toString();
		final String fileName = randomId +  name.substring(name.lastIndexOf("."));		
		final String filePath = path + File.separator + fileName;
		
		File f = new File(path);
		 if(!f.exists()) {
			 f.mkdir();
		 }
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return fileName;
	}

	@Override
	public InputStream download(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path +File.separator +fileName;
		InputStream response = new FileInputStream(fullPath); 
		return response;
	}

}
