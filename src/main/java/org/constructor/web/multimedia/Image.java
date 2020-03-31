package org.constructor.web.multimedia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.constructor.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Image {
	
	private static final String path =  System.getProperty("user.home") + "/resources" + File.separator;
	private final Logger log = LoggerFactory.getLogger(Video.class);
	
	@RequestMapping(value = "/loadImage", method = RequestMethod.GET)
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<byte[]> loadImage(@RequestParam("file") String nameImage) {
		
		HttpHeaders headers = new HttpHeaders();
		log.debug("*************Nimbus Image Request*************");
		log.debug("******** Path:  {}****** ", path + nameImage);
		byte[] fileArray = new byte[1];
		File file = new File(path + nameImage);
		
		if(!file.exists()) {
			fileArray[0] = 0;
			log.debug("******** Path not found****** ");
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(fileArray,HttpStatus.BAD_REQUEST);
		} 
		
		log.debug("********Load Image ******", file);
		fileArray = new byte[(int) file.length()];
		
		try { 
			FileInputStream read = new FileInputStream(file);
			log.debug("******** Reading File *****", nameImage);
			read.read(fileArray);
			read.close();
			log.debug("******** Sending File *****", nameImage);
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(fileArray,headers,HttpStatus.OK);
			//return fileArray;
		
		}catch(IOException ex){
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<byte[]>(fileArray,HttpStatus.BAD_REQUEST);
		}
		
	}

}
