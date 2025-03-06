import { Outlet } from "react-router";
import Navigation from "../../components/Navigation";

function BoardPage() {
  return (
    <div>
      <Navigation />

      <Outlet />
    </div>
  );
}

export default BoardPage;
