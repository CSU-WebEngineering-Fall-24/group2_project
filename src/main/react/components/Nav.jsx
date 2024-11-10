import React from "react";
import { NavLink } from "react-router-dom";

const Nav = (props) => {
	const { locationURL } = props;
	const pageURLs = [
		{
			url: "/",
			label: "Home Page",
		},
		{
			url: "/about",
			label: "About",
		}
	]
	return (
		<nav className="navbar navbar-expand-sm navbar-bg-custom">
			<div className="container-fluid">
				<a className="navbar-brand navbar-color-custom">DLOS Movie Info</a>
				<button className="navbar-toggler custom-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
					<span className="navbar-toggler-icon"></span>
				</button>
				<div className="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div className="navbar-nav ms-auto">
						{pageURLs.map((linkInfo, index) => (
							<li key={index} className="nav-item navbar-color-custom">
								<NavLink
									to={linkInfo.url}
									className={({ isActive }) =>
										"navbar-color-custom nav-link-custom" + `${isActive ? " custom-active" : ""}`
									}>
									{linkInfo.label}
								</NavLink>
							</li>
						))}
					</div>
				</div>
			</div>
		</nav>
	);
};

export default Nav;