/**
 * 
 */
package org.constructor.web.multimedia;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
 * @author Edukai
 *
 */
public class ImageResourceTest {
	
	
	
	@Mock
	private MultimediaService service;

	@InjectMocks
	private ImageResource controller;
	
	private MockMvc mvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	/**
	 * testLoadImage
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadImage () throws Exception {
		final Long  id =  1l;
		final String  file =  "musica.jpeg";
		StringBuilder builder = new StringBuilder();
		builder.append("string");
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_IMAGE)
		          .param("id", id.toString()).param("file", file.toString()));
		
	}
	
	/**
	 * testLoadImageEmty
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadImageEmty () throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("string");
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_IMAGE)
		          .param("id", "").param("file", ""));
		
	}


}
