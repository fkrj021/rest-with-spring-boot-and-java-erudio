import React from "react";
import { BrowserRouter, Route,Routes } from "react-router-dom";

import Login from "./pages/login";
import Livros from "./pages/livros";
import NovoLivro from "./pages/novoLivro";

export default function AppRoutes() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/livros" element={<Livros />} />
                <Route path="/livro/novo/:bookId" element={<NovoLivro />} />
            </Routes>
        </BrowserRouter>
    );
}