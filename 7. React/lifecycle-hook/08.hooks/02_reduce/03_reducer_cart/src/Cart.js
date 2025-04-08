import React, { useReducer, useState } from 'react';
import './Cart.css';

const sampleItem = {
  id: 1,
  name: 'React 티셔츠',
  price: 25000,
  quantity: 1,
};

const cmd = {
  ADD: 'ADD',
  REMOVE: 'REMOVE',
  INCREASE: 'INCREASE',
  DECREASE: 'DECREASE',
};

const reducer = (state, action) => {
  switch (action.type) {
    case cmd.ADD:
      const existingItem = state.find((item) => item.id === action.payload.id);
      if (existingItem) {
        return state.map((item) =>
          item.id === action.payload.id ? { ...item, quantity: item.quantity + 1 } : item
        );
      } else {
        return [...state, { ...action.payload, quantity: 1 }];
      }
    case cmd.REMOVE:
      return state.filter((item) => item.id !== action.payload.id);
    case cmd.INCREASE:
      return state.map((item) =>
        item.id === action.payload.id ? { ...item, quantity: item.quantity + 1 } : item
      );
    case cmd.DECREASE:
      return state.map((item) =>
        item.id === action.payload.id && item.quantity > 0
          ? { ...item, quantity: item.quantity - 1 }
          : item
      );
    default:
      return state;
  }
};

const Cart = () => {
  const [cart, dispatch] = useReducer(reducer, []);
  const totalCost = cart.reduce((total, item) => total + item.price * item.quantity, 0);
  return (
    <div>
      <h2>🛒 장바구니</h2>
      <button onClick={() => dispatch({ type: cmd.ADD, payload: sampleItem })}>
        React 티셔츠 추가
      </button>

      <ul className='cart-list'>
        {cart.map((item) => (
          <li key={item.id} className='cart-item'>
            <div>
              <span>
                {item.name} - 수량: {item.quantity}개 - 가격 ₩{totalCost}
              </span>
            </div>
            <div>
              <button onClick={() => dispatch({ type: cmd.INCREASE, payload: item })}> + </button>
              <button onClick={() => dispatch({ type: cmd.DECREASE, payload: item })}> - </button>
              <button onClick={() => dispatch({ type: cmd.REMOVE, payload: item })}>삭제</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Cart;
