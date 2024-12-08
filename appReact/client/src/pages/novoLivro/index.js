import React, { useState , useEffect} from "react";
import { useNavigate, Link , useParams} from "react-router-dom";

import { FiArrowLeft } from "react-icons/fi";
import "./styles.css";
import logo from "../../assets/logovisual.png";

import api from "../../services/api";
import { data } from "autoprefixer";

export default function NovoLivro() {

  const [id, setId] = useState(null);
  const [author, setAuthor] = useState("");
  const [launchDate, setLaunchDate] = useState("");
  const [price, setPrice] = useState("");
  const [title, setTitle] = useState("");

  const {bookId} = useParams();

  const userName = localStorage.getItem('userName');
  const acessToken= localStorage.getItem('acesssToken');

  const navigate = useNavigate();
  
  async function loadBook() {
    try {
      const response = await api.get(`/api/book/v1/${bookId}`,{
        headers:{
          Authorization: `Bearer ${acessToken}`
      }
      })
      let ajustaData = response.data.launchDate.split("T",10)[0];
      setId(response.data.id)
      setTitle(response.data.title)
      setAuthor(response.data.author)
      setPrice(response.data.price)
      setLaunchDate(ajustaData)
      
    } catch (err) {
      alert('Erro ao recuperar o livro! Tente novamente!');
      navigate('/livros');
    }
  }

  useEffect(() => {
    if(bookId === '0') return;
    else loadBook();
  },[bookId])
  
  async function salvarOuEditar(e) {
    e.preventDefault();
    const data={
        title,
        author,
        launchDate,
        price,
    }
   
    try {
       if (bookId === '0') {
        await api.post('/api/book/v1',data, {
          headers:{
              Authorization: `Bearer ${acessToken}`
          }
        });
        
       } else {
        data.id = id;
          await api.put('/api/book/v1',data, {
            headers:{
                Authorization: `Bearer ${acessToken}`
            }
        });
       }
        
        navigate('/livros')
    } catch (err) {
        alert('Erro ao salvar o livro! Tente novamente!')
    }
    
}

    return (
      <div className="novo-livro-container">
        <div className="content">
          <section className="form">
            <img src={logo} alt="" />
            <Link className="back-link" to="/livros">
              <FiArrowLeft size={16} color="#ffffff" />
              Voltar
            </Link>
          </section>
          <form onSubmit={salvarOuEditar}>
            <h1>{bookId === '0' ? 'Cadastar novo livro' : 'Editar Livro'}</h1>
            <p>{bookId === '0' ? 'Informe os dados do livro e clique em cadastrar' : 'Altere os dados e clique em Editar'} </p>
            <input
                 type="text" 
                 placeholder="Título" 
                 value={title}
                 onChange={e => setTitle(e.target.value)}
                 />
            <input
                 type="text" 
                 placeholder="Autor" 
                 value={author}
                 onChange={e => setAuthor(e.target.value)}
                 />
            <input
                 type="date" 
                 value={launchDate}
                 onChange={e => setLaunchDate(e.target.value)}
                 />
            <input
                 type="text" 
                 placeholder="Preço" 
                 value={price}
                 onChange={e => setPrice(e.target.value)}
                 />
            <button className="button" type="submit">{bookId === '0' ? 'Cadastrar' : 'Editar'}</button>
          </form>
        </div>
      </div>
    );
  
}
