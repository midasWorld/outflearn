package com.midas.outflearn.repository.order;

import com.midas.outflearn.dto.order.OrderJoinLectureDto;
import com.midas.outflearn.dto.order.OrderQueryDto;
import com.midas.outflearn.dto.order.OrderJoinVoucherDto;
import com.midas.outflearn.model.order.PaymentType;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.midas.outflearn.util.DateTimeUtils.dateTimeOf;

@Repository
public class OrderQueryJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderQueryJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderQueryDto> findAllOrderQueryDto(Optional<String> email) {
        Map<String, Object> params = new HashMap<>();
        String sql = "SELECT o.*, l.name AS lecture_name, l.price, l.thumbnail_image_url, v.name AS voucher_name, v.percent" +
                        " FROM orders o" +
                        " INNER JOIN lectures l ON o.lecture_id = l.lecture_id" +
                        " LEFT JOIN vouchers v ON o.voucher_id = v.voucher_id";

        if (email.isPresent()) {
            sql += " WHERE o.email = :email";
            params.put("email", email.get());
        }

        return jdbcTemplate.query(sql, params, orderDtoRowMapper);
    }

    public Optional<OrderQueryDto> findOrderQueryDtoById(Long orderId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT o.*, l.name AS lecture_name, l.price, l.thumbnail_image_url, v.name AS voucher_name, v.percent" +
                    " FROM orders o" +
                    " INNER JOIN lectures l ON o.lecture_id = l.lecture_id" +
                    " LEFT JOIN vouchers v ON o.voucher_id = v.voucher_id" +
                    " WHERE o.order_id = :orderId",
                Collections.singletonMap("orderId", orderId),
                orderDtoRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<OrderQueryDto> orderDtoRowMapper = (resultSet, i) -> {
        long orderId = resultSet.getLong("order_id");
        String email = resultSet.getString("email");
        long lectureId = resultSet.getLong("lecture_id");
        String lectureName = resultSet.getString("lecture_name");
        long price = resultSet.getLong("price");
        String thumbnailImageUrl = resultSet.getString("thumbnail_image_url");
        Long voucherId = resultSet.getObject("voucher_id", Long.class);
        String voucherName = resultSet.getString("voucher_name");
        long percent = resultSet.getLong("percent");
        PaymentType paymentType = PaymentType.valueOf(resultSet.getString("payment_type"));
        LocalDateTime createdAt = dateTimeOf(resultSet.getTimestamp("created_at"));
        return new OrderQueryDto(
            orderId,
            email,
            new OrderJoinLectureDto(lectureId, lectureName, price, thumbnailImageUrl),
            new OrderJoinVoucherDto(voucherId, voucherName, percent),
            paymentType,
            createdAt
        );
    };
}
