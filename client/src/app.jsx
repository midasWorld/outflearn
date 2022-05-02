import { useEffect, useState } from "react";
import styles from "./app.module.css";
import LectureList from "./components/lecture_list/lecture_list";
import SearchHeader from "./components/search_header/search_header";

function App({ lecture }) {
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
      <SearchHeader onSearch={search} />
      <LectureList lectures={lectures} />
    </div>
  );
}

export default App;
