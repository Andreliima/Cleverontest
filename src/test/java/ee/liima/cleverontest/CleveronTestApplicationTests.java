package ee.liima.cleverontest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.liima.cleverontest.controller.PermissionsController;
import ee.liima.cleverontest.model.Permission;
import ee.liima.cleverontest.service.PermissionsService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(PermissionsController.class)
//@ContextConfiguration(classes = {PermissionsController.class, PermissionsService.class})
@AutoConfigureMockMvc
class CleveronTestApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objMapper;

	@MockBean
	private PermissionsService permissionsService;

	@Test
	public void nameValidationTest()
			throws Exception {

		mvc.perform(post("/api/permissions")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": null}"))
				.andExpect(status().is4xxClientError());

		mvc.perform(post("/api/permissions")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"A\"}"))
				.andExpect(status().is4xxClientError());

		mvc.perform(post("/api/permissions")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"AB\"}"))
				.andExpect(status().is4xxClientError());

		mvc.perform(post("/api/permissions")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Tere\"}"))
				.andExpect(status().isCreated());

	}

//	@Test
//	public void updateNameTest() throws Exception {
//		var resultActions = mvc.perform(post("/api/permissions")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"name\": \"Tere\"}"))
//				.andExpect(status().isCreated());
//
//		var result = resultActions.andReturn();
//
//		System.out.println(result.getResponse().getContentAsString());
//		var permissionId = objMapper.readValue(result.getResponse().getContentAsString(), Permission.class).getId();
//
//		mvc.perform(put(String.format("/api/permissions/%d", permissionId))
//				.contentType(MediaType.APPLICATION_JSON)
//				.content("{\"name\": \"No\"}"))
//				.andDo(print())
//				.andExpect(status().is4xxClientError());
//	}

}
