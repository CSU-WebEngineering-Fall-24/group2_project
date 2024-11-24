/**
 * Splits an array into smaller chunks of a specified size.
 *
 * @param array The array to be divided into chunks.
 * @param size The maximum size of each chunk.
 * @returns The new array with "chunks" of the original array.
 */
export const chunkArray = (array, size) =>
  array?.reduce((chunks, _, index) => {
    if (index % size === 0) {
      chunks.push(array.slice(index, index + size));
    }
    return chunks;
  }, []);

/**
 * Converts a string to title case (capitalize the first letter of each word).
 * SOURCE: https://www.geeksforgeeks.org/convert-string-to-title-case-in-javascript/
 * @param {string} input The input string to convert
 * @returns The title-cased string.
 */
export const toTitleCase = (input) => {
  return input
    .toLowerCase()
    .split(" ")
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
    .join(" ");
};

/**
 * Gets a random search term from a list of predefined options.
 * @returns The random search term string.
 */
export const getRandomSearchTerm = () => {
  const searches = [
    "love",
    "adventure",
    "mystery",
    "hero",
    "power",
    "dark",
    "quest",
    "ghost",
    "legend",
    "rise",
    "war",
    "family",
    "lost",
    "secret",
    "game",
    "dream",
    "revenge",
    "escape",
    "monster",
    "future",
  ];

  const randomIndex = Math.floor(Math.random() * searches.length);
  return searches[randomIndex];
};
