import ColorBox from "./components/ColorBox";
import SelectColors from "./components/SelectColors";
import { ColorProvider } from "./store/color";
const App = () => {
  return (
    <div>
      <ColorProvider>
        <div>
          <SelectColors />
          <ColorBox />
        </div>
      </ColorProvider>
    </div>
  );
};

export default App;
