package be.ucll.repository;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import be.ucll.model.User;

public class UserRowMapper implements RowMapper<User>{
    
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new User(rs.getString("name"), rs.getInt("age"),rs.getString("email"),rs.getString("password"));
    }
}
