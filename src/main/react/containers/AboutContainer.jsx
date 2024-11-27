import React, { useEffect, useState } from "react";
import Member from "../components/Member";
import membersData from "../../resources/members.json";

const AboutContainer = () => {
	const [members, setMembers] = useState([]);
	const columnCount = 2;
	const itemCount = Math.ceil(members.length / columnCount);

	useEffect(() => {
		setMembers(membersData);
	}, []);

	return (
		<>
			<div className="projInfo">Dr. Canedo instructed the students of Web Engineering Tehnologies (CPSC6175) that we would be completing  a group project that consisted of developing a full stack application for a client. The client is asking for a full stack website that uses java and react and showcases an API of the groups choosing. Dr. Canedo assigned the following individuals to group 2:  Alexis Davidson, Nam Luu, Justin Owens and Michael Sretenovic. As a group we chose the TV and Movie API, where the client would be able to search for their favorite shows and movies. Below you will find out information about each group member.</div>
			<div className="memberBios">
				{Array.from({ length: columnCount }, (_, index) => {
					return (
						<div className="row">
							{members.slice(index * itemCount, index * itemCount + itemCount).map((member) => {
								return <div key={member.id} className="memberCard col-md-6"><Member key={member.id} member={member} /></div>
							})}
						</div>
					)
				})}
			</div>
		</>
	);
};

export default AboutContainer;
