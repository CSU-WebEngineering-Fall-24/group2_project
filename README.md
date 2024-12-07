# CPSC 6175 Group Project - Group 2

## Group Members

- Davidson, Alexis
- Luu, Nam
- Owens, Justin
- Sretenović, Michael

## About The Project

### Summary

The DLOS web application allows users to search for movies and TV shows by title and view detailed information, including cast, crew, descriptions, and posters. The app leverages the [OMDB API](https://www.omdbapi.com/), a RESTful web service that provides movie and TV series information. Key data points fetched from the API include:

- Titles
- Synopses
- Release years
- Cast and crew details
- Ratings (e.g., IMDb)
- Movie posters

### Features

- Search for movies and TV shows by keyword(s), with optional filters for year and type (movies, TV series, or episodes).
- View detailed information for selected search results, including title, description, cast, and more.
- Navigate between the "Home" and "About" page in a navigation bar.
- View details about the developers who worked on the project on the "About" page.
- Cache movie results to enhance performance and provide a low-latency experience.
- Responsive design built with reusable, modular components, integrating React for the front end and Spring Boot for the backend.

## Building and Running

### Prerequisites

Ensure you have the following installed:

- Node.js and npm
- Java 8+ (for backend Spring application)

### Steps

1. Navigate to `/src/main/react` and run the command `npm i` to install the front-end dependencies.
2. Build the front-end by running `npm run build`.
3. Add a file named `api.key` to the resources folder (`src/main/resources/api.key`) with your personal OMDB API key. See _Connecting to the OMDB API_ below for details on obtaining an API key.
4. Start the application by running the `MovieApplication.java` file located in `src/main/java/com/dlos/movie/`.
5. Navigate to `http://127.0.0.1:8080/` in the browser to access the application.

## Connecting to the OMDB API

1. Request a free API key from the OMDB API website: https://www.omdbapi.com/apikey.aspx
2. Confirm your API key by clicking the link provided in the confirmation email.
3. Add the API key to the `api.key` file in the resources directory. Ensure it is the only text in the file.

## Resources

[Project Demo](https://www.youtube.com/watch?v=Mp2AR3xEg9c) - A walkthrough of the app's features and functionality.

[Figma Design Files](https://www.figma.com/design/uCHTJEnGOGQiwiBWUTz4zW/Group-2-Project?node-id=0-1&node-type=canvas&t=UkhrbW7OYnJvg1ep-0) - Wireframes and mockups used during the design phase.

[GitHub Repository](https://github.com/CSU-WebEngineering-Fall-24/group2_project) - The source code and project files.

[GitHub Issue Tracker](https://github.com/orgs/CSU-WebEngineering-Fall-24/projects/2) - Active tracking of issues, bugs, and feature requests.

[OMDB API](https://www.omdbapi.com/)
