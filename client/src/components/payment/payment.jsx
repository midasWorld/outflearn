import React from "react";
import { useLocation } from "react-router-dom";
import Cart from "../cart/cart";
import Pay from "../pay/pay";
import styles from "./payment.module.css";

const Payment = ({ voucherService, orderService, goToPage }) => {
  const location = useLocation();
  const lecture = location.state.lecture;

  return (
    <div className={styles.container}>
      <Cart lecture={lecture} />
      <Pay
        lecture={lecture}
        voucherService={voucherService}
        orderService={orderService}
        goToPage={goToPage}
      />
    </div>
  );
};

export default Payment;
