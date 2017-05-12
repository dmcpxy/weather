package com.dmcp.smart.dao;

import com.dmcp.smart.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * $Id: CityDao.java
 * <p>
 * Copyright(c) 1995-2017 by Asiainfo.com(China)
 * All rights reserved.
 *
 * @author Jacob Liu <Liuxy8@asiainfo.com>
 *         2017/5/3 09:45
 */
@Repository
public class LocationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int add(Location location) {
        String sql = "insert into location(id, name, lat, lon, country, admin1, url, feature_code, time_zone, asl, population, distance) " +
                " values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql, location.getName(), location.getLat(), location.getLon(), location.getCountry(), location.getAdmin1(),
                location.getUrl(), location.getFeatureCode(), location.getTimezone(), location.getAsl(), location.getPopulation(), location.getDistance());
        return rows;
    }

    public int update(Location location) {
        String sql = "update location set name=?, lat=?, lon=?, country=?, admin1=?, url=?, feature_code=?, time_zone=?, asl=?, population=?, distance=? where id = ?";
        int rows = jdbcTemplate.update(sql, location.getName(), location.getLat(), location.getLon(), location.getCountry(), location.getAdmin1(),
                location.getUrl(), location.getFeatureCode(), location.getTimezone(), location.getAsl(), location.getPopulation(), location.getDistance(), location.getId());
        return rows;
    }

    public Location findById(int locationId) {
        try {
            return jdbcTemplate.queryForObject("select * from location where id = ?", new Object[]{locationId}, new RowMapper<Location>() {
                @Override
                public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return getCityInfoFromResultSet(rs);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Location findByName(String locationName) {
        try {
            return jdbcTemplate.queryForObject("select * from location where name = ?", new Object[]{locationName}, new RowMapper<Location>() {
                @Override
                public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return getCityInfoFromResultSet(rs);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Location getCityInfoFromResultSet(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setId(rs.getInt("id"));
        location.setName(rs.getString("name"));
        location.setAdmin1(rs.getString("admin1"));
        location.setAsl(rs.getString("asl"));
        location.setCountry(rs.getString("country"));
        location.setDistance(rs.getString("distance"));
        location.setFeatureCode(rs.getString("feature_code"));
        location.setLat(rs.getString("lat"));
        location.setLon(rs.getString("lon"));
        location.setPopulation(rs.getString("population"));
        location.setTimezone(rs.getString("time_zone"));
        location.setUrl(rs.getString("url"));

        return location;
    }

    public List<Location> list() {
        return jdbcTemplate.query("select * from location", new RowMapper<Location>() {
            @Override
            public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
                return getCityInfoFromResultSet(rs);
            }
        });
    }

    public int delete(Integer locationId) {
        return jdbcTemplate.update("delete from location where id = ?", locationId);
    }
}
