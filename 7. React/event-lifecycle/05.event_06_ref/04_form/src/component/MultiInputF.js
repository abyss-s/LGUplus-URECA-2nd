import React from "react";

function MultiInputF(props) {
  return (
    <form>
      <label for="isGoing">
        Is going:
        <input id="isGoing" type="checkbox" />
      </label>
      <br />
      <label for="numberOfGuests">
        Number of guests:
        <input id="numberOfGuests" type="number" />
      </label>
    </form>
  );
}

export default MultiInputF;
