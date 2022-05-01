INSERT INTO lectures(name, lecturer, price, thumbnail_image_url) VALUES ('데브코스 백엔드 1기', '백둥이', 10000, 'https://s3.ap-northeast-2.amazonaws.com/midas-outflearn/thumbnail/ef10ed9c-8ac0-4d00-9fd5-42fc56b1eed4.png');
INSERT INTO lectures(name, lecturer, price, thumbnail_image_url) VALUES ('데브코스 프론트 1기', '프롱이', 10000, 'https://s3.ap-northeast-2.amazonaws.com/midas-outflearn/thumbnail/208a83a0-9d8f-4d22-b3e3-c4b353ad6008.png');
INSERT INTO lectures(name, lecturer, price, thumbnail_image_url) VALUES ('데브코스 백엔드 2기', '백둥이', 20000, 'https://s3.ap-northeast-2.amazonaws.com/midas-outflearn/thumbnail/466f88ac-836b-4496-a58d-ba14cc8cfd99.png');
INSERT INTO lectures(name, lecturer, price, thumbnail_image_url) VALUES ('데브코스 프론트 2기', '프롱이', 20000, 'https://s3.ap-northeast-2.amazonaws.com/midas-outflearn/thumbnail/7462ef17-b74e-42f0-b4ee-1330dc7a03cb.png');
INSERT INTO lectures(name, lecturer, price, thumbnail_image_url) VALUES ('데브코스 자율주행 1기', '자랑이', 10000, 'https://s3.ap-northeast-2.amazonaws.com/midas-outflearn/thumbnail/53c2f303-3267-4ac5-8832-b3ca4cf581e0.png');

INSERT INTO vouchers(name, code, percent, status) VALUES ('오늘의 쿠폰', '1111-1111', 20, 'UNUSED');
INSERT INTO vouchers(name, code, percent, status) VALUES ('미래의 쿠폰', '2222-2222', 30, 'UNUSED');
INSERT INTO vouchers(name, code, percent, status) VALUES ('사용된 쿠폰', '3333-3333', 50, 'USED');
INSERT INTO vouchers(name, code, percent, status) VALUES ('옛날옛적 쿠폰', '5555-5555', 100, 'USED');