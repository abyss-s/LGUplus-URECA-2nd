import React from 'react';
import Avatar from './Avatar';
const style = {
    padding: 10,
    fontSize: "20px",
    color: "gray",
};
function UserInfo(props) {
    return (
        <div>
            <Avatar author={props.author} />
            <div style={style}>{ props.author.name }</div>
        </div>
    );
}
export default UserInfo;