import { Outlet } from "react-router";
import Navigation from "../components/Navigation";
import { DatePicker } from "antd";

function Home() {
  return (
    <div>
      <Navigation />
      <Outlet />

      <DatePicker />

      <a href="https://ant.design/docs/react/introduce">ANT디자인 여기 경로 가면됨</a>
    </div>
  );
}

export default Home;
