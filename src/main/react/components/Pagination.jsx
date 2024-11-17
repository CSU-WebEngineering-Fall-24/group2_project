import React, { useState } from "react";

const Pagination = (props) => {
	var { items, onClick } = props;

	const [currentPageIndex, setCurrentPageIndex] = useState(0);

	const maxPageNumbers = 5;
	const maxPerPage = 15;
	const pageCount = Math.ceil(items.length / maxPerPage);
	const pageCountIndex = pageCount - 1;

	const showLeftEllipsis = (currentPageIndex - 2) > 0;
	const showRightEllipsis = (currentPageIndex + 3) < pageCount;

	let displayPageIndex = (currentPageIndex == pageCountIndex) ? currentPageIndex - 1 : currentPageIndex;
	displayPageIndex = (currentPageIndex == 0) ? 1 : displayPageIndex;

	function onPageClick(pageIndex) {
		setCurrentPageIndex(pageIndex);
		onClick(pageIndex);
	}

	return (
		<nav>
			<ul className="pagination">
				{(pageCount < 1)
					? <li>No Contents</li>
					: <li className={currentPageIndex === 0 ? "active" : ""} onClick={(currentPageIndex !== 0) ? () => { onPageClick(0) } : null}><a href="#" data-page="0">1</a></li>
				}
				{(pageCount <= maxPageNumbers)
					? <>{Array.from({ length: maxPageNumbers - 1 }).map((_, key) => <li className={currentPageIndex === (key + 1) ? "active" : ""} onClick={(currentPageIndex !== (key + 1)) ? () => { onPageClick(key + 1) } : null}> <a href="#" data-page={key + 1}>{key + 2}</a></li>)}</>
					: <>
						{showLeftEllipsis && <li>...</li>}
						{((displayPageIndex - 1) > 0) && <li onClick={() => { onPageClick(displayPageIndex - 1) }}><a href="#" data-page={displayPageIndex - 1}>{displayPageIndex}</a></li>}
						<li className={currentPageIndex == displayPageIndex ? "active" : ""} onClick={() => { onPageClick(displayPageIndex) }}><a href="#" data-page={displayPageIndex}>{displayPageIndex + 1}</a></li>
						{((displayPageIndex + 1) < pageCountIndex) && <li onClick={() => { onPageClick(displayPageIndex + 1) }}><a href="#" data-page={displayPageIndex + 1}>{displayPageIndex + 2}</a></li>}
						{showRightEllipsis && <li>...</li>}
						<li className={(currentPageIndex === pageCountIndex) ? "active" : ""} onClick={() => { onPageClick(pageCountIndex) }}><a href="#" data-page={pageCountIndex}>{pageCount}</a></li>
					</>
				}
			</ul>
		</nav >
	);


}

export default Pagination;
