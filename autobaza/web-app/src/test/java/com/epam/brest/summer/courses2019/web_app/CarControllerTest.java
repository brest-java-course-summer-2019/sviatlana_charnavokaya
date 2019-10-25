package com.epam.brest.summer.courses2019.web_app;


import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.stub.CarStub;
import com.epam.brest.summer.courses2019.service.CarService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class CarControllerTest {

    private static final String CAR_ID = "carId";
    private static final String CAR_MODEL = "carModel";
    private static final String CAR_NUMBER = "carNumber";
    private static final String CAR_DRIVER = "carDriver";
    private static final String CAR_CHARACTERISTICS= "carCharacteristics";
    private static final String LOAD_CAPACITY = "loadCapacity";
    private static final String NUMBER_OF_TRIPS = "numberOfTrips";
    private static final String TOTAL_DISTANCE = "totalDistance";
    private static final Integer CAR_ID_1 = 1;
    private static final Integer CAR_ID_2 = 2;
    private static final Integer LOAD_CAPACITY_VALUE = 1000;
    private static final Integer NUMBER_OF_TRIPS_VALUE = 5;
    private static final Integer TOTAL_DISTANCE_VALUE = 2323;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CarService carService;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(carService);
        Mockito.reset(carService);

    }


    @Test
    public void getStubCars() throws Exception {

        Mockito.when(carService.findAllWithDistanceAndTrips()).thenReturn(Arrays.asList(createCarStub(CAR_ID_1), createCarStub(CAR_ID_2)));


           mockMvc.perform(
                MockMvcRequestBuilders.get("/cars/all/")
           ) .andDo(MockMvcResultHandlers.print()
        ).andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
           .andExpect(MockMvcResultMatchers.view().name("cars"))
           .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Cars List</title>")))
                   .andExpect(MockMvcResultMatchers.model().attribute("cars", hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", hasItem(
                        allOf(
                                hasProperty(CAR_ID, Matchers.is(CAR_ID_1)),
                                hasProperty(CAR_MODEL, Matchers.is(CAR_MODEL + CAR_ID_1)),
                                hasProperty(CAR_NUMBER, Matchers.is(CAR_NUMBER + CAR_ID_1)),
                                hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + CAR_ID_1)),
                                hasProperty(CAR_CHARACTERISTICS, Matchers.is(CAR_CHARACTERISTICS + CAR_ID_1)),
                                hasProperty(LOAD_CAPACITY, Matchers.is(LOAD_CAPACITY_VALUE + CAR_ID_1)),
                                hasProperty(NUMBER_OF_TRIPS, Matchers.is(NUMBER_OF_TRIPS_VALUE + CAR_ID_1)),
                                hasProperty(TOTAL_DISTANCE, Matchers.is(TOTAL_DISTANCE_VALUE + CAR_ID_1))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", hasItem(
                        allOf(
                                hasProperty(CAR_ID, Matchers.is(CAR_ID_2)),
                                hasProperty(CAR_MODEL, Matchers.is(CAR_MODEL + CAR_ID_2)),
                                hasProperty(CAR_NUMBER, Matchers.is(CAR_NUMBER + CAR_ID_2)),
                                hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + CAR_ID_2)),
                                hasProperty(CAR_CHARACTERISTICS, Matchers.is(CAR_CHARACTERISTICS + CAR_ID_2)),
                                hasProperty(LOAD_CAPACITY, Matchers.is(LOAD_CAPACITY_VALUE + CAR_ID_2)),
                                hasProperty(NUMBER_OF_TRIPS, Matchers.is(NUMBER_OF_TRIPS_VALUE + CAR_ID_2)),
                                hasProperty(TOTAL_DISTANCE, Matchers.is(TOTAL_DISTANCE_VALUE + CAR_ID_2))
                        )
                )))
        ;

        Mockito.verify(carService,  times(1)).findAllWithDistanceAndTrips();
    }

    @Test
    public  void  gotoAddCarPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/car"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Car</title>")))
        ;

    }

    @Test
    void gotoEditCarPage() throws Exception {
        Mockito.when(carService.findById(Mockito.anyInt())).thenReturn(createCar(CAR_ID_1));


        mockMvc.perform(
                MockMvcRequestBuilders.get("/car/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Car</title>")))
                .andExpect(MockMvcResultMatchers.view().name("car"))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_ID, Matchers.is(CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_MODEL, Matchers.is(CAR_MODEL + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_NUMBER, Matchers.is(CAR_NUMBER + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_CHARACTERISTICS, Matchers.is(CAR_CHARACTERISTICS + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(LOAD_CAPACITY, Matchers.is(LOAD_CAPACITY_VALUE + CAR_ID_1))))
        ;

        Mockito.verify(carService, Mockito.times(1)).findById((Mockito.anyInt()));
    }




    @Test
    public void addCar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/car")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(CAR_MODEL, CAR_MODEL + CAR_ID_2)
                .param(CAR_NUMBER, CAR_NUMBER + CAR_ID_2)
                .param(CAR_DRIVER, CAR_DRIVER + CAR_ID_2)
                .param(CAR_CHARACTERISTICS, CAR_CHARACTERISTICS + CAR_ID_2)
                .param(LOAD_CAPACITY, LOAD_CAPACITY_VALUE.toString()))

                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/cars/all"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/cars/all"))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_ID, nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_MODEL, Matchers.is(CAR_MODEL + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_NUMBER, Matchers.is(CAR_NUMBER + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_CHARACTERISTICS, Matchers.is(CAR_CHARACTERISTICS + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(LOAD_CAPACITY, Matchers.is(LOAD_CAPACITY_VALUE))))

        ;
        Mockito.verify(carService,  times(1)).add(Mockito.any(Car.class));

    }

    @Test
    public void updateCar() throws Exception {
       /* Mockito.doNothing().doThrow(new IllegalStateException())
                .when(carService).update(createCar(CAR_ID_1));*/

        mockMvc.perform(MockMvcRequestBuilders
                .post("/car/{carId}", CAR_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .param(CAR_MODEL, CAR_MODEL + CAR_ID_2)
                .param(CAR_NUMBER, CAR_NUMBER + CAR_ID_2)
                .param(CAR_DRIVER, CAR_DRIVER + CAR_ID_2)
                .param(CAR_CHARACTERISTICS, CAR_CHARACTERISTICS + CAR_ID_2)
                .param(LOAD_CAPACITY, LOAD_CAPACITY_VALUE.toString())
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/cars/all"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/cars/all"))
             //   .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_ID, Matchers.is(CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_MODEL, Matchers.is(CAR_MODEL + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_NUMBER, Matchers.is(CAR_NUMBER + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_CHARACTERISTICS, Matchers.is(CAR_CHARACTERISTICS + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + CAR_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(LOAD_CAPACITY, Matchers.is(LOAD_CAPACITY_VALUE))))
        ;

        Mockito.verify(carService, times(1)).update(Mockito.any(Car.class));
    }



    @Test
    void deleteCar() throws Exception {

        Mockito.doNothing().doThrow(new IllegalStateException()).when(carService).delete(Mockito.anyInt());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/car/{carId}/delete", CAR_ID_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/cars/all"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/cars/all"))
        ;

        Mockito.verify(carService, times(1)).delete(Mockito.anyInt());
    }



    @Test
    public void findCarById() throws Exception {
        Car car = createCar(CAR_ID_1);
        Mockito.when(carService.findById(CAR_ID_1)).thenReturn(car);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/car/" + CAR_ID_1))
                        .andDo(MockMvcResultHandlers.print()
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("car"))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_ID, Matchers.is(CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_MODEL, Matchers.is(CAR_MODEL + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_NUMBER, Matchers.is(CAR_NUMBER + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_CHARACTERISTICS, Matchers.is(CAR_CHARACTERISTICS + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + CAR_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("car", hasProperty(LOAD_CAPACITY, Matchers.is(LOAD_CAPACITY_VALUE + CAR_ID_1))))

        ;

        Mockito.verify(carService,  times(1)).findById(CAR_ID_1);
    }




    private Car createCar(int index) {
        Car car = new Car();
        car.setCarId(index);
        car.setCarModel(CAR_MODEL + index);
        car.setCarNumber(CAR_NUMBER + index);
        car.setCarDriver(CAR_DRIVER + index);
        car.setCarCharacteristics(CAR_CHARACTERISTICS + index);
        car.setLoadCapacity(LOAD_CAPACITY_VALUE + index);

        return car;
    }

    private CarStub createCarStub(int index) {
        CarStub car = new CarStub();
        car.setCarId(index);
        car.setCarModel(CAR_MODEL + index);
        car.setCarNumber(CAR_NUMBER + index);
        car.setCarDriver(CAR_DRIVER + index);
        car.setCarCharacteristics(CAR_CHARACTERISTICS + index);
        car.setLoadCapacity(LOAD_CAPACITY_VALUE + index);
        car.setNumberOfTrips(NUMBER_OF_TRIPS_VALUE + index);
        car.setTotalDistance(TOTAL_DISTANCE_VALUE + index);

        return car;
    }
}
