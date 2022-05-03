import { useEffect, useState } from "react";
import { Route, Routes, useNavigate, useLocation } from "react-router-dom";
import styles from "./app.module.css";
import LectureList from "./components/lecture_list/lecture_list";
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
  const goToMain = () => {
    navigate("/");
  };

  const [lectures, setLectures] = useState([]);

  const search = (query) => {
    if (location.pathname !== "/") {
      goToMain();
    }
    lecture
      .search(query) //
      .then((lectures) => setLectures(lectures));
  };

  useEffect(() => {
    lecture
      .all() //
      .then((lectures) => setLectures(lectures));
  }, []);

  return (
    <div className={styles.app}>
      <SearchHeader onSearch={search} goToMain={goToMain} />
      <Routes>
        <Route
          exact
          path="/"
          element={
            <LectureList lectures={lectures} goToPayment={goToPayment} />
          }
        />
        <Route
          path="/payment"
          element={
            <Payment
              voucherService={voucherService}
              orderService={orderService}
              goToMain={goToMain}
            />
          }
        />
      </Routes>
    </div>
  );
}

export default App;
