import { Route, Routes } from "react-router";

import Home from "../page/Home";
import BoardDetailPage from "../page/board/Detail";
import BoardListPage from "../page/board/List";
import BoardInsertPage from "../page/board/Insert";
import BoardUpdatePage from "../page/board/Update";
import BoardPage from "../page/board/Index";

function Router() {
  return (
    <Routes>
      <Route path="/board" element={<BoardPage />}>
        <Route path="list" element={<BoardListPage />} />
        <Route path="detail" element={<BoardDetailPage />} />
        <Route path="insert" element={<BoardInsertPage />} />
        <Route path="update" element={<BoardUpdatePage />} />
      </Route>

      <Route path="/*" element={<Home />} />
    </Routes>
  );
}

export default Router;
