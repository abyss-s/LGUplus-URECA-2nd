// color.ts
export const CHANGE_COLOR = "color/CHANGE_COLOR" as const;
export const CHANGE_SUBCOLOR = "color/CHANGE_SUBCOLOR" as const;

export const changeColor = (color: string) => ({
  type: CHANGE_COLOR,
  payload: color,
});

export const changeSubColor = (subcolor: string) => ({
  type: CHANGE_SUBCOLOR,
  payload: subcolor,
});

type ColorAction = ReturnType<typeof changeColor> | ReturnType<typeof changeSubColor>;

interface ColorState {
  color: string;
  subcolor: string;
}

const initialState: ColorState = {
  color: "black",
  subcolor: "red",
};

export default function colorReducer(state = initialState, action: ColorAction): ColorState {
  switch (action.type) {
    case CHANGE_COLOR:
      return {
        ...state,
        color: action.payload,
      };
    case CHANGE_SUBCOLOR:
      return {
        ...state,
        subcolor: action.payload,
      };
    default:
      return state;
  }
}
