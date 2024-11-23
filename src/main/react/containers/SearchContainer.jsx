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

  console.log("Search Input:", searchInput);
  console.log("Year:", year);
  console.log("Type:", type);
  console.log("Results:", results);

  return (
    <div className="container mt-4 search-container">
      <h1>Search Results</h1>
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
