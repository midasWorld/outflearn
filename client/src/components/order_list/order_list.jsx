import React from "react";
import OrderItem from "../order_item/order_item";
import styles from "./order_list.module.css";

const OrderList = ({ orders, goToOrderDetails }) => {
  return (
    <div className={styles.container}>
      <h2>주문 내역</h2>
      <table className={styles.orders}>
        <thead>
          <tr>
            <th>주문 번호</th>
            <th>주문 날짜</th>
            <th>상태</th>
            <th>주문명</th>
            <th>금액</th>
            <th>주문자</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <OrderItem
              key={order.orderId}
              order={order}
              goToOrderDetails={goToOrderDetails}
            />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OrderList;
