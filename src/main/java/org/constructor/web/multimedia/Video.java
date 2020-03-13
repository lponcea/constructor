package org.constructor.web.multimedia;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.constructor.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class Video {
	
	private static final String path =  System.getProperty("user.home") + "/resources" + File.separator;
	private final Logger log = LoggerFactory.getLogger(Video.class);
	
	@RequestMapping(value = "/loadVideo", method = RequestMethod.GET, produces = "video/mp4")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public byte[] loadVideo(@RequestParam("file") String nameVideo) {
		log.debug("********Nimbus Video Request******", nameVideo);
		log.debug("******** Path:  {}****** ", path + nameVideo);
		byte[] fileArray = new byte[1];
		File file = new File(path + nameVideo);
		if(!file.exists()) {
			log.debug("******** Path:  {}****** ", path + file.exists());
			fileArray[0] = 0;
			log.debug("******** Path:  {}****** ", fileArray.length);
			return fileArray;
		} 
		log.debug("********Load video ******", file);
		fileArray = new byte[(int) file.length()];
		//byte[] fileArray = new byte[(int) file.length()];
		
		try { 
			FileInputStream read = new FileInputStream(file);
			log.debug("******** Reading File *****", nameVideo);
			read.read(fileArray);
			read.close();
			log.debug("******** Sending File *****", nameVideo);
			return fileArray;
		
		}catch(IOException ex){
			//log.debug("Exception al leer el video: {} ", ex.getMessage());
			return fileArray;
		}
	}
	
}

