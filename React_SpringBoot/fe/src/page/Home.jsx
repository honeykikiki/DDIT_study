import { Link, Outlet } from "react-router";

function Home() {
  return (
    <div>
      <div style={{ display: "flex", gap: "10px" }}>
        <Link to={"/"}>홈</Link>
        <Link to={"/board"}>게시판</Link>
      </div>
      <Outlet />
    </div>
  );
}

export default Home;
