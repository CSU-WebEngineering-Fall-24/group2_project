import axios from "axios";
import React, { useEffect, useState } from "react";
import MovieCarousel from "../components/MovieCarousel";
import SearchBar from "../components/SearchBar";

const HomePageContainer = () => {
  const [movies2024, setMovies2024] = useState(null);
  const [movies2023, setMovies2023] = useState(null);

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

      const getRandomSearchTerm = () => {
        const randomIndex = Math.floor(Math.random() * searches.length);
        return searches[randomIndex];
      };

      const url = "/movie/search?search=";

      try {
        // Fetch 2024 movies with a random search term
        const randomSearch2024 = getRandomSearchTerm();
        const url2024 = `${url}${randomSearch2024}&years=2024`;
        const result2024 = await axios.get(url2024);

        setMovies2024(result2024.data);

        // Fetch 2023 movies with a different random search term
        const randomSearch2023 = getRandomSearchTerm();
        const url2023 = `${url}${randomSearch2023}&years=2023`;
        const result2023 = await axios.get(url2023);

        setMovies2023(result2023.data);
      } catch (error) {
        console.error("Error fetching movies:", error);
      }
    };

    fetchMovies();
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
