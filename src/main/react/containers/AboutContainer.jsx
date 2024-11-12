import React, { useEffect } from "react";
import Member from "../components/Member";

const AboutContainer = () => {

	const member = {
		name: "Michael",
		shortBio: "I am currently working on my Masters in Computer Science.  This is my last class and I'm very excited to graduate.  I started my career life as a draftsman in architecture.  In 2002, I went back to get my Bachelor's in Computer Science.  While working full time, I went to school part time and graduated in 2009.  I got a job at CallCopy in 2010 and have been a software engineer ever since.  I work at NICE, which is the number one vendor in Call Center as a Service (CCaaS).",
		shortBioImageUrl: "https://avatars.githubusercontent.com/u/178439261?v=4"
	};

	return (
		<>
			About Page...
			<Member member={member} />
		</>
	);
}

export default AboutContainer;
