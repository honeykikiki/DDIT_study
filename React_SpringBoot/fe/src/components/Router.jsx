import { Route, Routes } from "react-router";

import Home from "../page/Home";
import BoardList from "./board/BoardList";
import ChatPage from "../page/chat";

function Router() {
  return (
    <Routes>
      <Route path="/" element={<Home />}>
        <Route path="board" element={<BoardList />} />
      </Route>

      <Route path="/chat" element={<ChatPage />}></Route>
    </Routes>
  );
}

export default Router;
