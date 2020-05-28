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
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Image {
	
	/**
	 * PATH
	 */
	private static final String PATH =  System.getProperty("user.home") + "/resources" + File.separator;
	
	/**
	 * Logger
	 */
	private final Logger log = LoggerFactory.getLogger(Image.class);
	
	
	/**
	 * method Get  loadImage
	 * @param nameImage
	 * @return
	 */
	@RequestMapping(value = "/loadImage", method = RequestMethod.GET)
	@Secured({ AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER })
	public ResponseEntity<byte[]> loadImage(@RequestParam("file") String nameImage) {
		
		HttpHeaders headers = new HttpHeaders();
		log.debug("*************Nimbus Image Request*************");
		log.debug("******** Path:  {}****** ", PATH + nameImage);
		byte[] fileArray = new byte[1];
		File file = new File(PATH + nameImage);
		
		if(!file.exists()) {
			fileArray[0] = 0;
			log.debug("******** Path not found****** ");
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<>(fileArray,HttpStatus.BAD_REQUEST);
		} 
		
		log.debug("********Load Image ******: {}", file);
		fileArray = new byte[(int) file.length()];
		
		try { 
			FileInputStream read = new FileInputStream(file);
			log.debug("******** Reading File *****", nameImage);
			read.read(fileArray);
			read.close();
			log.debug("******** Sending File *****", nameImage);
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<>(fileArray,headers,HttpStatus.OK);
		
		}catch(IOException ex){
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<>(fileArray,HttpStatus.BAD_REQUEST);
		}
		
	}

}
