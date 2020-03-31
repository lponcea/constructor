package org.constructor.web.multimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.constructor.multimedia.response.VideoResponse;
import org.constructor.security.AuthoritiesConstants;
import org.constructor.service.multimedia.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class FileUploadResource {
private final Logger log = LoggerFactory.getLogger(FileUploadResource.class);
	
	@Autowired
    private FileUploadService fileUploadService;
	
	@PostMapping("/fileUpload")
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public ResponseEntity<VideoResponse>  uploadFile( @RequestParam("file") MultipartFile file) {
		VideoResponse vr = new VideoResponse();
		log.debug("Upload File", file);
		String path = "";
		if (!file.isEmpty()) {
			vr = fileUploadService.saveFile(file);
		} else {
			vr.setName("empty file");
			return new ResponseEntity<>(vr,HttpStatus.BAD_REQUEST);
		}
		if(vr.getPath() == null) {
			vr.setName("invalid file");
			return new ResponseEntity<>(vr,HttpStatus.BAD_REQUEST);
		}
		log.debug("vr", vr.getName());
	  return  new ResponseEntity<>(vr,HttpStatus.OK);
	}
}
