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
 */
public class DocsResourceTest {
	

	@Mock
	private MultimediaService service;

	@InjectMocks
	private DocsResource controller;
	
	private MockMvc mvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	/**
	 * testLoadDocs
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadDocs () throws Exception {
		final Long  id =  1l;
		final String  file =  "musica.pdf";
		StringBuilder builder = new StringBuilder();
		builder.append("string");
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_DOCS)
		          .param("id", id.toString()).param("file", file.toString()));
		
	}
	
	/**
	 * testLoadDocsEmpty
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoadDocsEmpty () throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("string");
		this.mvc.perform(get("/" + RestConstants.PATH_API + "/" + RestConstants.PATH_LOAD_DOCS)
		          .param("id", "").param("file", ""));
		
	}


}


