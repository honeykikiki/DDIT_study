import { useRef } from "react";
import { boardInsert } from "../remote/board";
import Input from "./Input";
import Button from "./Button";

// eslint-disable-next-line react/prop-types
const BoardInsertForm = ({ setBoardList }) => {
  const formRef = useRef(null);
  // 게시물 추가
  const handleInsert = async (e) => {
    e.preventDefault();

    // 폼이 없는경우 에러처리
    if (formRef === null) return;

    const data = await boardInsert(formRef);
    if (data !== null && data.code === 1) {
      setBoardList((prevBoard) => [data.item, ...prevBoard]);
      formRef.current.title.value = "";
      formRef.current.content.value = "";
      formRef.current.writer.value = "";
    }
  };

  return (
    <>
      <form ref={formRef}>
        title <Input dataName="title" /> <br />
        content <Input dataName="content" /> <br />
        writer <Input dataName="writer" /> <br />
        file <input id="files" name="files" type="file" multiple /> <br />
        <Button title={"추가"} onClick={handleInsert} />
      </form>
    </>
  );
};

export default BoardInsertForm;
