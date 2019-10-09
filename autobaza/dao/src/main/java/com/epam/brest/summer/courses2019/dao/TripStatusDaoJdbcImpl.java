package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.TripStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *  TripStatus DAO Interface implementation.
 */
@Component
public class TripStatusDaoJdbcImpl implements TripStatusDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${tripStatus.findAll}")
    private String findAllSql;

    @Value("${tripStatus.findById}")
    private String findByIdSql;

    private static final String TRIP_STATUS_ID = "tripStatusId";

    public TripStatusDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<TripStatus> findAll() {
        List<TripStatus> trips =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(TripStatus.class));
        return trips;
    }

    @Override
    public Optional<TripStatus> findById(Integer tripStatusId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(TRIP_STATUS_ID, tripStatusId);
        List<TripStatus> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(TripStatus.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }
}
