package com.midas.outflearn.repository.order;

import com.midas.outflearn.model.order.Order;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order insert(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update("INSERT INTO orders(email, lecture_id, voucher_id, payment_type, created_at, updated_at)" +
            " VALUES (:email, :lectureId, :voucherId, :paymentType, :createdAt, :updatedAt)",
            toOrderParamSource(order),
            keyHolder);

        Number key = keyHolder.getKey();
        long generatedOrderId = key != null ? key.longValue() : -1;
        return new Order.Builder(order)
            .orderId(generatedOrderId)
            .build();
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId", order.getOrderId());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("lectureId", order.getLectureId());
        paramMap.put("voucherId", order.getVoucherId());
        paramMap.put("paymentType", order.getPaymentType().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());
        return paramMap;
    }

    private SqlParameterSource toOrderParamSource(Order order) {
        Map<String, Object> paramMap = toOrderParamMap(order);
        return new MapSqlParameterSource(paramMap);
    }
}
