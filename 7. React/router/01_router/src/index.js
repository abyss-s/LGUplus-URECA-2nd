import React from "react";
import ReactDOM from "react-dom/client"; // ✅ 변경됨
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
import * as serviceWorker from "./serviceWorker";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
);

serviceWorker.unregister();
