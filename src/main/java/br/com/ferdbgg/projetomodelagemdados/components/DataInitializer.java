package br.com.ferdbgg.projetomodelagemdados.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class DataInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Value("classpath:sql/script0001.sql")
    private org.springframework.core.io.Resource dataScript;

    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        try {
            String sql = StreamUtils.copyToString(dataScript.getInputStream(), StandardCharsets.UTF_8);
            jdbcTemplate.execute(sql);
        } catch (IOException e) {
            // Tratar exceção, se necessário
            e.printStackTrace();
        }
    }
}
