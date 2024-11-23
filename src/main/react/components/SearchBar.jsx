import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { fetchMovies } from "../services/apiService";

const SearchBar = () => {
  const [searchInput, setSearchInput] = useState("");
  const [year, setYear] = useState("");
  const [type, setType] = useState("All");

  const navigate = useNavigate();

  /**
   * Handles the search button click.
   * Fetches the movies based on the parameters and navigates to the search results page.
   */
  const handleSearch = async () => {
    const results = await fetchMovies(
      searchInput,
      year,
      type !== "All" ? type : null
    );

    navigate("/search", {
      state: {
        searchInput,
        year,
        type,
        results,
      },
    });
  };

  return (
    <div className="container mt-4">
      <div className="input-group">
        <input
          type="number"
          className="form-control"
          id="yearInput"
          value={year}
          placeholder="Year"
          onFocus={() => {
            if (!year) setYear("2024");
          }}
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
