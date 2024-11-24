import React from "react";
import { render, fireEvent } from "@testing-library/react";
import Member from "../components/Member";

describe("Member", () => {
	test("Render with no image", () => {
		const info = {
			name: "Michael",
			shortBioImageUrl: null,
			shortBio: "This is my short bio."
		};
		const member = render(<Member member={info} />);
		const divCard = member.container.children[0];
		const divHeader = divCard.children[0];
		const divBio = divCard.children[1];
		const divText = divBio.children[0];

		expect(divCard.classList.contains("memberCard")).toBe(true);
		expect(divCard.children.length).toBe(2);

		expect(divHeader.innerHTML).toBe(info.name);
		expect(divText.innerHTML).toBe(info.shortBio);
	});

	test("Render with image", () => {
		const info = {
			name: "Michael",
			shortBioImageUrl: "http://www.test.com",
			shortBio: "This is my short bio."
		};
		const member = render(<Member member={info} />);
		const divCard = member.container.children[0];
		const divHeader = divCard.children[0];
		const divBio = divCard.children[1];
		const img = divBio.children[0];
		const divText = divBio.children[1];

		expect(divCard.classList.contains("memberCard")).toBe(true);
		expect(divCard.children.length).toBe(2);

		expect(divHeader.innerHTML).toBe(info.name);
		expect(img.getAttribute("src")).toBe(info.shortBioImageUrl);
		expect(divText.innerHTML).toBe(info.shortBio);
	});

	test("Render with null member", () => {
		const info = null;
		const member = render(<Member member={info} />);
		const divCard = member.container.children[0];
		const divHeader = divCard.children[0];
		const divBio = divCard.children[1];

		expect(divCard.classList.contains("memberCard")).toBe(true);
		expect(divCard.children.length).toBe(2);

		expect(divHeader.innerHTML).toBe("Unknown");
		expect(divBio.innerHTML).toBe("No information found");
	});

	test("Render with member with null name", () => {
		const info = {
			name: null,
			shortBioImageUrl: "http://www.test.com",
			shortBio: "This is my short bio."
		};
		const member = render(<Member member={info} />);
		const divCard = member.container.children[0];
		const divHeader = divCard.children[0];
		const divBio = divCard.children[1];

		expect(divCard.classList.contains("memberCard")).toBe(true);
		expect(divCard.children.length).toBe(2);

		expect(divHeader.innerHTML).toBe("Unknown");
		expect(divBio.innerHTML).toBe("No information found");
	});
});