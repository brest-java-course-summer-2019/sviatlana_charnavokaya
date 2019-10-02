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

    private static final Integer CAR_ID_0 = 0;
    private static final Integer CAR_ID_1 = 1;

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

    @AfterEach
    public void after() {
        Mockito.reset(carService);
    }


    @Test
    public void departments() throws Exception {

        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(createCar(CAR_ID_0), createCar(CAR_ID_1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carModel", Matchers.is("Model" + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carNumber", Matchers.is("Number" + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carDriver", Matchers.is("Driver" + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carCharacteristics", Matchers.is("ref" + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].loadCapacity", Matchers.is(1000 + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carId", Matchers.is(CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carModel", Matchers.is("Model" + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carNumber", Matchers.is("Number" + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carDriver", Matchers.is("Driver" + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carCharacteristics", Matchers.is("ref" + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].loadCapacity", Matchers.is(1000 + CAR_ID_1)))
        ;

        Mockito.verify(carService).findAll();
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

    private Car createCar(int index) {
        Car car = new Car();
        car.setCarId(index);
        car.setCarModel("Model" + index);
        car.setCarNumber("Number" + index);
        car.setCarDriver("Driver" + index);
        car.setCarCharacteristics("ref" + index);
        car.setLoadCapacity(1000 + index);

        return car;
    }


}
