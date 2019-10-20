package com.epam.brest.summer.courses2019.service;

import com.epam.brest.summer.courses2019.dao.TripDao;
import com.epam.brest.summer.courses2019.dao.TripStatusDao;
import com.epam.brest.summer.courses2019.model.Trip;
import com.epam.brest.summer.courses2019.model.TripStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 *  Trip Service Interface implementation.
 */
@Component
@Transactional
public class TripServiceImpl implements TripService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceImpl.class);

    private TripDao tripDao;

    private TripStatusDao tripStatusDao;

    public TripServiceImpl(TripDao tripDao, TripStatusDao tripStatusDao){
        this.tripDao = tripDao;
        this.tripStatusDao = tripStatusDao;
    }


    @Override
    public Trip add(Trip trip) {
        LOGGER.debug("add({})", trip);
        return tripDao.add(trip);
    }

    @Override
    public void update(Trip trip) {
        LOGGER.debug("update({})", trip);
        tripDao.update(trip);
    }

    @Override
    public void delete(Integer tripId) {
        LOGGER.debug("delete({})", tripId);
        tripDao.delete(tripId);
    }

    @Override
    public List<Trip> findAll() {
        LOGGER.debug("Find all trips");
        return tripDao.findAll();
    }

    @Override
    public Trip findById(Integer tripId) {
        LOGGER.debug("findById({})", tripId);
        return tripDao.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Failed to get trip from DB"));
    }

    @Override
    public List<TripStatus> findAllTripStatuses() {
        LOGGER.debug("Find all trip statuses");
        return tripStatusDao.findAll();
    }

    @Override
    public List<Trip> findByDates(LocalDate startDate, LocalDate endDate) {
        LOGGER.debug("find trips by date: ({} : {})", startDate, endDate);
        return tripDao.findByDates(startDate, endDate);
    }
}
