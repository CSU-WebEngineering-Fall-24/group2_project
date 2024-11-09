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
		<nav className="navbar navbar-expand-lg bg-body-tertiary">
			<div className="container-fluid">
				<a className="navbar-brand">Sretenovic</a>
				<button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
					<span className="navbar-toggler-icon"></span>
				</button>
				<div className="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div className="navbar-nav">
						{pageURLs.map((linkInfo, index) => (
							<li key={index} className="nav-item">
								<NavLink
									to={linkInfo.url}
									className={({ isActive }) =>
										"nav-link" + `${isActive ? " active" : ""}`
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