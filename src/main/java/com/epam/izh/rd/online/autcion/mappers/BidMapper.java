package com.epam.izh.rd.online.autcion.mappers;

import com.epam.izh.rd.online.autcion.entity.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.ZoneId;
@Component
public class BidMapper implements RowMapper<Bid> {

    @Override
    public Bid mapRow(ResultSet resultSet, int i) throws SQLException {
        Bid mapper = new Bid();
        mapper.setBidId(resultSet.getLong("bid_id"));
        mapper.setBidDate(new Date(resultSet.getDate("bid_date").getTime()).toLocalDate());
        mapper.setBidValue(resultSet.getDouble("bid_value"));
        mapper.setItemId(resultSet.getLong("item_id"));
        mapper.setUserId(resultSet.getLong("user_id"));
        return mapper;
    }
}
