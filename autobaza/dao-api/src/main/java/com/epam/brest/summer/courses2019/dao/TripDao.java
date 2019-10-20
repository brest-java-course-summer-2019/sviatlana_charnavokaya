package com.epam.brest.summer.courses2019.dao;


import com.epam.brest.summer.courses2019.model.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Trip DAO Interface.
 */
public interface TripDao {

    /**
     * Persist new trip.
     *
     * @param trip new trip
     * @return new trip object.
     */
    Trip add(Trip trip);

    /**
     * Update trip.
     *
     * @param trip trip
     */
    void update(Trip trip);

    /**
     * Delete trip with specified id.
     *
     * @param tripId trip id
     */
    void delete(Integer tripId);


    /**
     * Get trips.
     *
     * @return trips list.
     */
    List<Trip> findAll();

    /**
     * Get Trip By Id.
     *
     * @param tripId tripID
     * @return Trip
     */
    Optional<Trip> findById(Integer tripId);

    /**
     * Find trips by dates
     *
     * @param startDate  Beginning date .
     * @param endDate Ending date
     * @return A list of trips.
     */
    List<Trip> findByDates(LocalDate startDate, LocalDate endDate);
}
