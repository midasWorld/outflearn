import React from "react";
import { useLocation } from "react-router-dom";
import Cart from "../cart/cart";
import Pay from "../pay/pay";
import styles from "./payment.module.css";

const Payment = (props) => {
  const location = useLocation();

  return (
    <div className={styles.container}>
      <Cart lecture={location.state.lecture} />
      <Pay />
    </div>
  );
};

export default Payment;
