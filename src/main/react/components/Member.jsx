import React from "react";

const Member = (props) => {
	const { member } = props;
	return (
		<div className="memberCard">
			<div className="memberHeader">{member.name}</div>
			<div className="memberBio">
				{member.shortBioImageUrl &&
					<img src={member.shortBioImageUrl} className="memberBioImageSm" />
				}
				<div className="memberBioText">{member.shortBio}</div>
			</div>
		</div>
	);
};

export default Member;
