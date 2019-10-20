package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.TripStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  TripStatus DAO Interface implementation.
 */
@Component
public class TripStatusDaoJdbcImpl implements TripStatusDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${tripStatus.findAll}")
    private String findAllSql;

    public TripStatusDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<TripStatus> findAll() {
        List<TripStatus> trips =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(TripStatus.class));
        return trips;
    }

}
