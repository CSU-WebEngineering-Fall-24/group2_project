import React, { useEffect, useState } from "react";
import Member from "../components/Member";
import membersData from "../../resources/members.json";

const AboutContainer = () => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    setMembers(membersData);
  }, []);

  return (
    <>
      {members.map((member) => (
        <Member key={member.id} member={member} />
      ))}
    </>
  );
};

export default AboutContainer;
