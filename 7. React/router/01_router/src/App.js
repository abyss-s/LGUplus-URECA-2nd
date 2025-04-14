import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import About from "./About";
import Home from "./Home";
import Profiles from "./Profiles";

const App = () => {
  return (
    <div>
      <ul>
        <li>
          <Link to="/">홈</Link>
        </li>
        <li>
          <Link to="/about">소개</Link>
        </li>
        <li>
          <Link to="/profiles">프로필</Link>
        </li>
      </ul>
      <hr />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/info" element={<About />} />
        <Route path="/profiles/*" element={<Profiles />} />
        {/* path='*' 
           정확한  path 요청이 들어오면 해당  Component 로 분기 하고 
           그 외는 모두 처리하는 기법 */}
        <Route
          path="*"
          element={
            <div>
              <h2>이 페이지는 존재하지 않습니다:</h2>
              <p>{window.location.pathname}</p>
            </div>
          }
        />
      </Routes>
    </div>
  );
};

export default App;
