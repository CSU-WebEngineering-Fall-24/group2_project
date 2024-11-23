import React from "react";

const Pagination = ({ items, currentPage, itemsPerPage, onClick }) => {
  const pageCount = Math.ceil(items.length / itemsPerPage);

  if (pageCount <= 1) return null; // Disable pagination for 1 page of results

  return (
    <nav>
      <ul className="pagination justify-content-center">
        <li className={`page-item ${currentPage === 0 ? "disabled" : ""}`}>
          <button
            className="page-link"
            onClick={() => onClick(currentPage - 1)}
            aria-label="Previous"
            disabled={currentPage === 0}
          >
            <span aria-hidden="true">&laquo;</span>
          </button>
        </li>

        {Array.from({ length: pageCount }).map((_, index) => (
          <li
            key={index}
            className={`page-item ${index === currentPage ? "active" : ""}`}
          >
            <button className="page-link" onClick={() => onClick(index)}>
              {index + 1}
            </button>
          </li>
        ))}

        <li
          className={`page-item ${
            currentPage === pageCount - 1 ? "disabled" : ""
          }`}
        >
          <button
            className="page-link"
            onClick={() => onClick(currentPage + 1)}
            aria-label="Next"
            disabled={currentPage === pageCount - 1}
          >
            <span aria-hidden="true">&raquo;</span>
          </button>
        </li>
      </ul>
    </nav>
  );
};

export default Pagination;
