package com.xumpy.painting.database;

import com.xumpy.painting.database.model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileDB {
    @Autowired private JdbcTemplate jdbcTemplate;

    private static String TABLE_NAME = "FILES";
    private static String LOCATION_BASE = "LOCATION_BASE";
    private static String FILE_TYPE = "FILE_TYPE";

    public Number insertFile(String location_base, String file_type){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.usingGeneratedKeyColumns("rowId");
        jdbcInsert.withTableName(TABLE_NAME);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(LOCATION_BASE, location_base);
        parameters.put(FILE_TYPE, file_type);

        return jdbcInsert.executeAndReturnKey(parameters);
    }

    public Files selectFile(Number rowId){
        String sql = "SELECT rowId, * FROM " + TABLE_NAME + " WHERE rowId = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{rowId}, new BeanPropertyRowMapper<>(Files.class));
    }
}
