import React, { useReducer, useState } from 'react';
import './Todo.css';

const command = {
  ADD: 'ADD',
  TOGGLE: 'TOGGLE',
  REMOVE: 'REMOVE',
};

const reducer = (state, action) => {
  switch (action.type) {
    case command.ADD:
      return [...state, { id: Date.now(), text: action.text, completed: false }];
    case command.TOGGLE:
      return state.map((todo) =>
        todo.id === action.id ? { ...todo, completed: !todo.completed } : todo
      );
    case command.REMOVE:
      return state.filter((todo) => todo.id !== action.id);
    default:
      return state;
  }
};

const Todo = () => {
  const [todos, dispatchTodos] = useReducer(reducer, []);
  const [text, setText] = useState('');

  return (
    <div className='app'>
      <div className='todo-container'>
        <h2>📋 Todo List</h2>
        <div className='input-container'>
          <input
            value={text}
            onChange={(e) => {
              setText(e.target.value);
            }}
          />
          <button
            onClick={() => {
              dispatchTodos({ type: command.ADD, text });
              setText('');
            }}
          >
            추가
          </button>
        </div>
        <ul className='todo-list'>
          {todos.map((todo) => (
            <li key={todo.id} className='todo-item'>
              <div className={`todo-text ${todo.completed ? 'completed' : ''}`}>{todo.text}</div>
              <button onClick={() => dispatchTodos({ type: command.TOGGLE, id: todo.id })}>
                완료
              </button>
              <button onClick={() => dispatchTodos({ type: command.REMOVE, id: todo.id })}>
                삭제
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default Todo;
