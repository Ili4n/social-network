package org.socialnetwork.dal;

import org.socialnetwork.presentation.web.security.SecurityUser;
import org.socialnetwork.presentation.web.security.SecurityUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcSecurityUserRepositoryImpl implements SecurityUserRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcSecurityUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SecurityUser findByEmail(String email) {
        return this.jdbcTemplate.queryForObject("SELECT email, password FROM users WHERE email = ?", new Object[]{email}, new JdbcSecurityUserRepositoryImpl.SecurityUserRowMapper());
    }

    private static class SecurityUserRowMapper implements RowMapper<SecurityUser> {

        @Override
        public SecurityUser mapRow(ResultSet resultSet, int i) throws SQLException {

            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            SecurityUser user = new SecurityUser(password,email);

            return user;
        }
    }
}
