import {
  chunkArray,
  toTitleCase,
  getRandomSearchTerm,
} from "../../utilities/utils";

describe("Utilities", () => {
  describe("chunkArray", () => {
    it("should split an array into chunks of the specified size", () => {
      // Arrange
      const input = [1, 2, 3, 4, 5, 6];
      const chunkSize = 2;

      // Act
      const result = chunkArray(input, chunkSize);

      // Assert
      expect(result).toEqual([
        [1, 2],
        [3, 4],
        [5, 6],
      ]);
    });

    it("should handle arrays that are not divisible by the chunk size", () => {
      // Arrange
      const input = [1, 2, 3, 4, 5];
      const chunkSize = 3;

      // Act
      const result = chunkArray(input, chunkSize);

      // Assert
      expect(result).toEqual([
        [1, 2, 3],
        [4, 5],
      ]);
    });

    it("should return an empty array if the input array is empty", () => {
      // Arrange
      const input = [];
      const chunkSize = 2;

      // Act
      const result = chunkArray(input, chunkSize);

      // Assert
      expect(result).toEqual([]);
    });

    it("should return an empty array if the chunk size is 0 or less", () => {
      // Arrange
      const input = [1, 2, 3];
      const chunkSize = 0;

      // Act
      const result = chunkArray(input, chunkSize);

      // Assert
      expect(result).toEqual([]);
    });

    it("should return the original array in a single chunk if chunk size is larger than the array", () => {
      // Arrange
      const input = [1, 2, 3];
      const chunkSize = 5;

      // Act
      const result = chunkArray(input, chunkSize);

      // Assert
      expect(result).toEqual([[1, 2, 3]]);
    });
  });

  describe("toTitleCase", () => {
    it("should convert a lowercase string to title case", () => {
      // Arrange
      const input = "hello world";

      // Act
      const result = toTitleCase(input);

      // Assert
      expect(result).toBe("Hello World");
    });

    it("should convert a mixed-case string to title case", () => {
      // Arrange
      const input = "hElLo WoRLd";

      // Act
      const result = toTitleCase(input);

      // Assert
      expect(result).toBe("Hello World");
    });

    it("should return an empty string if the input is an empty string", () => {
      // Arrange
      const input = "";

      // Act
      const result = toTitleCase(input);

      // Assert
      expect(result).toBe("");
    });

    it("should handle single-word strings", () => {
      // Arrange
      const input = "hello";

      // Act
      const result = toTitleCase(input);

      // Assert
      expect(result).toBe("Hello");
    });
  });

  describe("getRandomSearchTerm", () => {
    it("should return a string", () => {
      // Arrange / Act
      const result = getRandomSearchTerm();

      // Assert
      expect(typeof result).toBe("string");
    });

    it("should return a term from the predefined list", () => {
      // Arrange
      const predefinedTerms = [
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

      // Act
      const result = getRandomSearchTerm();

      // Assert
      expect(predefinedTerms).toContain(result);
    });

    it("should return random results across multiple calls", () => {
      // Arrange / Act
      const results = new Set();
      for (let i = 0; i < 5; i++) {
        results.add(getRandomSearchTerm());
      }

      // Assert
      expect(results.size).toBeGreaterThan(1);
    });
  });
});
