package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.stub.CarStub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *  CarStub DAO Interface implementation.
 */
@Component
public class CarStubDaoJdbcImpl implements CarStubDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${carStub.findAllSql}")
    private String findAllWithDistanceAndTripsSql;


    public CarStubDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<CarStub> findAllWithDistanceAndTrips() {
        List<CarStub> cars =
                namedParameterJdbcTemplate.query(findAllWithDistanceAndTripsSql, BeanPropertyRowMapper.newInstance(CarStub.class));
        return cars;
    }
}
