import React, { useEffect, useState } from "react";
import MovieCarousel from "../components/MovieCarousel";
import SearchBar from "../components/SearchBar";
import { fetchMovies } from "../services/apiService";
import { getRandomSearchTerm } from "../utilities/utils";

const HomePageContainer = () => {
  const [movies2024, setMovies2024] = useState(null);
  const [movies2023, setMovies2023] = useState(null);

  useEffect(() => {
    const fetchMovieData = async () => {
      try {
        // 2024 movies with a random search term
        const result2024 = await fetchMovies(
          getRandomSearchTerm(),
          "2024",
          "Movie"
        );
        setMovies2024(result2024);

        // 2023 movies with a different random search term
        const result2023 = await fetchMovies(
          getRandomSearchTerm(),
          "2023",
          "Movie"
        );

        setMovies2023(result2023);
      } catch (error) {
        console.error("Error fetching movies:", error);
      }
    };

    fetchMovieData();
  }, []);

  return (
    <>
      <div className="d-grid gap-3">
        <div className="container text-center">
          <SearchBar></SearchBar>
        </div>

        <h1 className="ps-4">2024 Releases</h1>

        <div className="container text-center">
          <MovieCarousel movies={movies2024} carouselId="carousel1" />
        </div>

        <h1 className="ps-4">2023 Releases</h1>

        <div className="container text-center">
          <MovieCarousel movies={movies2023} carouselId="carousel2" />
        </div>
      </div>
    </>
  );
};

export default HomePageContainer;
