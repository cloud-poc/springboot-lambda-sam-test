package org.akj.aws.lambda.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.akj.aws.lambda.test.Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = { Application.class })
@AutoConfigureMockMvc
class ConfigControllerTest {

	@Autowired
	@InjectMocks
	private ConfigController configController;

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private MockHttpServletRequest request;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(configController);
	}

	@Test
	void test() throws Exception {
		//Mockito.when(request.)
		// caution: this is might not be suitable for project, should use mockito to mock service layer response, 
		// otherwise it has dependency on service layer
		mockMvc.perform(get("/ca")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.entity").value("ca")).andExpect(jsonPath("$.config").exists());
	}

}
