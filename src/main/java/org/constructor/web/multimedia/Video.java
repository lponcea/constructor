package org.constructor.web.multimedia;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.constructor.security.AuthoritiesConstants;
import org.constructor.service.multimedia.UploadFileService;
import org.constructor.service.multimedia.VideoService;
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
	@Autowired
    private VideoService videoService;
	
	@RequestMapping(value = "/loadVideo", method = RequestMethod.GET, produces = "video/mp4")
	@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
	public byte[] loadVideo(@RequestParam("file") String nameVideo) {
		byte[] video = null;
		
		return video = videoService.getVideo(nameVideo);
	}
	

}
