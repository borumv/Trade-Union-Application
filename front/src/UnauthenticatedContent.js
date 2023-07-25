import React from 'react';
import { BrowserRouter, Route, Routes, Navigate, useNavigate } from 'react-router-dom';
import Authentication from './node_modules/pages/authentication/index';

export default function UnauthenticatedContent() {
  const navigate = useNavigate();

  return (
    <>
      <Routes>
        <Route path="/login" element={<Authentication />} />
        <Route path="/register" element={<Authentication />} />
      </Routes>
    </>
  );
}
