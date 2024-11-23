import axios from "axios";
import React, { useEffect, useState } from "react";
import MovieCarousel from "../components/MovieCarousel";
import SearchBar from "../components/SearchBar";

const HomePageContainer = () => {
  const [movieList, setMovieList] = useState(null);
  useEffect(() => {
    const fetchMovies = async () => {
      const searches = [
        "bear",
        "alien",
        "king",
        "dragon",
        "godzilla",
        "duck",
        "barbie",
        "transformers",
        "treasure",
        "dinosaur",
      ];

      const index = Math.floor(
        Math.random(searches.length - 1) * searches.length
      );
      const url = "/movie/search?search=" + searches[index];

      const result = await axios
        .get(url)
        .then((response) => {
          console.log("Movies Returned:");
          console.log(response.data);
          setMovieList(response.data);
          console.log(movieList);
        })
        .catch((error) => {
          console.error(error);
        });
    };

    fetchMovies();
  }, []);

  return (
    <>
      <div className="d-grid gap-3">
        <div className="container text-center">
          <SearchBar></SearchBar>
        </div>

        <h1 className="ps-4">2024 New Releases</h1>

        <div className="container text-center">
          <MovieCarousel movies={movieList} carouselId="carousel1" />
        </div>

        <h1 className="ps-4">Last Year's Movies</h1>

        <div className="container text-center">
          <MovieCarousel movies={movieList} carouselId="carousel2" />
        </div>
      </div>
    </>
  );
};

export default HomePageContainer;
