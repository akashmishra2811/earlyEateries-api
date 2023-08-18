package com.example.earlyEateries.controllers;

import java.io.IOException;
import java.io.InputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.earlyEateries.dto.ApiResponse;
import com.example.earlyEateries.dto.EateryPaginationResponse;
import com.example.earlyEateries.dto.EateryRequestResponse;
import com.example.earlyEateries.dto.FileResponse;
import com.example.earlyEateries.service.EateryService;
import com.example.earlyEateries.service.FileService;
import com.example.earlyEateries.config.ApiConstants;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.Eatery.END_POINT)
public class EateryController {
	
    @Autowired
	private EateryService eateryService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("")
	public ResponseEntity<EateryRequestResponse> create(@Valid @RequestBody EateryRequestResponse eateryRequestResponse){
		
		EateryRequestResponse createdEateryRequestResponse = this.eateryService.create(eateryRequestResponse);
		
		
		return new ResponseEntity<>(createdEateryRequestResponse,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<EateryRequestResponse> update(@Valid @RequestBody EateryRequestResponse eateryRequestResponse, @PathVariable("id") Long id){
		
		EateryRequestResponse updatedEateryRequestResponse = this.eateryService.update(eateryRequestResponse, id);
		
		return ResponseEntity.ok(updatedEateryRequestResponse) ;
		
		
	}
	
	@GetMapping("")
	public ResponseEntity<EateryPaginationResponse> get(@RequestParam(value ="pageNumber", defaultValue ="0") Integer pageNumber,
			@RequestParam(value ="pageSize", defaultValue ="25") Integer pageSize,
			@RequestParam(value ="sortBy", defaultValue ="eateryId") String sortBy){
		
		EateryPaginationResponse eateryRequestResponseList = this.eateryService.getAll(pageNumber,pageSize,sortBy);
		
		
		return new ResponseEntity<>(eateryRequestResponseList,HttpStatus.OK);
		
		
	}
	
	@PostMapping(ApiConstants.FileService.UPLOAD)
	public ResponseEntity<FileResponse> upload(@RequestParam("image") MultipartFile image, @PathVariable Long eateryId) throws IOException{
		
		try {
		     	final String fileName = this.fileService.upload(path, image);
		     	Long idLong = (long) 2;
		     	 EateryRequestResponse eateryRequestResponse =   this.eateryService.getById(idLong);
		     	 eateryRequestResponse.setImageLink(fileName);
		     	  this.eateryService.update(eateryRequestResponse, eateryId);
		     	
		     	return new ResponseEntity<>(new FileResponse(fileName, "File uploaded successfully"),HttpStatus.OK);
		    }
		catch (Exception e) {
	   		e.printStackTrace();
	   	    	return new ResponseEntity<>(new FileResponse(null, "File uploaded successfully"),HttpStatus.OK);
		     }
	
		
	}
	@GetMapping(value =ApiConstants.FileService.DOWNLOAD,produces = MediaType.IMAGE_JPEG_VALUE)
	 public void download(@PathVariable Long eateryId,HttpServletResponse response) throws IOException {
		EateryRequestResponse eateryRequestResponse = this.eateryService.getById(eateryId);
		String fileName = eateryRequestResponse.getImageLink();
		 
		InputStream resource = this.fileService.download(path,fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    StreamUtils.copy(resource,response.getOutputStream());
		
	}
	
	
	  @DeleteMapping("/{id}")
	     public ResponseEntity<ApiResponse> delete( @PathVariable("id") Long id){
	    	  this.eateryService.delete(id);
	    	  
	    	  return new ResponseEntity<ApiResponse>( new ApiResponse("The eatery has been deleted Successfully", true),HttpStatus.OK);
	     }

	

}
