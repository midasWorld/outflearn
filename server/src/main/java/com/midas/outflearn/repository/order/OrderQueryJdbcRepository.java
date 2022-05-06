package com.midas.outflearn.repository.order;

import com.midas.outflearn.dto.order.OrderQueryDto;
import com.midas.outflearn.model.order.PaymentType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.midas.outflearn.util.DateTimeUtils.dateTimeOf;

@Repository
public class OrderQueryJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderQueryJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderQueryDto> findAllOrderQueryDto() {
        return jdbcTemplate.query("SELECT o.*, l.name AS lecture_name, l.price, v.name AS voucher_name, v.percent\n" +
            "FROM orders o\n" +
            "INNER JOIN lectures l ON o.lecture_id = l.lecture_id\n" +
            "LEFT JOIN vouchers v ON o.voucher_id = v.voucher_id",
            orderDtoRowMapper);
    }

    private RowMapper<OrderQueryDto> orderDtoRowMapper = (resultSet, i) -> {
        long orderId = resultSet.getLong("order_id");
        String email = resultSet.getString("email");
        long lectureId = resultSet.getLong("lecture_id");
        String lectureName = resultSet.getString("lecture_name");
        long price = resultSet.getLong("price");
        Long voucherId = resultSet.getObject("voucher_id", Long.class);
        String voucherName = resultSet.getString("voucher_name");
        long discountPercent = resultSet.getLong("percent");
        PaymentType paymentType = PaymentType.valueOf(resultSet.getString("payment_type"));
        LocalDateTime createdAt = dateTimeOf(resultSet.getTimestamp("created_at"));
        return new OrderQueryDto(orderId, email, lectureId, lectureName, price, voucherId, voucherName, discountPercent, paymentType, createdAt);
    };
}
