/** @type { import('@types/jest').InitialOptionsTsJest } */

const { TestEnvironment } = require('jest-environment-jsdom');

module.exports = {
	testEnvironment: "jsdom",
	testPathIgnorePatterns: ["node_modules", "build", "dist"],
	collectCoverage: true,
	collectCoverageFrom: ["src/**/*.{js,jsx}"],
	coverageDirectory: "coverage",
	setupFilesAfterEnv: ["<rootDir>/jest.setup.js"],
};
