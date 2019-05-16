package org.akj.aws.lambda.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ConfigController {

	@RequestMapping(value = "/{entity}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getConfigByEntity(@PathVariable("entity") String entity)
			throws JsonParseException, JsonMappingException, IOException {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("entity", entity);

		ObjectMapper mapper = new ObjectMapper();
		Resource resource = new ClassPathResource("entity-config.json");
		Map<String, Object> entityConfig = mapper.readValue(resource.getFile(),
				new TypeReference<Map<String, Object>>() {
				});
		map.put("config", entityConfig);

		return map;
	}
}
