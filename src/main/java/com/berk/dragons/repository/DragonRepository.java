package com.berk.dragons.repository;

import com.berk.dragons.model.DragonBase;
import com.berk.dragons.patterns.DragonFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DragonRepository {

    private final JdbcTemplate jdbcTemplate;

    public DragonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper превращает строки SQL в объекты Java, используя FACTORY PATTERN
    private final RowMapper<DragonBase> dragonMapper = (rs, rowNum) -> {
        return DragonFactory.createDragon(
                rs.getString("type"),
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("stamina"),
                rs.getDouble("base_price")
        );
    };

    public List<DragonBase> findAll() {
        return jdbcTemplate.query("SELECT * FROM dragons", dragonMapper);
    }

    public DragonBase findById(int id) {
        String sql = "SELECT * FROM dragons WHERE id = ?";
        List<DragonBase> result = jdbcTemplate.query(sql, dragonMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void save(DragonBase dragon) {
        String sql = "INSERT INTO dragons (name, type, stamina, base_price) VALUES (?, ?, ?, ?)";
        // Определяем тип для БД
        String typeStr = dragon.getSpeciesType().contains("Strike") ? "Lightning" : "Fire";
        jdbcTemplate.update(sql, dragon.getName(), typeStr, dragon.getStamina(), dragon.getBasePrice());
    }

    public void update(DragonBase dragon) {
        String sql = "UPDATE dragons SET name = ?, base_price = ? WHERE id = ?";
        jdbcTemplate.update(sql, dragon.getName(), dragon.getBasePrice(), dragon.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM dragons WHERE id = ?", id);
    }
}