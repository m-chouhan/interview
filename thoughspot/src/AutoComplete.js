import React, { Component } from "react";
import Downshift from "downshift";
import { timer, Subject, from } from "rxjs";
import { switchMap, debounce, distinctUntilChanged } from "rxjs/operators";

import "./AutoComplete.css";
import { getSuggestions } from "./api";

const event$ = new Subject();
//our event pipeline which
// : rejects any continuous duplicates
// : passes only single input in 500 ms
// : only caters to latest requests (switmap ignores previous streams)
// : retries once if api fails
const suggestions$ = event$.pipe(
  distinctUntilChanged(),
  debounce(() => timer(500)),
  switchMap(string =>
    from(
      getSuggestions(string)
        .catch(getSuggestions(string))
        .catch(error => [string])
    )
  )
);

//emits events/changes in last word into above pipeline
//uses downshift for rendering https://github.com/paypal/downshift

export default class AutoComplete extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: "",
      suggestions: []
    };
    suggestions$.subscribe(suggestions => this.setState({ suggestions }));
  }

  //when an item is selected from dropdown, append selected word from dropdown
  onDropdownItemSelect = selectedWord => {
    console.log(`selected text is ${selectedWord}`);
    const { text } = this.state;
    let wordArray = text.trim().split(/\s+/);
    wordArray.pop();

    let outputText =
      wordArray.length > 0
        ? wordArray.reduce((previous, current) => previous + " " + current) +
          " "
        : "";

    this.setState({
      text: outputText + selectedWord + " "
    });
  };
  //when text input changes
  onTextChange = event => {
    const text = event.target.value;
    this.setState({ text });
    //emit last word only for suggestions
    const words = text.trim().split(/\s+/);
    if (words.length > 0) event$.next(words[words.length - 1]);
  };

  renderSearchView = ({
    getInputProps,
    getItemProps,
    isOpen,
    inputValue,
    highlightedIndex,
    selectedItem
  }) => {
    return (
      <div>
        <input
          className="input"
          {...getInputProps({
            placeholder: "Search",
            onChange: this.onTextChange,
            value: this.state.text
          })}
        />
        {isOpen ? (
          <div className="downshift-dropdown">
            {this.state.suggestions.map((item, index) => {
              return (
                <div
                  className="dropdown-item"
                  {...getItemProps({ key: item, index, item })}
                  style={{
                    backgroundColor:
                      highlightedIndex === index ? "lightgray" : "white",
                    fontWeight: selectedItem === item ? "bold" : "normal"
                  }}
                >
                  {item}
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
        onChange={this.onDropdownItemSelect}
        itemToString={item => item}
      >
        {this.renderSearchView}
      </Downshift>
    );
  };
}
