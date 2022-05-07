import React from "react";
import { useLocation } from "react-router-dom";
import OrderLectureList from "../order_lecture_list/order_lecture_list";
import OrderPayment from "../order_payment/order_payment";
import styles from "./order_details.module.css";

const OrderDetails = (props) => {
  const location = useLocation();
  const order = location.state.order;

  return (
    <>
      <h2>
        구매 상세 내역<span>(주문번호: {order.orderId})</span>
      </h2>
      <div className={styles.container}>
        <OrderLectureList lecture={order.lecture} voucher={order.voucher} />
        <OrderPayment order={order} />
      </div>
    </>
  );
};

export default OrderDetails;
