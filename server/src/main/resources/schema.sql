DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS lectures CASCADE;
DROP TABLE IF EXISTS vouchers CASCADE;

-- 강의 데이터
CREATE TABLE lectures
(
    lecture_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    lecturer VARCHAR(50) NOT NULL,
    price DECIMAL NOT NULL,
    thumbnail_image_url VARCHAR(255) DEFAULT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (lecture_id)
);

-- 바우처 데이터
CREATE TABLE vouchers
(
    voucher_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    code VARCHAR(50) NOT NULL,
    percent DECIMAL NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (voucher_id)
);

CREATE TABLE orders
(
    order_id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    lecture_id BIGINT NOT NULL,
    voucher_id BIGINT NOT NULL,
    payment_type VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (order_id),
    CONSTRAINT fk_order_to_lecture FOREIGN KEY (lecture_id) REFERENCES lectures (lecture_id) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT fk_order_to_voucher FOREIGN KEY (voucher_id) REFERENCES vouchers (voucher_id) ON DELETE RESTRICT ON UPDATE RESTRICT
);