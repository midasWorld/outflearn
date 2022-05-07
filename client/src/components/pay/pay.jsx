import React, { useRef, useState } from "react";
import styles from "./pay.module.css";

const Pay = ({ lecture, voucherService, orderService, goToPage }) => {
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
    goToPage();
  };

  const handleOrderError = (error) => {
    alert(error.response.data.error.message);
  };

  const searchVoucher = () => {
    if (voucherRef.current.value === "") {
      alert("바우처 코드를 입력해주세요.");
      return;
    }

    voucherService
      .search(voucherRef.current.value) //
      .then((voucher) => handleVoucherSuccess(voucher))
      .catch((error) => handleVoucherError(error));
  };

  const onVoucherClick = () => {
    searchVoucher();
  };

  const onVoucherKeyDown = (event) => {
    if (event.key === "Enter") {
      searchVoucher();
    }
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

  const onVoucherChange = () => {
    if (voucherRef.current.value === "") {
      setVoucher(null);
      changeDiscount(0);
    }
  };

  const changeDiscount = (discount) => {
    const discountAmount = (lecture.price * discount) / 100;
    const amount = lecture.price - discountAmount;
    setAmount(amount);
  };

  const onPayTypeClick = (event) => {
    const payBtn = event.target;
    const clickedPayType =
      payBtn.nodeName === "BUTTON" ? payBtn.value : payBtn.parentNode.value;

    if (clickedPayType === payType) {
      setPayType(null);
      return;
    }
    setPayType(clickedPayType);
  };

  return (
    <section className={styles.container}>
      <div className={styles.voucher}>
        <input
          className={styles.input}
          type="search"
          ref={voucherRef}
          placeholder="보유한 쿠폰코드를 입력하세요."
          onKeyDown={onVoucherKeyDown}
          onChange={onVoucherChange}
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
          <div>
            {voucher === null ? (
              ""
            ) : (
              <span className={styles.origin}>
                ₩{lecture.price.toLocaleString()}
              </span>
            )}
            <span>₩{amount.toLocaleString()}</span>
          </div>
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
            <button
              value="CREDIT_CARD"
              onClick={onPayTypeClick}
              className={payType === "CREDIT_CARD" ? styles.active : ""}
            >
              <i className="fa-solid fa-credit-card"></i>
              <span>신용카드</span>
            </button>
            <button
              value="NAVER_PAY"
              onClick={onPayTypeClick}
              className={payType === "NAVER_PAY" ? styles.active : ""}
            >
              <i className="fa-solid fa-n"></i>
              <span>네이버페이</span>
            </button>
            <button
              value="VIRTUAL_ACCOUNT"
              onClick={onPayTypeClick}
              className={payType === "VIRTUAL_ACCOUNT" ? styles.active : ""}
            >
              <i className="fa-solid fa-piggy-bank"></i>
              <span>가상계좌</span>
            </button>
            <button
              value="KAKAO_PAY"
              onClick={onPayTypeClick}
              className={payType === "KAKAO_PAY" ? styles.active : ""}
            >
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
