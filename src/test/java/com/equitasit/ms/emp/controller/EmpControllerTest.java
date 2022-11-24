package com.equitasit.ms.emp.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.equitasit.ms.emp.exception.EmpException;
import com.equitasit.ms.emp.exception.EmpExceptionConstants;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.equitasit.ms.emp.dto.EmpDTO;
import com.equitasit.ms.emp.service.EmpService;

@WebMvcTest(EmpController.class)
//@WebMvcTest(controllers = EmpController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@ActiveProfiles("test")
@ContextConfiguration
 class EmpControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmpService empService;

	private String baseUrl;

	@BeforeEach
	 void init() {
		baseUrl = "/emp";

	}

	@Test
	 void testSave() throws Exception {

		EmpDTO empDTO = getEmpDTO();

		JSONObject empDTOJsonObject = getEmpDTOJSONObject(empDTO);

		Mockito.when(empService.save(Mockito.any(EmpDTO.class))).thenReturn(getEmpDTO());

		this.mockMvc.perform(post(baseUrl).content(empDTOJsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated()).andExpect(content().string(containsString("1234")));

	}

	@Test
	 void testUpdate() throws Exception {

		EmpDTO empDTO = getEmpDTO();

		JSONObject empDTOJsonObject = getEmpDTOJSONObject(empDTO);

		Mockito.when(empService.update(Mockito.any(EmpDTO.class))).thenReturn(getEmpDTO());

		this.mockMvc.perform(put(baseUrl).content(empDTOJsonObject.toString()).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().is2xxSuccessful())
				.andExpect(content().string(containsString("1234")));
	}

	@Test
	 void testRemove() throws Exception {

		Mockito.doNothing().when(empService).remove(Mockito.anyInt());

		this.mockMvc.perform(delete(baseUrl + "/1234").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	 void testGetById() throws Exception {

		Mockito.when(empService.get(Mockito.anyInt())).thenReturn(getEmpDTO());

		this.mockMvc.perform(get(baseUrl + "/1234").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("1234")));

	}

	@Test
	 void testGetByIdWhenIdNotFound() throws Exception {

		Mockito.doThrow(new EmpException(EmpExceptionConstants.EMP_NOT_FOUND.getValue())).when(empService).get(Mockito.anyInt());

		this.mockMvc.perform(get(baseUrl + "/1234").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is4xxClientError());

	}

	@Test
	 void testGetAll() throws Exception {

		List<EmpDTO> list = new ArrayList<>();

		list.add(getEmpDTO());

		Mockito.when(empService.getAll()).thenReturn(list);

		this.mockMvc.perform(get(baseUrl).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().is2xxSuccessful()).andExpect(content().string(containsString("1234")));
	}

	 JSONObject getEmpDTOJSONObject(EmpDTO empDTO) throws Exception {

		JSONObject creditInfoObject = new JSONObject();

		creditInfoObject.put("empno", empDTO.getEmpno());

		creditInfoObject.put("ename", empDTO.getEname());

		return creditInfoObject;
	}

	private EmpDTO getEmpDTO() {
		EmpDTO empDTO = new EmpDTO();
		empDTO.setEmpno(1234);
		empDTO.setEname("nityaay");
		return empDTO;
	}

}
