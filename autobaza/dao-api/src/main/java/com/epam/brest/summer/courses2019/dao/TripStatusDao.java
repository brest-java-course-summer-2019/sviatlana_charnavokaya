package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.TripStatus;

import java.util.List;
import java.util.Optional;

/**
 * TripStatus DAO Interface.
 */
public interface TripStatusDao {


    /**
     * Get trip statuses.
     *
     * @return tripStatus list.
     */
    List<TripStatus> findAll();

    /**
     * Get TripStatus By Id.
     *
     * @param tripStatusId tripStatusID
     * @return TripStatus
     */
    Optional<TripStatus> findById(Integer tripStatusId);
}
