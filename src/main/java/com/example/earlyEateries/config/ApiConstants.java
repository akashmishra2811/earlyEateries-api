package com.example.earlyEateries.config;

public class ApiConstants {

	public static final String BASE_URL = "api/v1/";
	public static final String DEFAULT_PAGE_NUMBER = "0";
	public static final String DEFAULT_PAGE_SIZE = "25";
	
	public class Eatery {
		
		public static final String END_POINT = BASE_URL + "eatery";
	}
	
	public class User{
		public static final String END_POINT = BASE_URL + "user";
	}
	
	public class Comment{
		public static final String END_POINT = BASE_URL + "comment";
	}
	public class FileService{
		
		public static final String UPLOAD =	"/upload/image/{eateryId}";
		public static final String DOWNLOAD ="/download/image/{eateryId}";
	}
	
}
