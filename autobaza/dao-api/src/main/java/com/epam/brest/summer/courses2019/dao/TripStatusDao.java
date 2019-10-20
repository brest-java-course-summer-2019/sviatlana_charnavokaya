package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.TripStatus;

import java.util.List;

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

}
