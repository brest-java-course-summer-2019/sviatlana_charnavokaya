package com.epam.brest.summer.courses2019.rest_app;

import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.service.TripService;
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

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class TripRestControllerTest {

    private static final LocalDate DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final LocalDate UPDATE_DATE_TRIP = LocalDate.of(2019, 9, 01);
    private static final Integer CAR_ID = 6;
    private static final Integer DISTANCE = 1201;
    private static final Integer TRIP_STATUS_ID = 1;
    private static final Integer TRIP_ID_0 = 0;
    private static final Integer TRIP_ID_1 = 1;

    @Autowired
    private TripRestController controller;

    @Autowired
    private TripService tripService;

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
        Mockito.reset(tripService);
    }


    @Test
    public void departments() throws Exception {

        Mockito.when(tripService.findAll()).thenReturn(Arrays.asList(createTrip(TRIP_ID_0), createTrip(TRIP_ID_1)));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/trips")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tripId", Matchers.is(TRIP_ID_0)))
              //  .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateTrip", Matchers.is(DATE_TRIP)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(CAR_ID + TRIP_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].distance", Matchers.is(DISTANCE + TRIP_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tripStatusId", Matchers.is(TRIP_STATUS_ID + TRIP_ID_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].tripId", Matchers.is(TRIP_ID_1)))
               // .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateTrip", Matchers.is(DATE_TRIP)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].carId", Matchers.is(CAR_ID + TRIP_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].distance", Matchers.is(DISTANCE + TRIP_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].tripStatusId", Matchers.is(TRIP_STATUS_ID + TRIP_ID_1)))
        ;

        Mockito.verify(tripService).findAll();
    }

   @Test
    public void addTrip() throws Exception {

        Trip expectedTrip = createTrip(3);

        Trip inputTrip = new Trip();
        inputTrip.setDistance(expectedTrip.getDistance());

        String json = new ObjectMapper().writeValueAsString(inputTrip);

        Mockito.when(tripService.add(any(Trip.class))).thenReturn(expectedTrip);

        MockHttpServletResponse response = mockMvc.perform(
                post("/trip")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        String content = response.getContentAsString();
        Trip result = objectMapper.readValue(content, Trip.class);
        assertEquals(expectedTrip.getDistance(), result.getDistance());
        assertEquals(expectedTrip.getTripId(), result.getTripId());

    }

    @Test
    public void updateCar() throws Exception {

        Trip trip = createTrip(1);
        String json = new ObjectMapper().writeValueAsString(trip);

        mockMvc.perform(put("/trip")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted());
    }

    private Trip createTrip(int index) {
        Trip trip = new Trip();
        trip.setTripId(index);
       // trip.setDateTrip(DATE_TRIP);
        trip.setCarId(CAR_ID + index);
        trip.setDistance(DISTANCE + index);
        trip.setTripStatusId(TRIP_STATUS_ID + index);

        return trip;
    }

}
