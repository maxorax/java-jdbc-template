package com.epam.izh.rd.online.autcion.mappers;

import com.epam.izh.rd.online.autcion.entity.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

@Component
public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item mapper = new Item();
        mapper.setItemId(resultSet.getLong("item_id"));
        mapper.setBidIncrement(resultSet.getDouble("bid_increment"));
        mapper.setBuyItNow(resultSet.getBoolean("buy_it_now"));
        mapper.setDescription(resultSet.getString("description"));
        mapper.setStartDate(new Date(resultSet.getDate("start_date").getTime()).toLocalDate());
        mapper.setStartPrice(resultSet.getDouble("start_price"));
        mapper.setStopDate(new Date(resultSet.getDate("stop_date").getTime()).toLocalDate());
        mapper.setTitle(resultSet.getString("title"));
        mapper.setUserId(resultSet.getLong("user_id"));
        return mapper;
    }
}
