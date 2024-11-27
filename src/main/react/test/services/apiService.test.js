import axios from "axios";
import MockAdapter from "axios-mock-adapter";
import { fetchMovies, fetchMovieDetails } from "../../services/apiService";

describe("API Service", () => {
  let mock;

  beforeEach(() => {
    mock = new MockAdapter(axios);
  });

  afterEach(() => {
    mock.restore();
  });

  describe("fetchMovies", () => {
    it("should fetch movies based on search term", async () => {
      // Arrange
      const search = "action";
      const url = getMockUrl(search, null, null);
      const mockResponse = { title: "Action Title" };
      mock.onGet(url).reply(200, mockResponse);

      // Act
      const data = await fetchMovies(search, null, null);

      // Assert
      expect(data).toEqual(mockResponse);
      expect(mock.history.get.length).toBe(1);
      expect(mock.history.get[0].url).toBe(url);
    });

    it("should fetch movies with search term and year", async () => {
      // Arrange
      const search = "comedy";
      const year = "2022";
      const url = getMockUrl(search, year, null);
      const mockResponse = {
        results: [{ title: "Comedy Title", year: year }],
      };

      mock.onGet(url).reply(200, mockResponse);

      // Act
      const data = await fetchMovies(search, year, null);

      // Assert
      expect(data).toEqual(mockResponse);
      expect(mock.history.get.length).toBe(1);
      expect(mock.history.get[0].url).toBe(url);
    });

    it("should fetch movies with search term, year, and category", async () => {
      // Arrange
      const search = "thriller";
      const year = "2023";
      const category = "Movie";
      const url = getMockUrl(search, year, category);
      const mockResponse = {
        results: [{ title: "Comedy Title", year: year, category: category }],
      };
      mock.onGet(url).reply(200, mockResponse);

      // Act
      const data = await fetchMovies(search, year, category);

      // Assert
      expect(data).toEqual(mockResponse);
      expect(mock.history.get.length).toBe(1);
      expect(mock.history.get[0].url).toBe(url);
    });

    it("should throw an error if API call fails", async () => {
      // Arrange
      const search = "drama";
      const url = getMockUrl(search, null, null);
      mock.onGet(url).reply(500);

      // Act / Assert
      await expect(fetchMovies(search, null, null)).rejects.toThrow();
    });
  });

  describe("fetchMovieDetails", () => {
    it("should fetch movie details by IMDb ID", async () => {
      // Arrange
      const id = "foo";
      const mockResponse = { title: "Test Movie", year: "2022" };

      const url = `/movie/${id}`;
      mock.onGet(url).reply(200, mockResponse);

      // Act
      const data = await fetchMovieDetails(id);

      // Assert
      expect(data).toEqual(mockResponse);
      expect(mock.history.get.length).toBe(1);
      expect(mock.history.get[0].url).toBe(url);
    });

    it("should throw an error if API call fails", async () => {
      // Arrange
      const id = "foo";

      const url = `/movie/${id}`;
      mock.onGet(url).reply(404);

      // Act/Assert
      await expect(fetchMovieDetails(id)).rejects.toThrow();
    });
  });
});

/**
 *
 * @param search The search term.
 * @param year The year.
 * @param category The category (type).
 * @returns The url string with provided parameters.
 */
function getMockUrl(search, year, category) {
  let url = `/movie/search?search=${search}`;

  if (year) {
    url += `&years=${year}`;
  }

  if (category) {
    url += `&types=${category}`;
  }

  return url;
}
