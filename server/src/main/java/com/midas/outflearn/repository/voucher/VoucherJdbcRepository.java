package com.midas.outflearn.repository.voucher;

import com.midas.outflearn.model.voucher.Voucher;
import com.midas.outflearn.model.voucher.VoucherStatus;
import com.midas.outflearn.util.DateTimeUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.midas.outflearn.util.DateTimeUtils.dateTimeOf;

@Repository
public class VoucherJdbcRepository implements VoucherRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public VoucherJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Voucher> findByCode(String code) {
        try {
            return Optional.of(
                jdbcTemplate.queryForObject("SELECT * FROM vouchers WHERE code = :code",
                    Collections.singletonMap("code", code),
                    voucherRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Voucher> voucherRowMapper = (resultSet, i) -> {
        long voucherId = resultSet.getLong("voucher_id");
        String name = resultSet.getString("name");
        String code = resultSet.getString("code");
        long percent = resultSet.getLong("percent");
        String status = resultSet.getString("status");
        VoucherStatus voucherStatus = VoucherStatus.valueOf(status);
        LocalDateTime createdAt = dateTimeOf(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = dateTimeOf(resultSet.getTimestamp("updated_at"));
        return new Voucher(voucherId, name, code, percent, voucherStatus, createdAt, updatedAt);
    };

}
