import React, { Component } from "react";

class Select extends Component {
  render() {
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
}

export default Select;
