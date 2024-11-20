import React, { useState } from "react";

const SearchBar = () => {
  const [searchInput, setSearchInput] = useState("");
  const [year, setYear] = useState("2024"); // start at current year
  const [type, setType] = useState("All");

  const handleSearch = () => {
    console.log("Search Input:", searchInput);
    console.log("Year:", year);
    console.log("Type:", type);
    // Add API call here
  };

  return (
    <div className="container mt-4">
      <div className="input-group">
        <input
          type="number"
          className="form-control"
          id="yearInput"
          placeholder="e.g., 2022"
          value={year}
          onChange={(e) => setYear(e.target.value)}
          style={{ maxWidth: "120px" }}
        />
        <select
          className="form-select"
          id="typeDropdown"
          value={type}
          onChange={(e) => setType(e.target.value)}
          style={{ maxWidth: "150px" }}
        >
          <option value="All">All</option>
          <option value="Movie">Movie</option>
          <option value="Series">Series</option>
          <option value="Episode">Episode</option>
        </select>
        <input
          type="text"
          className="form-control flex-grow-1"
          placeholder="Search..."
          aria-label="Search"
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
        />
        <button className="btn btn-light" type="button" onClick={handleSearch}>
          <i className="ph ph-magnifying-glass"></i>
        </button>
      </div>
    </div>
  );
};

export default SearchBar;
