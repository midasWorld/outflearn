import React from "react";
import { useNavigate } from "react-router-dom";
import styles from "./order_item.module.css";

const OrderItem = ({ order }) => {
  const discount = (order.lecture.price * order.voucher.percent) / 100;
  const amount = order.lecture.price - discount;

  const navigate = useNavigate();

  const onNameClick = () => {
    navigate("/order/details", {
      state: {
        order: order,
      },
    });
  };

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
      <td className={styles.name} onClick={onNameClick}>
        {order.lecture.name}
      </td>
      <td className={styles.price}>{amount.toLocaleString()}</td>
      <td>{order.email}</td>
    </tr>
  );
};

export default OrderItem;
