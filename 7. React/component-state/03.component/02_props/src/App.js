import React from "react";
import Welcome from "./Welcome";

const style = {
  backgroundColor: "black",
  color: "pink",
  fontSize: "50px",
  fontWeight: "bold",
  padding: 6,
};
const App = () => {
  return (
    <div>
      <Welcome style={style}>class</Welcome>
      <Welcome name="React Component">ureca</Welcome>
    </div>
  );
};

export default App;