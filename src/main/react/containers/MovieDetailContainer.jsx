import React, { useEffect, useState } from "react";
import SearchBar from "../components/SearchBar";

const MovieDetailContainer = () => {
    const movie = {
        title: "Lackadaisy",
		year: "2023",
		rated: "TV-PG",
		duration: "27m",
		genres: ["Animation", "Short", "Crime"],
		director: ["Tracy Butler", "Fable Siegal"],
        writer: ["Tracy Butler", "Dave Capdevielle", "Fable Siegal"],
        plot: "Lackadaisy (aka Lackadaisy Cats) is based on the webcomic created by American artist, Tracy J. Butler. Set in a Prohibition-era St. Louis with a population of anthropomorphic cats.",
        awards: "N/A",
        poster: "https://m.media-amazon.com/images/M/MV5BZTdlOGQ1OTEtYjNmNS00YjI0LWI2MWEtNDVlZmU3N2U5ZDc2XkEyXkFqcGc@._V1_SX300.jpg",
		cast: ["Michael Kovach", "Belsheber Rusape", "Lisa Reimold"],
		rating: "4/5"
    };
    return (
		<>
			<div className="d-grid gap-3">
			    <div className="container text-center">
  				    <SearchBar></SearchBar>
			    </div>
			    <div className="px-4">
  			        <h1>{movie.title}</h1>
                    <div>{movie.year} • {movie.rated} • {movie.duration}</div>
			    </div>

			    <div className="container text-center">
                    <img src={movie.poster} alt={movie.poster || "No Image Available"} />
			    </div>
			
                <div className="px-4">
                    <h1>Summary</h1>
                    <p>{movie.plot}</p>
                    {movie.genres.map((genre, index) => (
                        <a className="btn btn-primary" href="#" role="button" key={index}>{genre}</a>
                    ))}
                </div>

                <div className="px-4">
                    <h1>Awards</h1>
                    <div>{movie.awards}</div>
                </div>

                <div className="px-4">
                    <div>
                        <strong>Directed by: </strong>{movie.director.join(", ")}
                    </div>
                    <div>
                        <strong>Written by: </strong>{movie.writer.join(", ")}
                    </div>
                </div>

                <div className="px-4">
                    <h1>Cast</h1>
                    <ul>
                        {movie.cast.map((actor, index) => (
                            <li key={index}>{actor}</li>
                        ))}
                    </ul>
                </div>
			</div>
		</>
	);
};

export default MovieDetailContainer;