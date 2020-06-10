package org.constructor.service.multimedia;

import org.constructor.multimedia.response.MultimediaResponse;
import org.constructor.multimedia.response.VideoResponse;
import org.constructor.service.dto.MultimediaDTO;

public interface MultimediaService {

	/**
	 * saveFile
	 * 
	 * @param file
	 * @return
	 */
	VideoResponse saveFile(MultimediaDTO file);
	
	/**
	 * deleteCourseCover
	 * 
	 * @param id
	 * @return
	 */
	MultimediaResponse deleteCourseCover (Long id );
	
	/**
	 * deleteFile
	 * 
	 * @param file
	 * @return
	 */
	String deleteFile(String file);
	
	/**
	 * otherCourseCoverExists
	 * 
	 * @param content
	 * @return
	 */
    boolean otherCourseCoverExists(String content);
}
