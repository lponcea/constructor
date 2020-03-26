package org.constructor.service.multimedia;

import org.constructor.multimedia.response.VideoResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	
	 VideoResponse saveFile(MultipartFile file);

}
