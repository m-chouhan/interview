import "./index.css";
import ReactDOM from "react-dom";
import React, { Component } from "react";

import AutoComplete from "./AutoComplete";

class App extends Component {
  render() {
    return (
      <div className="App">
        <p>Hello Thoughspot!</p>
        <AutoComplete />
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("root"));
