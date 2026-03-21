package com.vsu.demo.repository;

import com.vsu.demo.entity.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProfileRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<Profile> findProfileByLogin(String login) {
        String sql = "SELECT id, login, balance from profile where login = :login";
        List<Profile> profiles = namedParameterJdbcTemplate.query(sql, Map.of("login", login), PROFILE_ROW_MAPPER);
        if (profiles.size() > 1) {
            throw new RuntimeException("More than one profile found for login: " + login);
        }
        if (profiles.isEmpty()) {
            return Optional.empty();
        }
        return profiles.stream().findFirst();
    }

    public void lockOnValue(Object value){
        String sql = "SELECT pg_advisory_xact_lock(hashtext(:lock));";
        namedParameterJdbcTemplate.queryForObject(sql, Map.of("lock", value.toString()), Object.class);
    }

    public Optional<Profile> findProfileByLoginWithLock(String login) {
        String sql = "SELECT id, login, balance from profile where login = :login FOR UPDATE";
        List<Profile> profiles = namedParameterJdbcTemplate.query(sql, Map.of("login", login), PROFILE_ROW_MAPPER);
        if (profiles.size() > 1) {
            throw new RuntimeException("More than one profile found for login: " + login);
        }
        if (profiles.isEmpty()) {
            return Optional.empty();
        }
        return profiles.stream().findFirst();
    }

    public boolean createProfile(Profile profile) {
        String sql = "INSERT INTO profile (id, login, balance) VALUES (:id, :login, :balance)";
        return namedParameterJdbcTemplate.update(
                sql, Map.of(
                        "id", profile.id(),
                        "login", profile.login(),
                        "balance", profile.balance()
                )
        ) == 1;
    }

    private static final RowMapper<Profile> PROFILE_ROW_MAPPER = (rs, rowNum) -> new Profile(
            rs.getObject("id", UUID.class),
            rs.getString("login"),
            rs.getBigDecimal("balance")
    );
}