import React, { useState } from "react";

const EventHandle = () => {
  const [username, setUsername] = useState("");
  const [message, setMessage] = useState("");
  const handleUsername = (e) => {
    console.log(e);
    setUsername(e.target.value);
  };
  const handleMessage = (e) => {
    console.log(e);
    setMessage(e.target.value);
  };

  const handleClick = () => {
    setUsername("");
    setMessage("");
  };

  return (
    <div>
      <input
        type="text"
        value={username}
        onChange={handleUsername}
        name="username"
        placeholder="user name"
      ></input>
      <input
        type="text"
        onChange={handleMessage}
        value={message}
        name="message"
        placeholder="message"
      ></input>
      <button type="button" onClick={handleClick}>
        확인
      </button>
      <div>
        {username} {username !== "" && ":"} {message}
      </div>
    </div>
  );
};

export default EventHandle;