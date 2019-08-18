package com.epam.brest.summer.courses2019.dao;

import com.epam.brest.summer.courses2019.model.Car;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *  Car DAO Interface implementation.
 */
@Component
public class CarDaoJdbcImpl implements CarDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select car_id, car_model, car_number, load_capacity, car_characteristics, car_driver from car order by 1";

    private final static String ADD_CAR =
            "insert into car (car_model, car_number, load_capacity, car_characteristics, car_driver) " +
                    "values (:carModel, :carNumber, :loadCapacity, :carCharacteristics, :carDriver)";

    private static final String FIND_BY_ID =
            "select car_id, car_model, car_number, load_capacity, car_characteristics, car_driver from car " +
                    "where car_id = :carId";

    private static final String UPDATE =
            "update car set car_model =  :carModel, car_number = :carNumber, load_capacity = :loadCapacity, " +
                    "car_characteristics = :carCharacteristics, car_driver = :carDriver " +
                    "where car_id = :carId";

    private static final String DELETE =
            "delete from car where car_id = :carId";

    private static final String CAR_ID = "carId";



    public CarDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Car add(Car car) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("carModel", car.getCarModel());
        parameters.addValue("carNumber", car.getCarNumber());
        parameters.addValue("loadCapacity", car.getLoadCapacity());
        parameters.addValue("carCharacteristics", car.getCarCharacteristics());
        parameters.addValue("carDriver", car.getCarDriver());
       // parameters.addValue("isFixed", car.getFixed());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_CAR, parameters, generatedKeyHolder);
        car.setCarId(generatedKeyHolder.getKey().intValue());
        return car;
    }


    @Override
    public void update(Car car) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(car)))
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
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from DB"));

    }

    @Override
    public List<Car> findAll() {

        List<Car> cars =
                namedParameterJdbcTemplate.query(SELECT_ALL, new CarRowMapper());
        return cars;
    }

    @Override
    public Optional<Car> findById(Integer carId) {

        SqlParameterSource namedParameters = new MapSqlParameterSource(CAR_ID, carId);
        List<Car> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Car.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    private class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car();
            car.setCarId(resultSet.getInt("car_id"));
            car.setCarModel(resultSet.getString("car_model"));
            car.setCarNumber(resultSet.getString("car_number"));
            car.setLoadCapacity(resultSet.getInt("load_capacity"));
            car.setCarCharacteristics(resultSet.getString("car_characteristics"));
            car.setCarDriver(resultSet.getString("car_driver"));
           // car.setFixed(resultSet.getBoolean("is_Fixed"));
            return car;
        }
    }
}
