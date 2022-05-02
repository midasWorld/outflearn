import { useEffect, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import styles from "./app.module.css";
import LectureList from "./components/lecture_list/lecture_list";
import Payment from "./components/payment/payment";
import SearchHeader from "./components/search_header/search_header";

function App({ lecture }) {
  const navigate = useNavigate();
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
        <Route path="/payment" element={<Payment />} />
      </Routes>
    </div>
  );
}

export default App;
