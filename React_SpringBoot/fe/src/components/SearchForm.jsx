import { useRef } from "react";
import Button from "./Button";
import Input from "./Input";

// eslint-disable-next-line react/prop-types
const SearchForm = ({ setPagination, setBRefresh }) => {
  const formRef = useRef(null);
  const handleSearch = (e) => {
    e.preventDefault();

    // 폼이 없는경우 에러처리
    if (formRef === null) return;

    setPagination((prevParam) => ({
      ...prevParam,
      searchVO: {
        title: formRef.current.title.value,
        content: formRef.current.content.value,
        writer: formRef.current.writer.value,
      },
    }));

    setBRefresh((prev) => !prev);
  };

  return (
    <div>
      검색 하기~
      <form ref={formRef}>
        제목 <Input dataName="title" /> <br />
        내용 <Input dataName="content" /> <br />
        작성자 <Input dataName="writer" /> <br />
        <Button title={"검색"} onClick={handleSearch} />
      </form>
    </div>
  );
};

export default SearchForm;
