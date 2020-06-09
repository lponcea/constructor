/**
 * 
 */
package org.constructor.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Edukai
 *
 */
public class MultimediaConfiguration  implements Serializable{

	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -3282443442114276860L;

	/**
	 * Long image
	 */
	@Value("${multimedia.image}")
	private Long image;
	
	/**
	 * Long video
	 */
	@Value("${multimedia.video}")
	private Long video;
	
	/**
	 * Long  audio
	 */
	@Value("${multimedia.audio}")
	private Long audio;
	
	/**
	 * Long docs
	 */
	@Value("${multimedia.docs}")
	private Long docs;

	
	/**
	 * Empty Constructor
	 */
	public MultimediaConfiguration() {
		//Default Constructor
	}


	/**
	 * Constructor con parametros
	 * @param image
	 * @param video
	 * @param audio
	 * @param docs
	 */
	public MultimediaConfiguration(final Long image,final  Long video,final  Long audio,final Long docs) {
		this.image = image;
		this.video = video;
		this.audio = audio;
		this.docs = docs;
	}


	/**
	 * Get
	 * @return the image
	 */
	public Long getImage() {
		return image;
	}


	/**
	 * Set
	 * @param image the image to set
	 */
	public void setImage(final Long image) {
		this.image = image;
	}


	/**
	 * Get
	 * @return the video
	 */
	public Long getVideo() {
		return video;
	}


	/**
	 * Set
	 * @param video the video to set
	 */
	public void setVideo(final Long video) {
		this.video = video;
	}


	/**
	 * Get
	 * @return the audio
	 */
	public Long getAudio() {
		return audio;
	}


	/**
	 * Set
	 * @param audio the audio to set
	 */
	public void setAudio(final Long audio) {
		this.audio = audio;
	}


	/**
	 * Get
	 * @return the docs
	 */
	public Long getDocs() {
		return docs;
	}


	/**
	 * Set
	 * @param docs the docs to set
	 */
	public void setDocs(final Long docs) {
		this.docs = docs;
	}
	
	
	
	
	
}
