import { useEffect, useState } from "react";
import "./app.css";
import LectureList from "./components/lecture_list/lecture_list";

function App() {
  const [lectures, setLectures] = useState([]);

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
    <>
      <LectureList lectures={lectures} />
    </>
  );
}

export default App;
