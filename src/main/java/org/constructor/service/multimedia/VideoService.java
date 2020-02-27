package org.constructor.service.multimedia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.constructor.web.multimedia.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
	
	private static final String path =  System.getProperty("user.home") + "/resources" + File.separator + "nimbus" + File.separator + "video" + File.separator + "video1.mp4";
	private final Logger log = LoggerFactory.getLogger(Video.class);
	
	public byte[] getVideo (String nameVideo) {
		log.debug("Holaaaaaaa ", nameVideo);
		File file = new File(path);
		byte[] fileArray = new byte[(int) file.length()];
		
		try { 
		FileInputStream read = new FileInputStream(file);
		read.read(fileArray);
		read.close();
		return fileArray;
		}catch(IOException ex)
		{
			log.debug("Exception al leer el video: {} ", ex.getMessage());
			return fileArray;
		}
		
	}
	

}
