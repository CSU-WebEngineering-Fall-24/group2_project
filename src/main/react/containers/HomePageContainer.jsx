import axios from "axios";
import React, { useEffect, useState } from "react";
import MovieCarousel from "../components/MovieCarousel";
import Pagination from "../components/Pagination";
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
      <div className="d-grid gap-3">
        <div className="container text-center">
          <SearchBar></SearchBar>
        </div>

        <h1 className="ps-4">2024 New Releases</h1>

        <div className="container text-center">
          <MovieCarousel movies={movies} carouselId="carousel1" />
        </div>

        <h1 className="ps-4">Last Year's Movies</h1>

        <div className="container text-center">
          <MovieCarousel movies={movies} carouselId="carousel2" />
        </div>
      </div>

      <Pagination items={movies} onClick={handleClick} />
    </>
  );
};

export default HomePageContainer;
