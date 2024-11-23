import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import MovieCard from "../components/MovieCard";
import Pagination from "../components/Pagination";

const SearchContainer = () => {
  const location = useLocation();
  const { searchInput, year, type, results } = location.state || {};

  const [currentPage, setCurrentPage] = useState(0);
  const itemsPerPage = 9; // 3 rows of 3 movies
  const startIndex = currentPage * itemsPerPage;
  const paginatedResults = results?.slice(
    startIndex,
    startIndex + itemsPerPage
  );

  return (
    <div className="container mt-4 search-container">
      <h1>Search Results: </h1>
      <div className="search-terms d-flex align-items-start flex-wrap">
        {searchInput && (
          <span className="badge badge-secondary">
            Search Term: {searchInput}
          </span>
        )}

        {year && <span className="badge badge-secondary">Year: {year}</span>}

        {type !== "All" && (
          <span className="badge badge-secondary">Type: {type}</span>
        )}
      </div>

      <div className="row">
        {paginatedResults.map((movie, index) => (
          <div className="col-md-4 mb-4" key={index}>
            <MovieCard movie={movie} />
          </div>
        ))}
      </div>
      <Pagination
        items={results}
        onClick={(pageIndex) => setCurrentPage(pageIndex)}
      />
    </div>
  );
};

export default SearchContainer;
