package com.springboot.todolist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.todolist.common.error.ErrorCode;
import com.springboot.todolist.domain.Status;
import com.springboot.todolist.resource.WorkResource;
import com.springboot.todolist.service.WorkService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkControllerTest_updateWorkResource
{
    /**
     * Response body - code
     */
    private static final String BODY_CODE = "code";

    /**
     * Response body - message
     */
    private static final String BODY_MESSAGE = "message";

    /**
     * Collection Resource URI
     */
    private static final String COLLECTION_RESOURCE_URI = "/works/{id}";

    /**
     * MockMvc
     */
    @Autowired
    protected MockMvc mockMvc;

    /**
     * Work service
     */
    @MockBean
    private WorkService workService;

    @ParameterizedTest(name = "status = 200, Check Validate Parameter id={0}, workName={1}")
    @MethodSource("parameter_test_200")
    public void test_status_200(String id, String workName) throws Exception {
        testStatus200(id, workName);
    }

    @ParameterizedTest(name = "status = 400, Check Validate Parameter id={0}, workName={1}")
    @MethodSource("parameter_test_400")
    public void test_status_400(String id, String workName) throws Exception {
        testStatus400(id, workName);
    }

    private void testStatus200(String id, String workName) throws Exception {
        WorkResource data = getDataMock(id, workName);
        Map<String, Object> postData = getPostData(id, workName);

        doReturn(data)
                .when(workService)
                .updateWorkResource(Mockito.any(), Mockito.any(WorkResource.class));

        MockHttpServletRequestBuilder request = put(COLLECTION_RESOURCE_URI, id)
                .contentType(MediaType.APPLICATION_JSON.toString())
                .content(toJson(postData));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private void testStatus400(String id, String workName) throws Exception {
        Map<String, Object> postData = getPostData(id, workName);

        MockHttpServletRequestBuilder request = put(COLLECTION_RESOURCE_URI, id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(postData));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath(BODY_CODE, equalTo(ErrorCode.VALIDATION_ERROR.getValue())))
                .andExpect(jsonPath(BODY_MESSAGE).isNotEmpty());
    }

    private WorkResource getDataMock(String id, String workName) {
        return WorkResource.builder()
                .id(Long.valueOf(id))
                .workName(workName)
                .status(Status.PLANNING)
                .isDeleted(false)
                .build();
    }

    private Map<String, Object> getPostData(String id, String workName) {
        return new HashMap<String, Object>() {
            {
                put("id", id);
                put("workName", workName);
            }
        };
    }

    private static String toJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    static Stream<Arguments> parameter_test_200() {
        return Stream.of(
                Arguments.of("1", "work name 1"),
                Arguments.of("2", "work name 2"),
                Arguments.of("3", "work name 3"));
    }

    private static Stream<Arguments> parameter_test_400() {
        return Stream.of(
                Arguments.of("1", null),
                Arguments.of("1", ""));
    }
}
