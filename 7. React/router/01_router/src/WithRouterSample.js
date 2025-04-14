import React from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";

const WithRouterSample = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const params = useParams();

  return (
    <div>
      <h4>location</h4>
      <textarea value={JSON.stringify(location, null, 2)} rows={7} readOnly={true} />
      <h4>params</h4>
      <textarea value={JSON.stringify(params, null, 2)} rows={7} readOnly={true} />
      <button onClick={() => navigate("/")}>홈으로</button>
    </div>
  );
};

export default WithRouterSample;
