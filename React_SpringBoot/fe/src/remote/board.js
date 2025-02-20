export async function boardInsert(formRef) {
  const formData = new FormData();
  formData.append("title", formRef.current.title.value);
  formData.append("content", formRef.current.content.value);
  formData.append("writer", formRef.current.writer.value);

  Object.values(formRef.current.files.files).forEach((file) => {
    formData.append("files", file);
  });

  try {
    const response = await fetch("http://localhost:8080/board/insert", {
      method: "POST",
      body: formData,
    })
  
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    
    return null
  }
}

export async function boardUpdate(curBoard) {
  const formData = new FormData();
  formData.append("boardId", curBoard.boardId);
  formData.append("title", curBoard.title);
  formData.append("content", curBoard.content);
  formData.append("writer", curBoard.writer);

  try {
    const response =  await fetch("http://localhost:8080/board/update", {
      method: "POST",
      body: formData,
    });

    const data = await response.json();

    return data;
  } catch (error) {
    console.error(error);
    return null;
  }
}


export async function boardDelete(boardId) {
  try {
    const response = await fetch("http://localhost:8080/board/delete", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ boardId }),
    })
  
    const data = await response.json();

    return data
  } catch (error) {
    console.error(error);
    
    return null;
  }
}