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
			<div className="projInfo">Project information goes here.</div>
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
