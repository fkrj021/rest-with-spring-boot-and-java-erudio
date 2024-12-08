import React,{useState,useEffect} from "react";
import { Link, useNavigate } from "react-router-dom";
import { FiPower,FiEdit,FiTrash2 } from "react-icons/fi";
import './styles.css';
import logo from '../../assets/logovisual.png'

import api from "../../services/api";
//import { direction } from "@excalidraw/excalidraw/types/ga";
export default function Livros(){
    
    const [books, setBooks] = useState([]);
    const [page, setPages] = useState(0);

    const userName = localStorage.getItem('userName');
    const acessToken= localStorage.getItem('acesssToken');
  
    const navigate = useNavigate();

    async function sair(){
        localStorage.clear();
        navigate('/')
    }

    

    async function editarLivro(id) {
        try {
            navigate(`/livro/novo/${id}`)
        } catch (error) {
            alert('Falha ao editar o registro! Tente novamente')
        }
 
    }

    async function deletaLivro(id) {
        try {
            await api.delete(`/api/book/v1/${id}`,{
                headers: {
                    Authorization: `Bearer ${acessToken}`
                  }, 
            })
            setBooks(books.filter(book => book.id !== id))
        } catch (err) {
            alert('Falha ao deletar o registro! Tente novamente')
        }
    }

    // async function fetchMoreBooks() {
    //     const response = await api.get('/api/book/v1',{
    //         headers: {
    //             Authorization: `Bearer ${acessToken}`
    //           }, 
    //         params:{
    //             page:page,
    //             size:18,
    //             direction:'asc'
    //         }         
    //     });

    //     setBooks([ ...books,...response.data._embedded.bookVOList])
    //     setPages(page++)
    // }

    useEffect(() => {
        api.get('/api/book/v1',{
            headers: {
                Authorization: `Bearer ${acessToken}`
              }, 
            params:{
                page:0,
                size:18,
                direction:'asc'
            }         
        }).then(response => {
            console.log('Resposta da API:', response.data._embedded);
            setBooks(response.data._embedded.bookVOList)
        })
        .catch((error) => {
            console.error('Erro ao buscar livros:', error);
            if (error.response) {
                console.log('Status:', error.response.status);
                console.log('Dados:', error.response.data);
            } else if (error.request) {
                console.log('Erro na requisição:', error.request);
            } else {
                console.log('Erro desconhecido:', error.message);
            }

            alert('Falha ao carregar livros, faça login novamente!');
            navigate('/');
          });
    }, [acessToken, navigate])

    return (
        <div className="livro-container">
            <header>
                <img src={logo} alt="Logo" />
                <span>Bem vindo,  <strong>{userName.toUpperCase()}</strong> !</span>
                <Link className="button" to="/livro/novo/0">Adicionar novo livro</Link>
                <button onClick={sair} type="button">
                    <FiPower size={18} color="#ffffff"/>
                </button>
            </header>
            <h1>Livros Cadastrados</h1>
            <ul>
                {books.map(book => (
                    <li key={book.id}>
                    <strong>Titulo:</strong>
                    <p>{book.title}</p>
                    <strong>Autor:</strong>
                    <p>{book.author}</p>
                    <strong>Preço:</strong>
                    <p>{Intl.NumberFormat('pt-BR',{style:'currency', currency:'BRL'}).format(book.price)}</p>
                    <strong>Data Lançamento:</strong>
                    <p>{Intl.DateTimeFormat('pt-BR').format(new Date(book.launchDate))}</p>
    
                    <button type="button">
                        <FiEdit onClick={()=> editarLivro(book.id)} className="edit" size={18} color="#02024e"/>
                    </button>
                    <button onClick={() => deletaLivro(book.id)} type="button">
                        <FiTrash2 className="delete" size={18} color="#02024e"/>
                    </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
