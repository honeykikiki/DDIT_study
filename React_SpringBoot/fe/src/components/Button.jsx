import PropTypes from "prop-types";

const Button = ({ title, ...props }) => {
  return <button {...props}>{title ?? "버튼 이름 넣어라"}</button>;
};

Button.propTypes = {
  title: PropTypes.string.isRequired, // title은 필수 문자열
};

export default Button;
