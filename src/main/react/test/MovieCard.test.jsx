import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { useNavigate } from "react-router-dom";
import MovieCard from "../components/MovieCard";
import { fetchMovieDetails } from "../services/apiService";

jest.mock("../services/apiService");
jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useNavigate: jest.fn(),
}));

/**
 * Helper function to simulate the 'more' mutton click
 */
const simulateButtonClick = () => {
  const moreButton = screen.getByRole("button", { name: /more/i });
  fireEvent.click(moreButton);
};

describe("MovieCard", () => {
  const mockNavigate = jest.fn();
  const mockMovie = {
    imdbID: "tt1234567",
    title: "Test Movie",
    poster: "http://example.com/poster.jpg",
    type: "movie",
    year: "2021",
  };

  beforeEach(() => {
    jest.clearAllMocks();
    useNavigate.mockReturnValue(mockNavigate);
  });

  test("calls fetchMovieDetails and navigates on More button click", async () => {
    const mockMovieDetails = { title: "Detailed Movie Info", year: "2021" };
    fetchMovieDetails.mockResolvedValue(mockMovieDetails); // mock API response

    render(<MovieCard movie={mockMovie} />);
    simulateButtonClick();

    await waitFor(() => {
      expect(fetchMovieDetails).toHaveBeenCalledWith("tt1234567");
    });

    expect(mockNavigate).toHaveBeenCalledWith("/details", {
      state: { movie: mockMovieDetails },
    });
  });

  test("logs error when fetchMovieDetails fails", async () => {
    const consoleErrorSpy = jest.spyOn(console, "error").mockImplementation();
    fetchMovieDetails.mockRejectedValue(new Error("API Error")); // Mock API error

    render(<MovieCard movie={mockMovie} />);
    simulateButtonClick();

    await waitFor(() => {
      expect(consoleErrorSpy).toHaveBeenCalledWith(
        "Error fetching movie details:",
        expect.any(Error)
      );
    });

    consoleErrorSpy.mockRestore();
  });
});
