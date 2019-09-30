package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Car;
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

import java.util.List;
import java.util.Optional;

/**
 *  Car DAO Interface implementation.
 */
@Component
public class CarDaoJdbcImpl implements CarDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${car.findAll}")
    private String findAllSql;

    @Value("${car.findById}")
    private String findByIdSql;

    @Value("${car.insert}")
    private String insertSql;

    @Value("${car.update}")
    private String updateSql;

    @Value("${car.delete}")
    private String deleteSql;

    private static final String CAR_ID = "carId";
    private static final String CAR_MODEL = "carModel";
    private static final String CAR_NUMBER = "carNumber";
    private static final String LOAD_CAPACITY = "loadCapacity";
    private static final String CAR_CHARACTERISTICS = "carCharacteristics";
    private static final String CAR_DRIVER = "carDriver";



    public CarDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Car add(Car car) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(CAR_MODEL, car.getCarModel());
        parameters.addValue(CAR_NUMBER, car.getCarNumber());
        parameters.addValue(LOAD_CAPACITY, car.getLoadCapacity());
        parameters.addValue(CAR_CHARACTERISTICS, car.getCarCharacteristics());
        parameters.addValue(CAR_DRIVER, car.getCarDriver());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, parameters, generatedKeyHolder);
        car.setCarId(generatedKeyHolder.getKey().intValue());
        return car;
    }


    @Override
    public void update(Car car) {
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(car)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update car in DB"));

    }
    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }


    @Override
    public void delete(Integer carId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CAR_ID, carId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete car from DB"));

    }

    @Override
    public List<Car> findAll() {

        List<Car> cars =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Car.class));
        return cars;
    }

    @Override
    public Optional<Car> findById(Integer carId) {

        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Car> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Car.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

  /*  private class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt("car_id"));
            car.setCarModel(resultSet.getString("car_model"));
            car.setCarNumber(resultSet.getString("car_number"));
            car.setLoadCapacity(resultSet.getInt("load_capacity"));
            car.setCarCharacteristics(resultSet.getString("car_characteristics"));
            car.setCarDriver(resultSet.getString("car_driver"));
            return car;
        }
    }*/
}
