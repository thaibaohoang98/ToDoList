package com.springboot.todolist.controller;

import com.springboot.todolist.common.domain.PaginationParam;
import com.springboot.todolist.common.domain.ResultSet;
import com.springboot.todolist.common.domain.SortParam;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WorkControllerTest_getWorkResourceList
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
    private static final String COLLECTION_RESOURCE_URI = "/works";

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

    @ParameterizedTest(
            name = "status = 200, Check Parameter Validation, limit={0}, page={1}, sortBy={2}, direction={3}")
    @MethodSource("parameter_test_200")
    public void test_200(String page, String limit, String sortBy, String direction) throws Exception {
        testStatus200(page, limit, sortBy, direction);
    }

    @ParameterizedTest(
            name = "status = 400, Check Parameter Validation, limit={0}, page={1}, sortBy={2}, direction={3}")
    @MethodSource("parameter_test_400")
    public void test_400(String page, String limit, String sortBy, String direction, String message) throws Exception {
        testStatus400(page, limit, sortBy, direction, message);
    }

    private void testStatus200(String page, String limit, String sortBy, String direction) throws Exception {
        ResultSet<WorkResource> data = getDataMock(3);

        doReturn(data)
                .when(workService)
                .getWorkResourceList(Mockito.any(PaginationParam.class), Mockito.any(SortParam.class));

        MockHttpServletRequestBuilder request = get(COLLECTION_RESOURCE_URI);

        if (page != null) {
            request.queryParam("page",page);
        }
        if (limit != null) {
            request.queryParam("limit",limit);
        }
        if (sortBy != null) {
            request.queryParam("sortBy", sortBy);
        }
        if (direction != null) {
            request.queryParam("direction", direction);
        }

        ResultActions actual = mockMvc.perform(request);

        actual.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data").isNotEmpty());
    }

    private void testStatus400(String page, String limit, String sortBy, String direction, String message)
            throws Exception {
        MockHttpServletRequestBuilder request = get(COLLECTION_RESOURCE_URI);

        if (page != null) {
            request.queryParam("page",page);
        }
        if (limit != null) {
            request.queryParam("limit",limit);
        }
        if (sortBy != null) {
            request.queryParam("sortBy", sortBy);
        }
        if (direction != null) {
            request.queryParam("direction", direction);
        }

        ResultActions actual = mockMvc.perform(request);

        actual.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(BODY_CODE, equalTo(ErrorCode.VALIDATION_ERROR.getValue())))
                .andExpect(jsonPath(BODY_MESSAGE, equalTo(message)));
    }

    private ResultSet<WorkResource> getDataMock(int size) {
        List<WorkResource> workResources = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            Long id = (long) i;

            WorkResource workResource = WorkResource.builder()
                    .id(id)
                    .workName("work name")
                    .startingDate(LocalDate.of(2022, 1, 1))
                    .endingDate(LocalDate.of(2022, 1, 2))
                    .status(Status.PLANNING)
                    .build();

            workResources.add(workResource);
        }

        return new ResultSet<>(workResources, size);
    }

    private static Stream<Arguments> parameter_test_200() {
        return Stream.of(
                Arguments.of("3", "1", "id", "ASC"),
                Arguments.of("1", "3", "workName", "DESC"));
    }

    private static Stream<Arguments> parameter_test_400() {
        return Stream.of(
                Arguments.of("-1", "3", "id", "ASC",
                        "Page index must not be less than zero!"),
                Arguments.of("3", "-1", "id", "ASC",
                        "Page size must not be less than one!"));
    }
}
