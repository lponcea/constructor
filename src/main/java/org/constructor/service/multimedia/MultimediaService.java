package org.constructor.service.multimedia;

import org.constructor.multimedia.response.MultimediaResponse;
import org.constructor.multimedia.response.VideoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MultimediaService {

	VideoResponse saveFile(MultipartFile file);
	
	MultimediaResponse deleteCourseCover (Long id );
	
	boolean deleteFile(String file);
}
