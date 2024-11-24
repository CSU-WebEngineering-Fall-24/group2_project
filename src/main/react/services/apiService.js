import axios from "axios";

/**
 * Fetches movies based on a search term, optional year, and optional category.
 *
 * @param {string} search The search term.
 * @param {string} year The release year to filter (optional).
 * @param {string} category The category/type to filter (optional).
 * @returns The API response data.
 */
export const fetchMovies = async (search, year, category) => {
  const url =
    `/movie/search?search=${search}` +
    (year ? `&years=${year}` : "") +
    (category ? `&types=${category}` : "");

  const { data } = await axios.get(url);
  return data;
};

/**
 * Fetches detailed information about a movie by IMDb ID.
 *
 * @param {string} id The id of the movie.
 * @returns The response data.
 */
export const fetchMovieDetails = async (id) => {
  const url = `/movie/${id}`;
  const { data } = await axios.get(url);
  return data;
};
