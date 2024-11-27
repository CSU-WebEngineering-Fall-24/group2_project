import React from "react";
import { useNavigate } from "react-router-dom";
import { toTitleCase } from "../utilities/utils";
import { fetchMovieDetails } from "../services/apiService";

const placeholderImage =
  "https://dummyimage.com/150X200/ababab/000000.jpg&text=No+Image+Found";

const MovieCard = ({ movie }) => {
  const { title, poster, type, year } = movie;
  const navigate = useNavigate();

  /**
   * Handles the "More" button click action.
   * Calls the API to fetch movie details and passes the response data to the movie details view.
   */
  const handleClick = async () => {
    try {
      const data = await fetchMovieDetails(movie.imdbID);

      navigate("/details", {
        state: {
          movie: data,
        },
      });
    } catch (error) {
      console.error("Error fetching movie details:", error);
    }
  };

  /**
   * Handles when an image throws an error.
   * @param event The event to check for error.
   */
  const handleImageError = (event) => {
    event.target.onerror = null;
    event.target.src = placeholderImage;
  };

  return (
    <div className="card movie-card w-50">
      <div className="card-header d-flex justify-content-between align-items-center movie-header">
        <h5 className="card-title mb-0 movie-title">{title}</h5>
      </div>
      <div className="card-body movie-details">
        <img
          className="movie-poster"
          src={poster || placeholderImage}
          alt={`${title} Poster`}
          onError={handleImageError}
        />
      </div>
      <div className="card-footer d-flex justify-content-between align-items-center movie-button-container">
        <p className="card-text movie-info mb-0">
          {toTitleCase(type)} • {year}
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
