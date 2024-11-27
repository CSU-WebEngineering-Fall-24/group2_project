import React from "react";
import { render, screen } from "@testing-library/react";
import Member from "../../components/Member";

describe("Member", () => {
  test("Render with no image", () => {
    const info = {
      name: "Michael",
      shortBioImageUrl: null,
      shortBio: "This is my short bio.",
    };

    render(<Member member={info} />);

    expect(screen.getByText("Michael")).toBeInTheDocument();
    expect(screen.getByText("This is my short bio.")).toBeInTheDocument();
    expect(screen.queryByRole("img")).not.toBeInTheDocument();
  });

  test("Render with image", () => {
    const info = {
      name: "Michael",
      shortBioImageUrl: "http://www.test.com",
      shortBio: "This is my short bio.",
    };

    render(<Member member={info} />);

    expect(screen.getByText("Michael")).toBeInTheDocument();
    expect(screen.getByText("This is my short bio.")).toBeInTheDocument();
    expect(screen.getByRole("img")).toHaveAttribute(
      "src",
      "http://www.test.com"
    );
  });

  test("Render with null member", () => {
    render(<Member member={null} />);
    expect(screen.getByText("Unknown")).toBeInTheDocument();
    expect(screen.getByText("No information found")).toBeInTheDocument();
  });

  test("Render with member with null name", () => {
    const info = {
      name: null,
      shortBioImageUrl: "http://www.test.com",
      shortBio: "This is my short bio.",
    };

    render(<Member member={info} />);

    expect(screen.getByText("Unknown")).toBeInTheDocument();
    expect(screen.getByText("No information found")).toBeInTheDocument();
  });
});
