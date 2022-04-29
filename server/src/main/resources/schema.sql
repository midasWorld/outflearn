DROP TABLE IF EXISTS lectures CASCADE;

-- 강의 데이터
CREATE TABLE lectures
(
    lecutre_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    lecturer VARCHAR(50) NOT NULL,
    price DECIMAL NOT NULL,
    thumbnail_image_url VARCHAR(255) DEFAULT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME DEFAULT NULL,
    PRIMARY KEY (lecutre_id)
);