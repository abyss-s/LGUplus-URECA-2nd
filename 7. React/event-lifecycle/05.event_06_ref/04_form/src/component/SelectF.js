import React, { useState } from "react";

function SelectF(props) {
  return (
    <form>
      <label>
        Pick your favorite flavor:
        <select></select>
      </label>
      <input type="submit" value="Submit" />
    </form>
  );
}

export default SelectF;
