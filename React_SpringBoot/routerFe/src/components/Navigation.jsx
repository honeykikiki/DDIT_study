import { Link } from "react-router";

export default function Navigation() {
  return (
    <div className="bg-white">
      <header className="relative bg-white">
        <nav aria-label="Top" className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="border-b border-gray-200">
            <div className="flex h-16 items-center">
              <div className="ml-4 flex lg:ml-0">
                <Link to={"/"}>Home</Link>
              </div>
              <div className="ml-4 flex lg:ml-0">
                <Link to={"/board"}>게시판 바로가기</Link>
              </div>
            </div>
          </div>
        </nav>
      </header>
    </div>
  );
}
