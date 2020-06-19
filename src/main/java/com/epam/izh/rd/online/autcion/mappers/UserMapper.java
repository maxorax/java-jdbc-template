package com.epam.izh.rd.online.autcion.mappers;

import com.epam.izh.rd.online.autcion.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User mapper = new User();
        mapper.setUserId(resultSet.getLong("user_id"));
        mapper.setFullName(resultSet.getString("full_name"));
        mapper.setBillingAddress(resultSet.getString("billing_address"));
        mapper.setLogin(resultSet.getString("login"));
        mapper.setPassword(resultSet.getString("password"));
        return mapper;
    }
}
