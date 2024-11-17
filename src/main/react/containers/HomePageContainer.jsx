import React, { useEffect } from "react";
import MovieCarousel from "../components/MovieCarousel";
import Pagination from "../components/Pagination";

const HomePageContainer = () => {
	//TODO: Replace with movies fetched via API
	const movies = [
		{
			title: "Movie Title",
			year: "2024",
			rated: "PG",
			duration: "2h 21m",
			genres: ["Drama", "Horror", "Comedy"],
			director: "John Doe",
			cast: ["John Smith", "Jane Doe"],
			rating: "4/5",
		},
		{
			title: "Movie Title 2",
			year: "2024",
			rated: "PG",
			duration: "2h 21m",
			genres: ["Drama", "Horror", "Comedy"],
			director: "John Doe",
			cast: ["John Smith", "Jane Doe"],
			rating: "4/5",
		},
		{
			title: "Movie Title 3",
			year: "2024",
			rated: "PG",
			duration: "2h 21m",
			genres: ["Drama", "Horror", "Comedy"],
			director: "John Doe",
			cast: ["John Smith", "Jane Doe"],
			rating: "4/5",
		},
		{
			title: "Movie Title 4",
			year: "2024",
			rated: "PG",
			duration: "2h 21m",
			genres: ["Drama", "Horror", "Comedy"],
			director: "John Doe",
			cast: ["John Smith", "Jane Doe"],
			rating: "4/5",
		},
		{
			title: "Movie Title 5",
			year: "2024",
			rated: "PG",
			duration: "2h 21m",
			genres: ["Drama", "Horror", "Comedy"],
			director: "John Doe",
			cast: ["John Smith", "Jane Doe"],
			rating: "4/5",
		},
	];

	function handleClick(pageIndex) {
		console.log("Page Index from callback: " + pageIndex);
	}

	return (
		<>
			<MovieCarousel movies={movies} />

			<Pagination items={movies} onClick={handleClick} />
		</>
	);
};

export default HomePageContainer;
