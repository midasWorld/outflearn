import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./app";
import "@fortawesome/fontawesome-free/js/all.js";
import Lecture from "./service/lecture";
import { BrowserRouter } from "react-router-dom";
import VoucherService from "./service/voucher";
import OrderService from "./service/order";

const lecture = new Lecture(process.env.REACT_APP_BASE_URL);
const voucherService = new VoucherService(process.env.REACT_APP_BASE_URL);
const orderService = new OrderService(process.env.REACT_APP_BASE_URL);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App
        lecture={lecture}
        voucherService={voucherService}
        orderService={orderService}
      />
    </BrowserRouter>
  </React.StrictMode>
);
