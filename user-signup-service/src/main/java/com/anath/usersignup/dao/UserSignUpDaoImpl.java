package com.anath.usersignup.dao;

import com.anath.model.Address;
import com.anath.model.User;
import com.anath.usersignup.dao.exception.UserNotFoundException;
import com.anath.usersignup.dto.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.UUID;

@Component
public class UserSignUpDaoImpl implements UserSignUpDao {

    private final JdbcTemplate jdbcTemplate;

    public UserSignUpDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public User findUserById(String id){
        try {
        return jdbcTemplate.queryForObject("SELECT USERS.id, "
                                                   + "mail_id, "
                                                   + "registered_at, first_name, last_name, "
                                                   + "insta_uname, "
                                                   + "twitter_uname,development_env,state,city FROM USERS INNER JOIN USER_ADDRESS ON USERS.id = "
                                                   + "USER_ADDRESS.user_id "
                                                   + "WHERE USERS.id ="
                                                   + " ?",
                                           UserSignUpDaoImpl::mapRow,
                                           new Object[] {id});
        } catch (final EmptyResultDataAccessException exception) {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public String createUser(final UserDto user) {

        KeyHolder keyHolderUserTable = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO USERS (mail_id, registered_at, first_name, last_name, insta_uname, twitter_uname,development_env) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getEmail());
            ps.setTimestamp(2, Timestamp.valueOf(user.getRegisteredAt()));
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getInstagramUsername());
            ps.setString(6, user.getTwitterUsername());
            ps.setString(7, user.getDevelopmentEnvironment());

            return ps;
        }, keyHolderUserTable);

        String uuid = !keyHolderUserTable.getKeys().isEmpty() ? keyHolderUserTable.getKeys().values().iterator().next().toString() : null;

        KeyHolder keyHolderAddressTable = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO USER_ADDRESS (city, state, user_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getAddress().getCity());
            ps.setString(2, user.getAddress().getState());
            ps.setString(3, uuid);

            return ps;
        }, keyHolderAddressTable);

        return uuid;
    }

    private static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(rs.getObject("USERS.id").toString()));
        user.setEmail(rs.getString("mail_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setInstagramUsername(rs.getString("insta_uname"));
        user.setTwitterUsername(rs.getString("twitter_uname"));
        user.setDevelopmentEnvironment(rs.getString("development_env"));
        Address address = new Address();
        address.setState(rs.getString("state"));
        address.setCity(rs.getString("city"));
        user.setAddress(address);
        user.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());

        return user;
    }
}
