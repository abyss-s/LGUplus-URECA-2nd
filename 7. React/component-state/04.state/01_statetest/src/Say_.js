import React, { useState } from 'react';

const Say = () => {
    const[message, setMessage] = useState(" ");
    const[color, setColor] = useState("black");
    const onClickEnter =() => {
      setMessage("Bonjour");
    };
    const onClickLeave =() => {
      setMessage("Au revoir");
    };
    
    return (
        <div>
            <button onClick={onClickEnter}>입장</button>
            <button onClick={onClickLeave}>퇴장</button>
            <h1 style={{color}}>{message}</h1>
            <button onClick={() => {
              setColor('red');
            }
            }>red</button>
            <button onClick={() => {
              setColor('blue');
            }
            }>blue</button>
            <button onClick={() => {
              setColor('green');
            }
            }>green</button>
        </div>
    );
};

export default Say;