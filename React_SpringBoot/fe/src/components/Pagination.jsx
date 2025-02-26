/* eslint-disable react/prop-types */
import Button from "./Button";

function Pagination({ pagination, nextPage, prevPage, currentPage, firstPage, lastPage }) {
  return (
    <>
      <Button title="<<" onClick={firstPage} />
      <Button title="<" onClick={prevPage} />

      {Array(pagination?.pageSize ?? 0)
        .fill("")
        .map((_, idx) => {
          idx += 1;

          return (
            <Button
              key={idx}
              title={idx}
              onClick={() => currentPage(idx)}
              style={
                idx == pagination.pageNo
                  ? {
                      background: "red",
                    }
                  : null
              }
            />
          );
        })}
      <Button title=">" onClick={nextPage} />
      <Button title=">>" onClick={lastPage} />
    </>
  );
}

export default Pagination;
