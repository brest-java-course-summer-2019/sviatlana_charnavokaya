package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
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
    private static final String CAR_MODEL = "Model";
    private static final String CAR_NUMBER = "Number";
    private static final String CAR_DRIVER = "Driver";
    private static final String CAR_CHARACTERISTICS= "ref";
    private static final Integer LOAD_CAPACITY = 1000;
    private static final Integer NUMBER_OF_TRIPS = 5;
    private static final Integer TOTAL_DISTANCE = 2323;

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
    public void cars() throws Exception {

        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(createCar(CAR_ID_0), createCar(CAR_ID_1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carModel", Matchers.is(CAR_MODEL + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carNumber", Matchers.is(CAR_NUMBER + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carDriver", Matchers.is(CAR_DRIVER + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carCharacteristics", Matchers.is(CAR_CHARACTERISTICS + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].loadCapacity", Matchers.is(LOAD_CAPACITY + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carId", Matchers.is(CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carModel", Matchers.is(CAR_MODEL + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carNumber", Matchers.is(CAR_NUMBER + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carDriver", Matchers.is(CAR_DRIVER + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carCharacteristics", Matchers.is(CAR_CHARACTERISTICS + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].loadCapacity", Matchers.is(LOAD_CAPACITY + CAR_ID_1)))
        ;

        Mockito.verify(carService).findAll();
        Mockito.verifyNoMoreInteractions(carService);
    }

    @Test
    public void getStubCars() throws Exception {

        Mockito.when(carService.findAllWithDistanceAndTrips()).thenReturn(Arrays.asList(createCarStub(CAR_ID_0), createCarStub(CAR_ID_1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/all/")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carModel", Matchers.is(CAR_MODEL + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carNumber", Matchers.is(CAR_NUMBER + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carDriver", Matchers.is(CAR_DRIVER + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carCharacteristics", Matchers.is(CAR_CHARACTERISTICS + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].loadCapacity", Matchers.is(LOAD_CAPACITY + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalDistance", Matchers.is(TOTAL_DISTANCE + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfTrips", Matchers.is(NUMBER_OF_TRIPS + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carId", Matchers.is(CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carModel", Matchers.is(CAR_MODEL + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carNumber", Matchers.is(CAR_NUMBER + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carDriver", Matchers.is(CAR_DRIVER + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carCharacteristics", Matchers.is(CAR_CHARACTERISTICS + CAR_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].loadCapacity", Matchers.is(LOAD_CAPACITY + CAR_ID_1)))
        ;

        Mockito.verify(carService).findAllWithDistanceAndTrips();
        Mockito.verifyNoMoreInteractions(carService);
    }


    @Test
    public void addCar() throws Exception {

        Car expectedCar = createCar(3);

        Car inputCar = new Car();
        inputCar.setCarModel(expectedCar.getCarModel());

        String json = objectMapper.writeValueAsString(inputCar);

        Mockito.when(carService.add(any(Car.class))).thenReturn(expectedCar);

        MockHttpServletResponse response = mockMvc.perform(
                post("/cars")
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
        Mockito.when(carService.add(any(Car.class))).thenReturn(car);
        String json = objectMapper.writeValueAsString(car);

        mockMvc.perform(put("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());

    }



    @Test
    void deleteCar() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/cars/" + CAR_ID_1)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(carService).delete(CAR_ID_1);
    }



    @Test
    public void findCarById() throws Exception {
        Car car = createCar(CAR_ID_0);
        Mockito.when(carService.findById(CAR_ID_0)).thenReturn(car);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/" + CAR_ID_0)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(car)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("carId", Matchers.is(CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("carModel", Matchers.is(CAR_MODEL + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("carNumber", Matchers.is(CAR_NUMBER + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("carDriver", Matchers.is(CAR_DRIVER + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("carCharacteristics", Matchers.is(CAR_CHARACTERISTICS + CAR_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("loadCapacity", Matchers.is(LOAD_CAPACITY + CAR_ID_0)));

        Mockito.verify(carService).findById(CAR_ID_0);
    }


    private Car createCar(int index) {
        Car car = new Car();
        car.setCarId(index);
        car.setCarModel(CAR_MODEL + index);
        car.setCarNumber(CAR_NUMBER + index);
        car.setCarDriver(CAR_DRIVER + index);
        car.setCarCharacteristics(CAR_CHARACTERISTICS + index);
        car.setLoadCapacity(LOAD_CAPACITY + index);

        return car;
    }

    private CarStub createCarStub(int index) {
        CarStub car = new CarStub();
        car.setCarId(index);
        car.setCarModel(CAR_MODEL + index);
        car.setCarNumber(CAR_NUMBER + index);
        car.setCarDriver(CAR_DRIVER + index);
        car.setCarCharacteristics(CAR_CHARACTERISTICS + index);
        car.setLoadCapacity(LOAD_CAPACITY + index);
        car.setNumberOfTrips(NUMBER_OF_TRIPS + index);
        car.setTotalDistance(TOTAL_DISTANCE + index);

        return car;
    }



}
