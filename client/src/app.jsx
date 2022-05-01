import { useEffect, useState } from "react";
import styles from "./app.module.css";
import LectureList from "./components/lecture_list/lecture_list";
import SearchHeader from "./components/search_header/search_header";

function App() {
  const [lectures, setLectures] = useState([]);
  const search = (query) => {
    const requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch(`http://localhost:8080/api/v1/lectures/${query}`, requestOptions)
      .then((response) => response.json())
      .then((result) => setLectures(result.response))
      .catch((error) => console.log("error", error));
  };

  useEffect(() => {
    const requestOptions = {
      method: "GET",
      redirect: "follow",
    };

    fetch("http://localhost:8080/api/v1/lectures", requestOptions)
      .then((response) => response.json())
      .then((result) => setLectures(result.response))
      .catch((error) => console.log("error", error));
  }, []);
  return (
    <div className={styles.app}>
      <SearchHeader onSearch={search} />
      <LectureList lectures={lectures} />
    </div>
  );
}

export default App;
