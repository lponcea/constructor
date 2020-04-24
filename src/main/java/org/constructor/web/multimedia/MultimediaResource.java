package org.constructor.web.multimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.constructor.multimedia.response.MultimediaResponse;
import org.constructor.multimedia.response.VideoResponse;
import org.constructor.security.AuthoritiesConstants;
import org.constructor.service.CursoService;
import org.constructor.service.multimedia.MultimediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class MultimediaResource {
private final Logger log = LoggerFactory.getLogger(MultimediaResource.class);
	
	@Autowired
    private MultimediaService multimediaService;
		
	@PostMapping(value = "/fileUpload",  produces = "application/json")
	public ResponseEntity<VideoResponse>  uploadFile( @RequestParam("file") MultipartFile file) {
		VideoResponse vr = new VideoResponse();
		log.debug("Upload File: {}", file); 
		if (!file.isEmpty()) {
			vr = multimediaService.saveFile(file);
		} else {
			vr.setName("empty file");
			return new ResponseEntity<>(vr,HttpStatus.BAD_REQUEST);
		}
		if(vr.getPath() == null) {
			vr.setName("invalid file");
			return new ResponseEntity<>(vr,HttpStatus.BAD_REQUEST);
		}
		log.debug(vr.getName(),"vr: {}");
	  return  new ResponseEntity<>(vr,HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/deleteCourseCover", produces = "application/json")
	public ResponseEntity<String> deleteCourseCover(@RequestParam("id") Long id) {
		
		log.debug("*************************   deleteCourseCover  *******************");
		
		log.debug("Id : {}", id);
		MultimediaResponse response;

		response = multimediaService.deleteCourseCover(id);

		if (response.isStatus()) {
			log.debug(response.getMessage());
			return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
		} else {
			log.debug(response.getMessage());
			return new ResponseEntity<>(response.getMessage(), HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping(value = "/deleteFile",  produces = "application/json")
	public ResponseEntity<String> deleteFile(@RequestParam("file") String file) {
		
		log.debug("*************************   deleteFile  *******************");
		
		log.debug("Path : {}", file);
		String response = "";

		response = multimediaService.deleteFile(file);

		if (response.equals("successful")) {
			log.debug("file removed successfully");
			return new ResponseEntity<>("file removed successfully", HttpStatus.OK);
		}
		if (response.equals("failed")) {
			log.debug("file not found");
			return new ResponseEntity<>("file not found", HttpStatus.NO_CONTENT);
		}else {
			log.debug("resource with dependency");
			return new ResponseEntity<>("resource with dependency", HttpStatus.ACCEPTED);
		}
	}
}
