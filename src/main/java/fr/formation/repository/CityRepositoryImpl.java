package fr.formation.repository;

import fr.formation.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepositoryImpl {

    private static final String DB_TABLE_CITY = "villes_france_free";
    private static final String DB_CITY_ID = "ville_id";
    private static final String DB_CITY_NAME = "ville_nom_reel";
    private static final String DB_CITY_DEPARTMENT_CODE = "ville_departement";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();
        jdbcTemplate.query(
                "SELECT * FROM " + DB_TABLE_CITY,
                (rs, rowNum) -> cityList.add(new City(rs.getInt(DB_CITY_ID), rs.getString(DB_CITY_NAME), rs.getString(DB_CITY_DEPARTMENT_CODE)))
        );

        return cityList;
    }

    public City findOne(int id) {
        City city = jdbcTemplate.queryForObject(
                "SELECT * FROM " + DB_TABLE_CITY + " WHERE " + DB_CITY_ID + " = ?", new Object[]{id},
                (rs, rowNum) -> new City(rs.getInt(DB_CITY_ID), rs.getString(DB_CITY_NAME), rs.getString(DB_CITY_DEPARTMENT_CODE))

        );
        return city;
    }
}
