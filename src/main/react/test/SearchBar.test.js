/** @jest-environment jsdom */

import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom";
import { MemoryRouter } from "react-router-dom";
import SearchBar from "../components/SearchBar";

const mockNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useNavigate: () => mockNavigate,
}));

jest.mock("../services/apiService", () => ({
  fetchMovies: jest.fn(() => Promise.resolve([])),
}));

describe("SearchBar Component", () => {
  it("renders input fields", () => {
    render(
      <MemoryRouter>
        <SearchBar />
      </MemoryRouter>
    );

    const input = screen.getByPlaceholderText("Search...");
    const yearInput = screen.getByPlaceholderText("Year");
    const genreInput = screen.getByDisplayValue("All");

    expect(input).toBeInTheDocument();
    expect(yearInput).toBeInTheDocument();
    expect(genreInput).toBeInTheDocument();
  });

  it("updates input values when typing", () => {
    render(
      <MemoryRouter>
        <SearchBar />
      </MemoryRouter>
    );

    const input = screen.getByPlaceholderText("Search...");
    const yearInput = screen.getByPlaceholderText("Year");

    fireEvent.change(input, { target: { value: "React" } });
    fireEvent.change(yearInput, { target: { value: "2024" } });

    expect(input.value).toBe("React");
    expect(yearInput.value).toBe("2024");
  });

  it("navigates to search results on button click", async () => {
    render(
      <MemoryRouter>
        <SearchBar />
      </MemoryRouter>
    );

    const input = screen.getByPlaceholderText("Search...");
    const yearInput = screen.getByPlaceholderText("Year");
    const searchButton = screen.getByRole("button");

    fireEvent.change(input, { target: { value: "Test" } });
    fireEvent.change(yearInput, { target: { value: "2024" } });

    await fireEvent.click(searchButton);

    expect(mockNavigate).toHaveBeenCalledWith("/search", {
      state: {
        searchInput: "Test",
        year: "2024",
        type: "All",
        results: expect.anything(),
      },
    });
  });
});
