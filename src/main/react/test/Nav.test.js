import React from "react";
import { MemoryRouter } from "react-router-dom";
import { render, fireEvent } from "@testing-library/react";
import Nav from "../components/Nav";

describe("Nav", () => {
	test("On first render, first link is active", () => {
		const nav = render(<MemoryRouter><Nav /></MemoryRouter>);
		const query = nav.queryAllByRole("listitem");
		const li = nav.container.querySelector("li");
		const a = li.querySelector("a");
		expect(query.length).toBe(2);
		expect(a.classList.contains("custom-active")).toBe(true);
	});

	test("Click on About, second link is active", () => {
		const nav = render(<MemoryRouter><Nav /></MemoryRouter>);
		const query = nav.queryAllByRole("listitem");
		const div = nav.container.children[0].children[0].children[2].children[0];
		const li = div.children[1];
		const a = li.children[0];
		expect(query.length).toBe(2);
		expect(a.classList.contains("custom-active")).toBe(false);

		fireEvent.click(a);
		expect(a.classList.contains("custom-active")).toBe(true);
	});

	test("Click on About, then Home Page, first link is active", () => {
		const nav = render(<MemoryRouter><Nav /></MemoryRouter>);
		const query = nav.queryAllByRole("listitem");
		const div = nav.container.children[0].children[0].children[2].children[0];
		const homeLi = div.children[0];
		const homeA = homeLi.children[0];
		const aboutLi = div.children[1];
		const aboutA = aboutLi.children[0];
		expect(query.length).toBe(2);
		expect(homeA.classList.contains("custom-active")).toBe(true);
		expect(aboutA.classList.contains("custom-active")).toBe(false);

		fireEvent.click(aboutA);
		expect(homeA.classList.contains("custom-active")).toBe(false);
		expect(aboutA.classList.contains("custom-active")).toBe(true);

		fireEvent.click(homeA);
		expect(homeA.classList.contains("custom-active")).toBe(true);
		expect(aboutA.classList.contains("custom-active")).toBe(false);
	});
});
