import { useEffect, useState } from "react";
import { Route, Routes, useNavigate, useLocation } from "react-router-dom";
import styles from "./app.module.css";
import LectureList from "./components/lecture_list/lecture_list";
import OrderHistory from "./components/order_history/order_history";
import Payment from "./components/payment/payment";
import SearchHeader from "./components/search_header/search_header";

function App({ lecture, voucherService, orderService }) {
  const navigate = useNavigate();
  const location = useLocation();

  const goToPayment = (lecture) => {
    navigate("/payment", {
      state: {
        lecture: lecture,
      },
    });
  };
  const goToOrder = () => {
    navigate("/order");
  };
  const goToMain = () => {
    navigate("/");
  };

  const [lectures, setLectures] = useState([]);
  const [orders, setOrders] = useState([]);

  const search = (query) => {
    if (location.pathname !== "/") {
      goToMain();
    }
    lecture
      .search(query) //
      .then((lectures) => setLectures(lectures));
  };

  const orderSearch = (query) => {
    orderService
      .search(query) //
      .then((orders) => setOrders(orders));
  };

  useEffect(() => {
    lecture
      .all() //
      .then((lectures) => setLectures(lectures));
  }, []);

  useEffect(() => {
    orderService
      .all() //
      .then((orders) => setOrders(orders));
  }, []);

  return (
    <div className={styles.app}>
      <SearchHeader
        onSearch={search}
        goToMain={goToMain}
        goToOrder={goToOrder}
      />
      <Routes>
        <Route
          exact
          path="/"
          element={
            <LectureList lectures={lectures} goToPayment={goToPayment} />
          }
        />
        <Route path="/order/*" element={<OrderHistory orders={orders} />} />
        <Route
          path="/payment"
          element={
            <Payment
              voucherService={voucherService}
              orderService={orderService}
              goToPage={goToOrder}
            />
          }
        />
      </Routes>
    </div>
  );
}

export default App;
