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
      <Welcome name="Ureca" style={style}>
        class
      </Welcome>
      <Welcome name="React Component"></Welcome>
    </div>
  );
};

export default App;
