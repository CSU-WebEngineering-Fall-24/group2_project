import React, { useEffect, useState } from "react";
import SearchBar from "../components/SearchBar";
import { useLocation } from "react-router-dom";
import { toTitleCase } from "../utilities/utils";

const MovieDetailContainer = () => {
	const location = useLocation();
	const movie = location.state?.movie;

	return (
		<>
			<div className="d-grid gap-3">
				<div className="container text-center">
					<SearchBar></SearchBar>
				</div>
				<div className="px-4">
					<h1>{movie?.title}</h1>
					<div className="movie-metadata">
						{toTitleCase(movie?.type || "")} • {movie.year} • {movie.rated} •
						{movie.runtime} • {movie.imdbRating} / 10
					</div>
				</div>

				<div className="container text-center">
					<img
						src={movie?.poster}
						alt={movie?.poster || "No Image Available"}
					/>
				</div>

				<div className="px-4">
					<h1>Summary</h1>
					<p>{movie?.plot}</p>
					{movie.genres?.map((genre, index) => (
						<a className="btn btn-primary" href="#" role="button" key={index}>
							{genre}
						</a>
					))}
				</div>

				<div className="px-4">
					<h1>Awards</h1>
					<div>{movie?.awards}</div>
				</div>

				<div className="px-4">
					<div>
						<strong>Directed by: </strong>
						{movie.directors?.join(", ")}
					</div>
					<div>
						<strong>Written by: </strong>
						{movie.writers?.join(", ")}
					</div>
				</div>

				<div className="px-4">
					<h1>Cast</h1>
					<ul>
						{movie.actors?.map((actor, index) => (
							<li key={index}>{actor}</li>
						))}
					</ul>
				</div>
			</div>
		</>
	);
};

export default MovieDetailContainer;
