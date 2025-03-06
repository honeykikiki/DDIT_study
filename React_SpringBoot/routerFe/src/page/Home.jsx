import { Outlet } from "react-router";
import Navigation from "../components/Navigation";
import { DatePicker } from "antd";

function Home() {
  return (
    <div>
      <Navigation />
      <Outlet />

      <DatePicker />
    </div>
  );
}

export default Home;
