package com.midas.outflearn.repository.order;

import com.midas.outflearn.model.order.Email;
import com.midas.outflearn.model.order.Order;
import com.midas.outflearn.model.order.PaymentType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.midas.outflearn.util.DateTimeUtils.dateTimeOf;
import static com.midas.outflearn.util.DateTimeUtils.timestampOf;

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

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query("SELECT * FROM orders", orderRowMapper);
    }

    private RowMapper<Order> orderRowMapper = (resultSet, i) -> {
        long orderId = resultSet.getLong("order_id");
        Email email = new Email(resultSet.getString("email"));
        long lectureId = resultSet.getLong("lecture_id");
        Long voucherId = resultSet.getObject("voucher_id", Long.class);
        PaymentType paymentType = PaymentType.valueOf(resultSet.getString("payment_type"));
        LocalDateTime createdAt = dateTimeOf(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = dateTimeOf(resultSet.getTimestamp("updated_at"));
        return new Order(orderId, email, lectureId, voucherId, paymentType, createdAt, updatedAt);
    };

    private Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId", order.getOrderId());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("lectureId", order.getLectureId());
        paramMap.put("voucherId", order.getVoucherId());
        paramMap.put("paymentType", order.getPaymentType().toString());
        paramMap.put("createdAt", timestampOf(order.getCreatedAt()));
        paramMap.put("updatedAt", timestampOf(order.getUpdatedAt()));
        return paramMap;
    }

    private SqlParameterSource toOrderParamSource(Order order) {
        Map<String, Object> paramMap = toOrderParamMap(order);
        return new MapSqlParameterSource(paramMap);
    }
}
