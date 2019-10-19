package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *  Trip DAO Interface implementation.
 */
@Component
public class TripDaoJdbcImpl implements TripDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(TripDaoJdbcImpl.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${trip.findAll}")
    private String findAllSql;

    @Value("${trip.findById}")
    private String findByIdSql;

    @Value("${trip.insert}")
    private String insertSql;

    @Value("${trip.update}")
    private String updateSql;

    @Value("${trip.delete}")
    private String deleteSql;

    @Value("${trip.findByDates}")
    private String findByDatesSql;

    private static final String TRIP_ID = "tripId";
    private static final String DATE_TRIP = "dateTrip";
    private static final String CAR_ID = "carId";
    private static final String DISTANCE = "distance";
    private static final String TRIP_STATUS_ID = "tripStatusId";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";


    public TripDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Trip add(Trip trip) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DATE_TRIP, trip.getDateTrip());
        parameters.addValue(CAR_ID, trip.getCarId());
        parameters.addValue(DISTANCE, trip.getDistance());
        parameters.addValue(TRIP_STATUS_ID, trip.getTripStatusId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        trip.setTripId(generatedKeyHolder.getKey().intValue());
        return trip;
    }

    @Override
    public void update(Trip trip) {
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(trip)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update car in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer tripId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(TRIP_ID, tripId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete car from DB"));

    }

    @Override
    public List<Trip> findAll() {
        List<Trip> trips =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Trip.class));
        return trips;
    }

    @Override
    public Optional<Trip> findById(Integer tripId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(TRIP_ID, tripId);
        List<Trip> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Trip.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public List<Trip> findByDates(LocalDate startDate, LocalDate endDate) {

        LOGGER.debug("find trips by date: ({} : {})", startDate, endDate);
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue(START_DATE, startDate)
                .addValue(END_DATE, endDate);
        List<Trip> trips = namedParameterJdbcTemplate.query(findByDatesSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Trip.class));

        return trips;
    }
}
