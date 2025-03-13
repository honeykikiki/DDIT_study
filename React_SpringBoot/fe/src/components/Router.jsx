import { Route, Routes } from "react-router";

import Home from "../page/Home";
import BoardList from "../page/board/BoardList";
import ChatPage from "../page/chat";
import UserDetailPage from "../page/user/Detail";

function Router() {
  return (
    <Routes>
      <Route path="/" element={<Home />}>
        <Route path="board" element={<BoardList />} />
        <Route path="chat" element={<ChatPage />} />
        <Route path="user" element={<UserDetailPage />} />
      </Route>
    </Routes>
  );
}

export default Router;
