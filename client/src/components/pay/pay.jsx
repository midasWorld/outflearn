import React, { useRef, useState } from "react";
import styles from "./pay.module.css";

const Pay = ({ lecture, voucherService, orderService, goToMain }) => {
  const [amount, setAmount] = useState(lecture.price);
  const [voucher, setVoucher] = useState(null);

  const voucherRef = useRef();
  const emailRef = useRef();

  const onPayClick = () => {
    orderService
      .create({
        lectureId: lecture.lectureId,
        voucherId: voucher == null ? null : voucher.voucherId,
        email: emailRef.current.value,
        paymentType: "NAVER_PAY",
      })
      .then((order) => handleOrderSuccess(order))
      .catch((error) => handleOrderError(error));
  };

  const handleOrderSuccess = (order) => {
    alert(
      `결제에 성공하였습니다.\n▼ 주문정보
  - 결제ID: ${order.orderId}
  - 이메일: ${order.email}
  - 결제방법:${order.paymentType}
  - 결제일시:${order.createdAt}`
    );
    goToMain();
  };

  const handleOrderError = (error) => {
    alert(error.response.data.error.message);
  };

  const onVoucherClick = () => {
    voucherService
      .search(voucherRef.current.value) //
      .then((voucher) => handleVoucherSuccess(voucher))
      .catch((error) => handleVoucherError(error));
  };

  const handleVoucherSuccess = (voucher) => {
    changeDiscount(voucher.percent);
    setVoucher(voucher);
  };

  const handleVoucherError = (error) => {
    changeDiscount(0);
    setVoucher(null);
    alert(error.response.data.error.message);
  };

  const changeDiscount = (discount) => {
    const discountAmount = (lecture.price * discount) / 100;
    const amount = lecture.price - discountAmount;
    setAmount(amount);
  };

  return (
    <section className={styles.container}>
      <div className={styles.voucher}>
        <input
          className={styles.input}
          type="search"
          ref={voucherRef}
          placeholder="보유한 쿠폰코드를 입력하세요."
        />
        <button
          className={styles.button}
          type="submit"
          onClick={onVoucherClick}
        >
          쿠폰
        </button>
      </div>
      <div className={styles.pay}>
        <h1>
          총계
          <span>${amount.toLocaleString()}</span>
        </h1>
        <div className={styles.field}>
          <label className={styles.label}>이메일</label>
          <input
            type="email"
            className={styles.input}
            name="email"
            ref={emailRef}
          />
        </div>
        <div className={styles.field}>
          <label className={styles.label}>결제수단</label>
          <div className={styles.paygroup}>
            <button>신용카드</button>
            <button>네이버페이</button>
            <button>가상계좌</button>
            <button>카카오페이</button>
          </div>
        </div>
        <div>
          <input type="checkbox" value="동의" />
          <span>(필수) 구매조건 및 개인정보취급방침 동의</span>
        </div>
        <button className={styles.button} type="submit" onClick={onPayClick}>
          결제하기
        </button>
      </div>
    </section>
  );
};

export default Pay;
