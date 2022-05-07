import React from "react";
import OrderLectureList from "../order_lecture_list/order_lecture_list";
import OrderPayment from "../order_payment/order_payment";
import styles from "./order_details.module.css";

const OrderDetails = ({ order }) => {
  return (
    <div className={styles.container}>
      <OrderLectureList />
      <OrderPayment />
    </div>
  );
};

export default OrderDetails;
