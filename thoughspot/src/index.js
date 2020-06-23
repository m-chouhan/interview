import "./index.css";
import ReactDOM from "react-dom";
import React, { Component } from "react";
import logo from "./thoughspot2.png";

import AutoComplete from "./AutoComplete";

class App extends Component {
  render() {
    return (
      <div className="App">
        <p>Hello Thoughspot!</p>
        <a
          className="App-link"
          href="https://github.com/m-chouhan/interview/tree/dev/thoughspot"
          target="_blank"
          rel="noopener noreferrer"
        >
          GitHub Link
        </a>
        <img src={logo} className="App-logo" alt="logo" />
        <AutoComplete />
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById("root"));
