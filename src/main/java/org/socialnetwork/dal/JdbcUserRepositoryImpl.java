package org.socialnetwork.dal;

import org.socialnetwork.domain.user.User;
import org.socialnetwork.domain.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public JdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(long userId) {
        return this.jdbcTemplate.queryForObject("SELECT id, first_name, last_name, email FROM users WHERE id = ?", new Object[]{userId}, new UserRowMapper());
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)", user.getFirstName(), user.getLastName(), user.getEmail(),
                passwordEncoder.encode(user.getPassword())
        );
    }

    @Override
    public List<User> findUsers(String firstName, String email) {
        if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(email)) {
            return new ArrayList<>();
        }
        firstName = buildLikeExpression(firstName);
        email = buildLikeExpression(email);
        return this.jdbcTemplate.query("SELECT first_name,last_name, email FROM users WHERE first_name LIKE ? and email LIKE ?", new Object[]{firstName, email}, new UserRowMapper());
    }

    private String buildLikeExpression(String column) {
        return MessageFormat.format("%{0}%", column);
    }


    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            User user = new User(firstName, lastName, email);

            return user;
        }
    }
}
