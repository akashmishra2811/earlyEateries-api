package com.example.earlyEateries.dto;

public class FileResponse {

	private String fileName;
	 String message;
	
	public FileResponse(String fileName, String message) {
		
		this.message =message;
		this.fileName =fileName;
		
	}
	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
