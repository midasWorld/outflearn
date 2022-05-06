import React from "react";
import { Route, Routes } from "react-router-dom";
import OrderDetails from "../order_details/order_details";
import OrderList from "../order_list/order_list";

const OrderHistory = ({ orders }) => {
  return (
    <Routes>
      <Route path="/" element={<OrderList orders={orders} />} />
      <Route path="details" element={<OrderDetails />} />
    </Routes>
  );
};

export default OrderHistory;
