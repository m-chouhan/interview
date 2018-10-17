import React, { Component } from "react";
import Downshift from "downshift";

import "./AutoComplete.css";

export default class AutoComplete extends Component {
  state = {
    suggestions: [
      { name: "Harry Potter" },
      { name: "Net Moves" },
      { name: "Half of a yellow sun" },
      { name: "The Da Vinci Code" },
      { name: "Born a crime" }
    ]
  };

  onChange = selectedBook => {
    alert(`your favourite book is ${selectedBook.name}`);
  };

  renderChildren = ({
    getInputProps,
    getItemProps,
    isOpen,
    inputValue,
    highlightedIndex,
    selectedItem
  }) => {
    return (
      <div>
        <input {...getInputProps()} />
        {isOpen ? (
          <div className="downshift-dropdown">
            {this.state.suggestions.map((item, index) => {
              return (
                <div
                  className="dropdown-item"
                  {...getItemProps({ key: item.name, index, item })}
                  style={{
                    backgroundColor:
                      highlightedIndex === index ? "lightgray" : "white",
                    fontWeight: selectedItem === item ? "bold" : "normal"
                  }}
                >
                  {item.name}
                </div>
              );
            })}
          </div>
        ) : null}
      </div>
    );
  };
  render = () => {
    return (
      <Downshift
        onChange={this.onChange}
        itemToString={books => (books ? books.name : "")}
      >
        {this.renderChildren}
      </Downshift>
    );
  };
}
