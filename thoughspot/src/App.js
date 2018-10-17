import React, { Component } from "react";

import "./App.css";
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

export default App;
