import styled from "styled-components";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
`;

const Box = styled.div`
  width: 200px;
  height: 200px;
  border: 1px solid pink;
`;

const Wrapper = styled.div`
  display: none;
  @media (min-width: 768px) {
    display: block;
  }
`;

const App = () => {
  return (
    <Container>
      <Wrapper>
        <Box>box1</Box>
      </Wrapper>
      <Box>box2</Box>
    </Container>
  );
};

export default App;
