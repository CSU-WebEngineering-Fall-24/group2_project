import React from "react";

const Member = (props) => {
	const { member } = props;
	return (
		<div>
			{member && member.name
				? <>
					<div className="memberHeader">{member.name}</div>
					<div className="memberBio">
						{member.shortBioImageUrl &&
							<img src={member.shortBioImageUrl} className="memberBioImageSm" />
						}
						<div className="memberBioText">{member.shortBio}</div>
					</div>
				</>
				: <>
					<div className="memberHeader">Unknown</div>
					<div className="memberBio">No information found</div>
				</>
			}
		</div>
	);
};

export default Member;
