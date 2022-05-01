package com.midas.outflearn.repository.lecture;

import com.midas.outflearn.model.lecture.Lecture;
import com.midas.outflearn.util.DateTimeUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.midas.outflearn.util.DateTimeUtils.timestampOf;

@Repository
public class LectureJdbcRepository implements LectureRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LectureJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Lecture> findAll() {
        return jdbcTemplate.query("SELECT * FROM lectures", lectureRowMapper);
    }

    @Override
    public List<Lecture> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM lectures WHERE name LIKE :name",
            Collections.singletonMap("name", "%" + name + "%"),
            lectureRowMapper);
    }

    @Override
    public Optional<Lecture> findById(Long lectureId) {
        try {
            return Optional.of(
                jdbcTemplate.queryForObject("SELECT * FROM lectures WHERE lecture_id = :lectureId",
                    Collections.singletonMap("lectureId", lectureId),
                    lectureRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Lecture insert(Lecture lecture) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update("INSERT INTO lectures (name, lecturer, price, thumbnail_image_url, created_at, updated_at)" +
            " VALUES (:name, :lecturer, :price, :thumbnailImageUrl, :createdAt, :updatedAt)",
            toParamSource(lecture),
            keyHolder);

        Number key = keyHolder.getKey();
        long generatedLectureId = key != null ? key.longValue() : -1;
        return new Lecture.Builder(lecture)
            .lectureId(generatedLectureId)
            .build();
    }

    @Override
    public Lecture update(Lecture lecture) {
        int update = jdbcTemplate.update("UPDATE lectures SET name = :name, lecturer = :lecturer, price = :price, thumbnail_image_url = :thumbnailImageUrl, updated_at = :updatedAt" +
            " WHERE lecture_id = :lectureId", toParamMap(lecture));

        if (update != 1) {
            throw new RuntimeException("Nothing was updated.");
        }
        return lecture;
    }

    private final RowMapper<Lecture> lectureRowMapper = (resultSet, i) -> {
        long lectureId = resultSet.getLong("lecture_id");
        String name = resultSet.getString("name");
        String lecturer = resultSet.getString("lecturer");
        long price = resultSet.getLong("price");
        String thumbnailImageUrl = resultSet.getString("thumbnail_image_url");
        LocalDateTime createdAt = DateTimeUtils.dateTimeOf(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = DateTimeUtils.dateTimeOf(resultSet.getTimestamp("updated_at"));
        return new Lecture(lectureId, name, lecturer, price, thumbnailImageUrl, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Lecture lecture) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("lectureId", lecture.getLectureId());
        paramMap.put("name", lecture.getName());
        paramMap.put("lecturer", lecture.getLecturer());
        paramMap.put("price", lecture.getPrice());
        paramMap.put("thumbnailImageUrl", lecture.getThumbnail_image_url());
        paramMap.put("createdAt", timestampOf(lecture.getCreatedAt()));
        paramMap.put("updatedAt", timestampOf(lecture.getUpdatedAt()));
        return paramMap;
    }

    private MapSqlParameterSource toParamSource(Lecture lecture) {
        return new MapSqlParameterSource(toParamMap(lecture));
    }
}
