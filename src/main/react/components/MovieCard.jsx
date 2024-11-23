import React from "react";
import { useNavigate } from "react-router-dom";
import { toTitleCase } from "../utilities/utils";

const MovieCard = (props) => {
  const { movie } = props;
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/details", {
      state: {
        movie,
      },
    });
  };

  return (
    <div className="card movie-card w-50">
      <div className="card-header d-flex justify-content-between align-items-center movie-header">
        <h5 className="card-title mb-0 movie-title">{movie.title}</h5>
      </div>
      <div className="card-body movie-details">
        <img className="movie-poster" src={movie.poster} />
      </div>
      <div className="card-footer d-flex justify-content-between align-items-center movie-button-container">
        <p className="card-text movie-info mb-0">
          {toTitleCase(movie.type)} • {movie.year}{" "}
        </p>
        <button
          className="btn btn-primary movie-more-button"
          onClick={handleClick}
        >
          More
        </button>
      </div>
    </div>
  );
};

export default MovieCard;
