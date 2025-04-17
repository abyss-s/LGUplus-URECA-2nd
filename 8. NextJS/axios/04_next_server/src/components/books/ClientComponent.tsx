"use client";
import React, { useState } from "react";

const ClientComponent = () => {
  const [data] = useState<string>("Hello");
  return <div>{data}</div>;
};

export default ClientComponent;
