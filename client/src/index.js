import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./app";
import "@fortawesome/fontawesome-free/js/all.js";
import Lecture from "./service/lecture";

const lecture = new Lecture(process.env.REACT_APP_BASE_URL);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App lecture={lecture} />
  </React.StrictMode>
);
