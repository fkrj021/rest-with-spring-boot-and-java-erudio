import React, {useState} from "react";
import {useNavigate } from 'react-router-dom';
import "./styles.css";
import logo from '../../assets/logovisual.png'
import api from '../../services/api'

export default function Login() {

  const [userName,setuserName] = useState('');
  const [password,setpassword] = useState('');

  const navigate = useNavigate();

  async function login(e) {
    e.preventDefault();

    const data = {
      userName,
      password,
    };
    try {
      const response = await api.post('/auth/signin',data);
      localStorage.setItem('userName',userName);
      localStorage.setItem('acesssToken',response.data.accessToken);
      console.log('Login bem-sucedido:', response.data); 
      navigate('/livros')
    } catch (err) {
      alert('Erro de login! Tente novamente!');
    }
  };
  
  return (
    <div className="login-container">
      <section className="form">
        <img className="logo" src={logo} alt="Login" />
        <h1>Sistema de Gerenciamento de Livraria</h1>
        <form onSubmit={login}>
          <h1>Acesse sua conta</h1>
          <input
              placeholder="Usuário"
              value={userName}
              onChange={e => setuserName(e.target.value)}
           />
          <input
              type= "password" placeholder="Senha"
              value={password}
              onChange={e => setpassword(e.target.value)}
           />
          <button className="button" type="submit">Entrar</button>
        
        </form>
      </section>

      {/* <img className="loginLogo" src={loginLogo} alt="Login" /> */}
    </div>
  );
}
