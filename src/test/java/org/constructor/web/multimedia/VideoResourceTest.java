/**
 * 
 */
package org.constructor.web.multimedia;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.constructor.multimedia.response.VideoResponse;
import org.constructor.service.dto.MultimediaDTO;
import org.constructor.service.multimedia.MultimediaService;
import org.constructor.utils.RestConstants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Norman Erick Estrada
 *
 */
public class VideoResourceTest {

	
	@Mock
	private MultimediaService service;

	@InjectMocks
	private VideoResource controller;
	
	private MockMvc mvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	/**
	 * testLoadVideo
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadVideo () throws Exception {
		final Long  id =  1l;
		final String  file =  "musica.mp4";
		StringBuilder builder = new StringBuilder();
		MultimediaDTO dto = new MultimediaDTO();
		VideoResponse value = new VideoResponse();
		builder.append("string");
		when(service.saveFile(dto)).thenReturn(value);
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_VIDEO)
		          .param("id", id.toString()).param("file", file.toString()));
		
	}
	
	/**
	 * testLoadVideoEmty
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadVideoEmty () throws Exception {
		StringBuilder builder = new StringBuilder();
		MultimediaDTO dto = new MultimediaDTO();
		VideoResponse value = new VideoResponse();
		builder.append("string");
		when(service.saveFile(dto)).thenReturn(value);
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_VIDEO)
		          .param("id", "").param("file", ""));
		
	}

}
