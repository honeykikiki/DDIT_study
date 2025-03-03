import { Route, Routes } from "react-router";

import Home from "../page/Home";
import BoardList from "./board/BoardList";

function Router() {
  return (
    <Routes>
      <Route path="/" element={<Home />}>
        <Route path="board" element={<BoardList />} />
      </Route>
    </Routes>
  );
}

export default Router;
