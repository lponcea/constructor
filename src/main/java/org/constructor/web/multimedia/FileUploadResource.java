package org.constructor.web.multimedia;

import org.springframework.beans.factory.annotation.Autowired;
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
	public VideoResponse  uploadFile( @RequestParam("file") MultipartFile file) {
		VideoResponse vr = new VideoResponse();
		log.debug("Upload File", file);
		String path = "";
		if (!file.isEmpty()) {
			vr = fileUploadService.saveFile(file);
		} else {
			path = "Load Failed, Try again";
		}
	  return vr;
	}
}
