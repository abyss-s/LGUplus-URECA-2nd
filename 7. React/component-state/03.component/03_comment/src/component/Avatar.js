import React from 'react';
const style = {
    padding: 10,
};
const Avatar = (props) => {
    return (<div style={style}>
        <img src={props.author.avatarUrl} alt = {props.author.name} width={150}></img>
    </div>);
};
export default Avatar;