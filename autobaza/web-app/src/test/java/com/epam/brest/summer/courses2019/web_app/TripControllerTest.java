package com.epam.brest.summer.courses2019.web_app;


import com.epam.brest.summer.courses2019.model.Car;
import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.model.TripStatus;
import com.epam.brest.summer.courses2019.service.CarService;
import com.epam.brest.summer.courses2019.service.TripService;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class TripControllerTest {

    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final LocalDate DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final LocalDate START_DATE = LocalDate.of(2019, 9, 01);
    private static final LocalDate END_DATE = LocalDate.of(2019, 9, 06);
    private static final String TRIP_ID = "tripId";
    private static final String DATE_TRIP_FIELD = "dateTrip";
    private static final String DISTANCE = "distance";
    private static final String CAR_ID = "carId";
    private static final String TRIP_STATUS_ID = "tripStatusId";
    private static final Integer TRIP_STATUS_ID_VALUE = 1;
    private static final Integer DISTANCE_VALUE = 1201;
    private static final Integer ID_1 = 1;
    private static final Integer ID_2 = 2;
    private static final String CAR_MODEL = "carModel";
    private static final String CAR_NUMBER = "carNumber";
    private static final String CAR_DRIVER = "carDriver";
    private static final String CAR_CHARACTERISTICS= "carCharacteristics";
    private static final String LOAD_CAPACITY = "loadCapacity";
    private static final Integer LOAD_CAPACITY_VALUE = 1000;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TripService tripService;

    @Autowired
    private CarService carService;

    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(tripService);
        Mockito.reset(tripService);
        Mockito.reset(carService);

    }


    @Test
    public void trips() throws Exception {

        Mockito.when(tripService.findAll()).thenReturn(Arrays.asList(createTrip(ID_1), createTrip(ID_2)));
        Mockito.when(tripService.findAllTripStatuses()).thenReturn(Arrays.asList(createTripStatus(ID_1), createTripStatus(ID_2)));
        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(createCar(ID_1), createCar(ID_2)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/trips")
        ) .andDo(MockMvcResultHandlers.print()
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("trips"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>List Trips</title>")))
                .andExpect(MockMvcResultMatchers.model().attribute("trips", hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("tripStatuses", hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("trips", hasItem(
                        allOf(
                                hasProperty(TRIP_ID, Matchers.is(ID_1)),
                                hasProperty(DATE_TRIP_FIELD, Matchers.is(DATE_TRIP)),
                                hasProperty(TRIP_STATUS_ID, Matchers.is(ID_1)),
                                hasProperty(DISTANCE, Matchers.is(DISTANCE_VALUE + ID_1)),
                                hasProperty(CAR_ID, Matchers.is(ID_1))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("trips", hasItem(
                        allOf(
                                hasProperty(TRIP_ID, Matchers.is(ID_2)),
                                hasProperty(DATE_TRIP_FIELD, Matchers.is(DATE_TRIP)),
                                hasProperty(TRIP_STATUS_ID, Matchers.is( ID_1)),
                                hasProperty(DISTANCE, Matchers.is(DISTANCE_VALUE + ID_2)),
                                hasProperty(CAR_ID, Matchers.is(ID_1))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", hasItem(
                        allOf(
                                hasProperty(CAR_ID, Matchers.is(ID_2)),
                                hasProperty(CAR_NUMBER, Matchers.is( CAR_NUMBER + ID_2)),
                                hasProperty(CAR_DRIVER, Matchers.is(CAR_DRIVER + ID_2))
                        )
                )))
        ;

        Mockito.verify(tripService,  times(1)).findAll();
        Mockito.verify(tripService,  times(1)).findAllTripStatuses();
        Mockito.verify(carService,  times(1)).findAll();
    }

    @Test
    public  void  gotoAddTripPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/trip"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Trip</title>")))
        ;
        Mockito.verify(tripService,  times(1)).findAllTripStatuses();
        Mockito.verify(carService,  times(1)).findAll();
    }



    @Test
    void gotoEditTripPage() throws Exception {
        Mockito.when(tripService.findById(Mockito.anyInt())).thenReturn(createTrip(ID_1));
        Mockito.when(tripService.findAllTripStatuses()).thenReturn(Arrays.asList(createTripStatus(ID_1),
                createTripStatus(ID_2), createTripStatus(ID_1)));
        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(createCar(ID_1), createCar(ID_2)));


        mockMvc.perform(
                MockMvcRequestBuilders.get("/trip/1")
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Trip</title>")))
                .andExpect(MockMvcResultMatchers.view().name("trip"))
                .andExpect(MockMvcResultMatchers.model().attribute("tripStatuses", hasSize(3)))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_ID, Matchers.is(ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DISTANCE, Matchers.is(DISTANCE_VALUE + ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DATE_TRIP_FIELD, Matchers.is(DATE_TRIP))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(CAR_ID, Matchers.is(ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_STATUS_ID, Matchers.is(ID_1))))
        ;

        Mockito.verify(tripService,  times(1)).findById(Mockito.anyInt());
        Mockito.verify(tripService,  times(1)).findAllTripStatuses();
        Mockito.verify(carService,  times(1)).findAll();
    }




    @Test
    public void addTrip() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/trip")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(DATE_TRIP_FIELD, DATE_TRIP.format(DATE_FORMATER))
                .param(CAR_ID, ID_1.toString())
                .param(DISTANCE, DISTANCE_VALUE.toString())
                .param(TRIP_STATUS_ID, ID_2.toString()))

                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/trips"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trips"))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_ID, nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DATE_TRIP_FIELD, Matchers.is(DATE_TRIP))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DISTANCE, Matchers.is(DISTANCE_VALUE))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(CAR_ID, Matchers.is(ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_STATUS_ID, Matchers.is(ID_2))))

        ;
        Mockito.verify(tripService,  times(1)).add(Mockito.any(Trip.class));

    }



    @Test
    public void updateTrip() throws Exception {
        Mockito.doNothing().doThrow(new IllegalStateException())
                .when(tripService).update(createTrip(ID_1));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/trip/{tripId}", ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(TRIP_ID, ID_1.toString())
                .param(DATE_TRIP_FIELD, DATE_TRIP.format(DATE_FORMATER))
                .param(CAR_ID, ID_1.toString())
                .param(DISTANCE, DISTANCE_VALUE.toString())
                .param(TRIP_STATUS_ID, ID_1.toString())
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/trips"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trips"))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_ID, Matchers.is(ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DATE_TRIP_FIELD, Matchers.is(DATE_TRIP))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DISTANCE, Matchers.is(DISTANCE_VALUE))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(CAR_ID, Matchers.is(ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_STATUS_ID, Matchers.is(ID_1))))
        ;

        Mockito.verify(tripService, times(1)).update(Mockito.any(Trip.class));
    }



    @Test
    void deleteTrip() throws Exception {

        Mockito.doNothing().doThrow(new IllegalStateException()).when(carService).delete(Mockito.anyInt());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/trip/{tripId}/delete", ID_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/trips"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/trips"))
        ;

        Mockito.verify(tripService, times(1)).delete(Mockito.anyInt());
    }



    @Test
    public void findTripById() throws Exception {
        Trip trip = createTrip(ID_2);
        Mockito.when(tripService.findById(ID_2)).thenReturn(trip);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/trip/" + ID_2))
                .andDo(MockMvcResultHandlers.print()
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("trip"))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_ID, Matchers.is(ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DATE_TRIP_FIELD, Matchers.is(DATE_TRIP))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(DISTANCE, Matchers.is(DISTANCE_VALUE + ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(CAR_ID, Matchers.is(ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute("trip", hasProperty(TRIP_STATUS_ID, Matchers.is(ID_1))))

        ;

        Mockito.verify(tripService,  times(1)).findById(ID_2);
        Mockito.verify(tripService,  times(1)).findAllTripStatuses();
        Mockito.verify(carService,  times(1)).findAll();
    }


    @Test
    public void findTripsByDates() throws  Exception {
        Mockito.when(tripService.findByDates(START_DATE, END_DATE)).thenReturn(Arrays.asList(createTrip(ID_1), createTrip(ID_2)));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/tripsFilter")
                .param("startDate", START_DATE.format(DATE_FORMATER))
                .param("endDate", END_DATE.format(DATE_FORMATER)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("trips"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>List Trips</title>")))
                .andExpect(MockMvcResultMatchers.model().attribute("trips", hasSize(2)));

        Mockito.verify(tripService,  times(1)).findByDates(START_DATE, END_DATE);
        Mockito.verify(tripService,  times(1)).findAllTripStatuses();
        Mockito.verify(carService,  times(1)).findAll();

    }


    private Trip createTrip(int index) {
        Trip trip = new Trip();
        trip.setTripId(index);
        trip.setDateTrip(DATE_TRIP);
        trip.setCarId(ID_1);
        trip.setDistance(DISTANCE_VALUE + index);
        trip.setTripStatusId(ID_1);

        return trip;
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

    private TripStatus createTripStatus(int index) {
        TripStatus tripStatus = new TripStatus();
        tripStatus.setTripStatusId(index);
        tripStatus.setTripStatusName("statusName" + index);

        return tripStatus;
    }
}
