// const MyButton = ({ title }: { title: string }) => {
//   return <button>{title}</button>;
// };

interface MyButtonProps {
  title: string;
  disabled: boolean;
}
const MyButton = ({ title, disabled }: MyButtonProps) => {
  return <button disabled={disabled}>{title}</button>;
};
export default MyButton;
