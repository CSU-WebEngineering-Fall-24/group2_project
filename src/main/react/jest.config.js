const { TestEnvironment } = require('jest-environment-jsdom');

/** @type { import('@types/jest').InitialOptionsTsJest } */
module.exports = {
	TestEnvironment: "node",
	testPathIgnorePatters: ["node_modules", "build", "dist"]
};
