import { useEffect } from "react";

const Board = () => {
  useEffect(() => {
    fetch("http://localhost:8080/get");
  }, []);
  return <div>Board</div>;
};

export default Board;
