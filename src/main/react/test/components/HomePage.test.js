import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import "@testing-library/jest-dom";
import { MemoryRouter } from "react-router-dom";
import HomePageContainer from "../../containers/HomePageContainer";

const mockNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useNavigate: () => mockNavigate,
}));

jest.mock("../../services/apiService", () => ({
  fetchMovies: jest.fn(() => Promise.resolve([])),
}));

describe("Home Page Container", () => {
  it("renders movie carousels in page", () => {
    render(
      <MemoryRouter>
        <HomePageContainer />
      </MemoryRouter>
    );

    const carousel1 = screen.getByLabelText(/carousel carousel1/i);
    const carousel2 = screen.getByLabelText(/carousel carousel2/i);

    expect(carousel1).toBeInTheDocument();
    expect(carousel1.id).toBe("carousel1");
    expect(carousel2).toBeInTheDocument();
    expect(carousel2.id).toBe("carousel2");
  });

  it("renders search bar in page", () => {
    render(
      <MemoryRouter>
        <HomePageContainer />
      </MemoryRouter>
    );

    const input = screen.getByPlaceholderText("Search...");
    const yearInput = screen.getByPlaceholderText("Year");
    const genreInput = screen.getByDisplayValue("All");

    expect(input).toBeInTheDocument();
    expect(yearInput).toBeInTheDocument();
    expect(genreInput).toBeInTheDocument();
  });

  it("headers are present", async () => {
    render(
      <MemoryRouter>
        <HomePageContainer />
      </MemoryRouter>
    );

    const header1 = screen.getByText("2024 Releases");
    const header2 = screen.getByText("2023 Releases");
    
    expect(header1).toBeInTheDocument();
    expect(header2).toBeInTheDocument();
  });
});
