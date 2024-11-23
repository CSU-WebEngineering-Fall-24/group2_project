import React from "react";
import MovieCard from "./MovieCard";
import { chunkArray } from "../utilities/utils";

const MovieCarousel = ({ movies, carouselId }) => {
  const movieChunks = chunkArray(movies, 3);

  return (
    <div
      id={carouselId}
      className="carousel slide"
      data-bs-ride="carousel"
      style={{ padding: 20 }}
    >
      <div className="carousel-inner">
        {movieChunks.map((chunk, index) => (
          <div
            key={index}
            className={`carousel-item ${index === 0 ? "active" : ""}`}
          >
            <div className="d-flex justify-content-center cards-wrapper">
              {chunk.map((movie, id) => (
                <MovieCard key={id} movie={movie} />
              ))}
            </div>
          </div>
        ))}
      </div>
      <button
        className="carousel-control-prev"
        type="button"
        data-bs-target={`#${carouselId}`}
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next"
        type="button"
        data-bs-target={`#${carouselId}`}
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
};

export default MovieCarousel;
