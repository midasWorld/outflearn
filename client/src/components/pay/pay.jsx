import React, { useRef, useState } from "react";
import styles from "./pay.module.css";

const Pay = ({ lecture, voucherService, orderService, goToMain }) => {
  const [amount, setAmount] = useState(lecture.price);
  const [voucher, setVoucher] = useState(null);
  const [payType, setPayType] = useState(null);

  const voucherRef = useRef();
  const emailRef = useRef();
  const acceptRef = useRef();

  const onPayClick = () => {
    if (emailRef.current.value === "") {
      alert("이메일을 입력하세요.");
      return;
    }

    if (payType == null) {
      alert("결제 수단을 선택하세요.");
      return;
    }

    if (acceptRef.current.checked === false) {
      alert("약관 동의를 해주세요.");
      return;
    }

    orderService
      .create({
        lectureId: lecture.lectureId,
        voucherId: voucher == null ? null : voucher.voucherId,
        email: emailRef.current.value,
        paymentType: payType,
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

  const onPayTypeClick = (event) => {
    const payBtn = event.target;
    const payType =
      payBtn.nodeName === "BUTTON" ? payBtn.value : payBtn.parentNode.value;
    setPayType(payType);
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
          <span>₩{amount.toLocaleString()}</span>
        </h1>
        <div className={styles.field}>
          <label className={styles.label}>이메일 주소</label>
          <input
            type="email"
            className={styles.input}
            name="email"
            ref={emailRef}
            placeholder="이메일을 입력하세요."
          />
        </div>
        <div className={styles.field}>
          <label className={styles.label}>결제수단</label>
          <div className={styles.paygroup}>
            <button value="CREDIT_CARD" onClick={onPayTypeClick}>
              <i className="fa-solid fa-credit-card"></i>
              <span>신용카드</span>
            </button>
            <button value="NAVER_PAY" onClick={onPayTypeClick}>
              <i className="fa-solid fa-n"></i>
              <span>네이버페이</span>
            </button>
            <button value="VIRTUAL_ACCOUNT" onClick={onPayTypeClick}>
              <i className="fa-solid fa-piggy-bank"></i>
              <span>가상계좌</span>
            </button>
            <button value="KAKAO_PAY" onClick={onPayTypeClick}>
              <i className="fa-brands fa-kickstarter-k"></i>
              <span>카카오페이</span>
            </button>
          </div>
        </div>
        <div className={styles.field}>
          <input type="checkbox" value="동의" ref={acceptRef} />
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
