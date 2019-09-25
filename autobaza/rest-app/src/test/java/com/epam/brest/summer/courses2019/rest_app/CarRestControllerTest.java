package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class CarRestControllerTest {

    @Autowired
    private CarRestController controller;

    @Autowired
    private CarService carService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(print())
                .build();
    }

    /*@AfterEach
    public void after() {
        Mockito.verifyNoMoreInteractions(service);
    }*/

    @AfterEach
    public void after() {
        Mockito.reset(carService);
    }


    @Test
    public void departments() throws Exception {

        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(createCar(0), createCar(1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carModel", Matchers.is("def0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carModel", Matchers.is("def1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carId", Matchers.is(1)))
        ;

        Mockito.verify(carService).findAll();
    }

    private Car createCar(int index) {
        Car car = new Car();
        car.setCarModel("def" + index);
        car.setCarId(index);
        return car;
    }

    @Test
    public void addCar() throws Exception {

        Car expectedCar = createCar(3);

        Car inputCar = new Car();
        inputCar.setCarModel(expectedCar.getCarModel());

        String json = new ObjectMapper().writeValueAsString(inputCar);

        Mockito.when(carService.add(any(Car.class))).thenReturn(expectedCar);

        MockHttpServletResponse response = mockMvc.perform(
                post("/department")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        String content = response.getContentAsString();
        Car result = objectMapper.readValue(content, Car.class);
        assertEquals(expectedCar.getCarModel(), result.getCarModel());
        assertEquals(expectedCar.getCarId(), result.getCarId());

    }

    @Test
    public void updateCar() throws Exception {

        Car car = createCar(1);
        String json = new ObjectMapper().writeValueAsString(car);

        mockMvc.perform(put("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());
    }


}
