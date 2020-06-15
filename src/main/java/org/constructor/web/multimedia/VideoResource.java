package org.constructor.web.multimedia;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.constructor.security.AuthoritiesConstants;
import org.constructor.utils.RestConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class VideoResource.
 */
@RestController
@RequestMapping(RestConstants.PATH_API)
public class VideoResource {
	
	/** path. */
	private static final String PATH = System.getProperty("user.home") + "/resources" + File.separator;
	
	/** Logger. */
	private final Logger log = LoggerFactory.getLogger(VideoResource.class);
	
	
	/**
	 * method Get 
	 *  
	 * video/mp4.
	 *
	 * @param nameVideo the name video
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(path = RestConstants.PATH_LOAD_VIDEO, method = RequestMethod.GET, produces = "video/mp4")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<byte[]> loadVideo(@RequestParam("file") String nameVideo) throws IOException {
		boolean  banner = false;
		
		return videoBanner(banner, nameVideo);
		
	}
	
	
	/**
	 * Pre view video.
	 *
	 * @param nameVideo the name video
	 * @return the response entity
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping(path = RestConstants.PATH_PREVIEW_VIDEO, produces = "video/mp4")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<byte[]> preViewVideo(@RequestParam("file") String nameVideo) throws IOException {
		boolean  banner = true;
		return videoBanner(banner,nameVideo);
		
	}
	

	/**
	 * Video banner.
	 *
	 * @param banner the banner
	 * @param nameVideo the name video
	 * @return the response entity
	 */
	private ResponseEntity<byte[]> videoBanner(Boolean banner, String nameVideo ) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(PATH);
		log.debug("******** Nimbus Video Request ******");
		log.debug("******** Path:  {} ****** ", nameVideo);
		
		byte[] fileArray = new byte[1];
		File file = new File(builder.append(nameVideo).toString());
		if(!file.exists()) {
			log.debug("******** Path not found****** ");
			fileArray[0] = 0;
			return new ResponseEntity<byte[]>(fileArray,HttpStatus.BAD_REQUEST);
		} 
		log.debug("********Load video... ******");
		
		if (banner) {
			
			fileArray = new byte[88888];
			
		} else if (!banner) {

			fileArray = new byte[(int) file.length()];
		}
		
		try { 
			FileInputStream read = new FileInputStream(file);
			log.debug("******** Reading File: {} *****", nameVideo);
			read.read(fileArray);
			read.close();
			log.debug("******** Sending File: {} *****", nameVideo);
			return new ResponseEntity<byte[]>(fileArray,HttpStatus.OK);
		
		}catch(IOException ex){
			return new ResponseEntity<byte[]>(fileArray,HttpStatus.BAD_REQUEST);
		}
		
	}
	
}

