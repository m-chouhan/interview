import React, { Component } from "react";
import Downshift from "downshift";
import {
  Observable,
  timer,
  Subject,
  ReplaySubject,
  from,
  of,
  range
} from "rxjs";
import {
  map,
  filter,
  switchMap,
  debounce,
  distinctUntilChanged
} from "rxjs/operators";

import "./AutoComplete.css";
import { getSuggestions } from "./api";

const event$ = new Subject();
const suggestions$ = event$.pipe(
  distinctUntilChanged(),
  debounce(() => timer(500)),
  switchMap(string => from(getSuggestions(string).catch(error => [string])))
);

export default class AutoComplete extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: "",
      suggestions: []
    };
    suggestions$.subscribe(suggestions => this.setState({ suggestions }));
  }

  itemSelected = text => {
    console.log(`selected text is ${text}`);
  };

  inputOnChange = event => {
    const text = event.target.value;
    this.setState({ text });
    const words = text.split(" ");
    event$.next(words[words.length - 1]);
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
          {...getInputProps({
            placeholder: "Search",
            onChange: this.inputOnChange,
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
      <Downshift onChange={this.itemSelected} itemToString={item => item}>
        {this.renderSearchView}
      </Downshift>
    );
  };
}
