package org.socialnetwork.dal;

import org.socialnetwork.domain.user.User;
import org.socialnetwork.domain.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private PasswordEncoder passwordEncoder;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
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
                "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)",user.getFirstName(),user.getLastName(),user.getEmail(),
                passwordEncoder.encode(user.getPassword())
        );
    }

    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException  {

            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");

            User user = new User(firstName,lastName,email);

            return user;
        }
    }
}
