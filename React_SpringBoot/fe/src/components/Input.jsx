// eslint-disable-next-line react/prop-types
const Input = ({ dataName, ...props }) => {
  // ...props는 기존 input attribute를 확장해서 받기 위해
  return <input id={dataName} name={dataName} type="text" {...props} />;
};

export default Input;
