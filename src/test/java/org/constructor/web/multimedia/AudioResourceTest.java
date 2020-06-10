/**
 * 
 */
package org.constructor.web.multimedia;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import org.constructor.multimedia.response.VideoResponse;
import org.constructor.service.multimedia.MultimediaService;
import org.constructor.utils.RestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



/**
 * @author Edukai
 *
 */

public class AudioResourceTest {
	
	@Mock
	private MultimediaService service;

	@InjectMocks
	private AudioResource controller;
	
	private MockMvc mvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * testLoadAudio
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadAudio () throws Exception {
		final Long  id =  1l;
		final String  file =  "musica.mp3";
		new  VideoResponse<Object>();
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_AUDIO)
		          .param("id", id.toString()).param("file", file.toString()));
		
	}
	
	/**
	 * testLoadAudioEmpty
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadAudioEmpty() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("");
		String nameAudio = null;
	  builder.append(nameAudio).toString();
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_AUDIO)
		          .param("id", "").param("file", ""));
				
	}

	
	

	

	
}

