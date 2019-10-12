package com.epam.brest.summer.courses2019.service;


import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.model.TripStatus;

import java.util.List;

/**
 * Trip Service Interface.
 */
public interface TripService {

    /**
     * Add new Trip.
     *
     * @param trip Trip.
     * @return trip.
     */
    Trip add(Trip trip);

    /**
     * Update trip.
     *
     * @param trip Trip
     */
    void update(Trip trip);

    /**
     * Delete Trip.
     *
     * @param tripId trip id
     */
    void delete(Integer tripId);

    /**
     * Find all trips stream.
     *
     * @return trips.
     */
    List<Trip> findAll();

    /**
     * Find Trip By Id.
     *
     * @param tripId id
     * @return Trip
     */
    Trip findById(Integer tripId);

    /**
     * Find all trip statuses stream.
     *
     * @return trip status list.
     */
    List<TripStatus> findAllTripStatuses();

    /**
     * Find TripStatus By Id.
     *
     * @param tripStatusId id
     * @return TripStatus
     */
    TripStatus findTripStatusById(Integer tripStatusId);

}
