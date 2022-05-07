import React from "react";
import styles from "./order_payment.module.css";

const OrderPayment = ({ order }) => {
  const lecture = order.lecture;
  const voucher = order.voucher;
  const discountAmount = (lecture.price * voucher.percent) / 100;
  const amount = lecture.price - discountAmount;

  const payType = new Map([
    ["CREDIT_CARD", "신용카드"],
    ["NAVER_PAY", "네이버페이"],
    ["VIRTUAL_ACCOUNT", "가상계좌"],
    ["KAKAO_PAY", "카카오페이"],
  ]);

  return (
    <section className={styles.container}>
      <h1>
        총계
        <div>
          {voucher.voucherId === null ? (
            ""
          ) : (
            <span className={styles.origin}>
              ₩{lecture.price.toLocaleString()}
            </span>
          )}
          <span>₩{amount.toLocaleString()}</span>
        </div>
      </h1>
      <table className={styles.payment}>
        <tbody>
          <tr>
            <td>이메일</td>
            <td>{order.email}</td>
          </tr>
          <tr>
            <td>주문 상태</td>
            <td>결제완료</td>
          </tr>
          <tr>
            <td>주문 시각</td>
            <td>{order.createdAt}</td>
          </tr>
          <tr>
            <td>결제 수단</td>
            <td>{payType.get(order.paymentType)}</td>
          </tr>
        </tbody>
      </table>
    </section>
  );
};

export default OrderPayment;
