import React from "react";

const Movie = (props) => {
  const { movie } = props;

  return (
    <div class="card movie-card w-50">
      <div class="card-header d-flex justify-content-between align-items-center movie-header">
        <h5 class="card-title mb-0 movie-title">{movie.title}</h5>
        <span class="movie-rating">4/5 ⭐</span>
      </div>
      <div class="card-body movie-details">
        <p class="card-text movie-info">
          {movie.year} • {movie.rated} • {movie.duration}
        </p>
        <p class="card-text movie-genres">
          <strong>{movie.genres.join(" • ")}</strong>
        </p>
        <p class="card-text movie-director">
          <strong>Directed By:</strong> {movie.director}
        </p>
        <p class="card-text movie-cast">
          <strong>Starring:</strong>
        </p>
        <ul>
          {movie.cast.map((actor, index) => (
            <li key={index}>{actor}</li>
          ))}
        </ul>
      </div>
      <div class="card-footer text-end movie-button-container">
        <a href="#" class="btn btn-primary movie-more-button">
          More
        </a>
      </div>
    </div>
  );
};

export default Movie;