import React from "react";
import { render, screen } from "@testing-library/react";
import MovieCarousel from "../components/MovieCarousel";
import { chunkArray } from "../utilities/utils";

jest.mock("../utilities/utils", () => ({
  chunkArray: jest.fn(),
}));

jest.mock("../components/MovieCard", () => ({ movie }) => (
  <div data-testid="movie-card">{movie.title}</div>
));

describe("MovieCarousel", () => {
  const mockMovies = [
    { title: "Movie 1", id: "1" },
    { title: "Movie 2", id: "2" },
    { title: "Movie 3", id: "3" },
    { title: "Movie 4", id: "4" },
    { title: "Movie 5", id: "5" },
  ];

  beforeEach(() => {
    jest.clearAllMocks();
  });

  test("renders the carousel with correct ID", () => {
    chunkArray.mockReturnValue([[mockMovies[0], mockMovies[1], mockMovies[2]]]);

    render(<MovieCarousel movies={mockMovies} carouselId="test-carousel" />);

    const carousel = screen.getByLabelText(/carousel test-carousel/i);
    expect(carousel).toBeInTheDocument();
    expect(carousel.id).toBe("test-carousel");
  });

  test("chunks movies correctly and renders them", () => {
    chunkArray.mockReturnValue([
      [mockMovies[0], mockMovies[1], mockMovies[2]],
      [mockMovies[3], mockMovies[4]],
    ]);

    render(<MovieCarousel movies={mockMovies} carouselId="test-carousel" />);

    // Verify chunkArray is called with correct arguments
    expect(chunkArray).toHaveBeenCalledWith(mockMovies, 3);

    // Verify movie cards are rendered
    const movieCards = screen.getAllByTestId("movie-card");
    expect(movieCards).toHaveLength(5);
    expect(movieCards[0]).toHaveTextContent("Movie 1");
    expect(movieCards[4]).toHaveTextContent("Movie 5");
  });

  test("renders navigation buttons", () => {
    chunkArray.mockReturnValue([[mockMovies[0], mockMovies[1], mockMovies[2]]]);

    render(<MovieCarousel movies={mockMovies} carouselId="test-carousel" />);

    const prevButton = screen.getByRole("button", { name: /previous/i });
    const nextButton = screen.getByRole("button", { name: /next/i });

    expect(prevButton).toBeInTheDocument();
    expect(nextButton).toBeInTheDocument();
  });

  test("sets the first chunk as active", () => {
    chunkArray.mockReturnValue([
      [mockMovies[0], mockMovies[1], mockMovies[2]],
      [mockMovies[3], mockMovies[4]],
    ]);

    render(<MovieCarousel movies={mockMovies} carouselId="test-carousel" />);

    const activeItem = screen.getByRole("listitem", { current: true });
    expect(activeItem).toBeInTheDocument();
    expect(activeItem).toHaveClass("active");
  });
});
