import React from 'react';
import ReactDom from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import AppContainer from './containers/AppContainer';
import AboutContainer from './containers/AboutContainer';
import SearchContainer from './containers/SearchContainer';
import HomePageContainer from './containers/HomePageContainer';

const router = createBrowserRouter([
	{
		path: "/",
		element: <AppContainer />,
		children: [
			{
				index: true,
				element: <HomePageContainer />
			},
			,
			{
				path: "about",
				element: <AboutContainer />
			},
			{
				path: "search",
				element: <SearchContainer />
			}
		]
	}
]);

ReactDom.createRoot(document.getElementById('root')).render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
);
