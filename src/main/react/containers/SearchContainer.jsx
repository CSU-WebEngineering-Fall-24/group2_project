import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import MovieCard from "../components/MovieCard";
import Pagination from "../components/Pagination";

const SearchContainer = () => {
	const location = useLocation();
	const { searchInput, year, type, results = [] } = location.state || {};
	const itemsPerPage = 15; // 3 rows of 5 movies
	const [movies, setMovies] = useState(results);
	const [currentPage, setCurrentPage] = useState(0);
	const startIndex = currentPage * itemsPerPage;
	const totalPages = Math.ceil(movies.length / itemsPerPage);

	const paginatedResults = movies.slice(startIndex, startIndex + itemsPerPage);

	useEffect(() => {
		if (currentPage >= totalPages) {
			setCurrentPage(0); // Reset to first page if out of bounds
		}
	}, [currentPage, totalPages]);

	function onPaginationClick(pageNumber) {
		setCurrentPage(pageNumber);
	}

	return (
		<div className="container mt-4 search-container">
			<h1>Search Results:</h1>
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

			{movies.length === 0 ? (
				<p>No results found for your search.</p>
			) : (
				<>
					<div className="row">
						{paginatedResults.map((movie, index) => (
							<div className="col-md-4 mb-4" key={index}>
								<MovieCard movie={movie} />
							</div>
						))}
					</div>
					<div className="pagination-container mt-auto d-flex justify-content-center">
						<Pagination items={movies} onClick={onPaginationClick} />
					</div>
				</>
			)}
		</div>
	);
};

export default SearchContainer;
