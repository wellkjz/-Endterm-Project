package com.berk.dragons.repository;

import com.berk.dragons.model.Rider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RiderRepository {

    private final JdbcTemplate jdbcTemplate;

    public RiderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Rider> riderMapper = (rs, rowNum) ->
            new Rider(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("skill_level")
            );

    public List<Rider> findAll() {
        return jdbcTemplate.query("SELECT * FROM riders ORDER BY id", riderMapper);
    }

    public Rider findById(int id) {
        String sql = "SELECT * FROM riders WHERE id = ?";
        List<Rider> result = jdbcTemplate.query(sql, riderMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Rider rider) {
        String sql = "INSERT INTO riders (name, skill_level) VALUES (?, ?)";
        jdbcTemplate.update(sql, rider.getName(), rider.getSkillLevel());
    }

    public void update(Rider rider) {
        String sql = "UPDATE riders SET name = ?, skill_level = ? WHERE id = ?";
        jdbcTemplate.update(sql, rider.getName(), rider.getSkillLevel(), rider.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM riders WHERE id = ?", id);
    }
}