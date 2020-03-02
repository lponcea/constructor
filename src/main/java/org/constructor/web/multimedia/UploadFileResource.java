package org.constructor.web.multimedia;

import org.constructor.multimedia.response.VideoResponse;
import org.constructor.security.AuthoritiesConstants;
import org.constructor.service.multimedia.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class UploadFileResource {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileResource.class);
	
	@Autowired
    private UploadFileService uploadFileService;
	
	@PostMapping("/uploadFile")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public VideoResponse  uploadFile( @RequestParam("file") MultipartFile file) {
		VideoResponse vr = new VideoResponse();
		log.debug("Upload File", file);
		String path = "";
		if (!file.isEmpty()) {
			vr = uploadFileService.saveFile(file);
		} else {
			path = "Load Failed, Try again";
		}
	  return vr;
	}
	
}
