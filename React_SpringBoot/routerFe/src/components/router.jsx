import { Route, Routes } from "react-router";

import Home from "../page/Home";
import BoardDetailPage from "../page/board/Detail";
import BoardListPage from "../page/board/Index";
import BoardInsertPage from "../page/board/Insert";
import BoardUpdatePage from "../page/board/Update";

function Router() {
  return (
    <Routes>
      <Route path="/" element={<Home />}>
        <Route path="/board" element={<BoardListPage />} />
        <Route path="/board/detail" element={<BoardDetailPage />} />
        <Route path="/board/insert" element={<BoardInsertPage />} />
        <Route path="/board/update" element={<BoardUpdatePage />} />
      </Route>

      <Route path="/*" element={<Home />} />
    </Routes>
  );
}

export default Router;
