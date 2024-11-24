import React from "react";
import { render, fireEvent } from "@testing-library/react";
import Pagination from "../components/Pagination";

describe("Pagination", () => {
	test("Has 1 link", () => {
		const items = Array.from({ length: 4 });
		const pagination = render(<Pagination items={items} onClcik={() => { }} />);
		const query = pagination.queryAllByRole("listitem");
		expect(query.length).toBe(1);
	});

	test("Has 2 links and first link is active", () => {
		const items = Array.from({ length: 25 });
		const pagination = render(<Pagination items={items} onClcik={() => { }} />);
		const query = pagination.queryAllByRole("listitem");
		const li = pagination.container.querySelector("li");
		const a = li.querySelector("a");
		expect(query.length).toBe(2);
		expect(li.classList.contains("active")).toBe(true);
		expect(a).toBeTruthy();
		expect(a.innerHTML).toBe("1");
	});

	test("Has 2 links and second link is active", () => {
		let testValue = 1;
		const items = Array.from({ length: 25 });
		const pagination = render(<Pagination items={items} onClick={() => { ++testValue; }} />);
		const query = pagination.queryAllByRole("listitem");
		const li = pagination.container.children[0].children[0].children[1];

		const a = li.children[0];
		expect(query.length).toBe(2);
		expect(li.classList.length).toBe(0);
		expect(typeof (li.onclick)).toBe("function");

		expect(a).toBeTruthy();
		expect(a.innerHTML).toBe("2");

		fireEvent.click(li);
		expect(testValue).toBe(2);
		expect(li.classList.length).toBe(1);
		expect(li.classList.contains("active")).toBe(true);
	});

	test("Has 5 links", () => {
		const items = Array.from({ length: 70 });
		const pagination = render(<Pagination items={items} onClick={() => { }} />);
		const query = pagination.queryAllByRole("listitem");
		expect(query.length).toBe(5);
	});

	test("Has 5 links and fifth link is active", () => {
		let testValue = 1;
		const items = Array.from({ length: 70 });
		const pagination = render(<Pagination items={items} onClick={() => { ++testValue; }} />);
		const query = pagination.queryAllByRole("listitem");
		const li = pagination.container.children[0].children[0].children[4];

		const a = li.children[0];
		expect(query.length).toBe(5);
		expect(li.classList.length).toBe(0);
		expect(typeof (li.onclick)).toBe("function");

		expect(a).toBeTruthy();
		expect(a.innerHTML).toBe("5");

		fireEvent.click(li);
		expect(testValue).toBe(2);
		expect(li.classList.length).toBe(1);
		expect(li.classList.contains("active")).toBe(true);
	});
});
