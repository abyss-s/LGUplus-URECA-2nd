//https://jsonplaceholder.typicode.com/users

/*    <div className="container">
      <h1 className="title">User List</h1>
      <ul className="user-list">
       
          <li key="" className="user-item">
            <span className="user-name"></span>
            <br />
            <span className="user-email"></span>
          </li>
        
      </ul>
    </div>
*/

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Info.css';

const Info = () => {
  const [users, setUsers] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        setError(null);
        setUsers(null);

        setLoading(true);
        const response = await axios.get('https://jsonplaceholder.typicode.com/users');
        setUsers(response.data); // 데이터는 response.data 안에 들어있습니다.
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    };

    fetchUsers();
  }, []);

  if (loading) return <div>로딩중..</div>;
  if (error) return <div>에러가 발생했습니다</div>;
  if (!users) return null;
  return (
    <ul>
      {users.map((user) => (
        <li key={user.id}>
          {user.username} ({user.name})
        </li>
      ))}
    </ul>
  );
};
export default Info;
