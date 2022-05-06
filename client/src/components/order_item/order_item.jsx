import React from "react";
import styles from "./order_item.module.css";

const OrderItem = ({ order }) => {
  return (
    <tr className={styles.order}>
      <td className={styles.id}>{order.orderId}</td>
      <td className={styles.date}>
        {
          new Date(+new Date(order.createdAt) + 3240 * 10000)
            .toISOString()
            .split("T")[0]
        }
      </td>
      <td className={styles.state}>결제 완료</td>
      <td className={styles.name}>{order.lectureName}</td>
      <td className={styles.price}>{order.price.toLocaleString()}</td>
      <td>{order.email}</td>
    </tr>
  );
};

export default OrderItem;
