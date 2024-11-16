/**
 * Splits an array into smaller chunks of a specified size.
 *
 * @param array The array to be divided into chunks.
 * @param size The maximum size of each chunk.
 * @returns The new array with "chunks" of the original array.
 */
export const chunkArray = (array, size) =>
  array.reduce((chunks, _, index) => {
    if (index % size === 0) {
      chunks.push(array.slice(index, index + size));
    }
    return chunks;
  }, []);
