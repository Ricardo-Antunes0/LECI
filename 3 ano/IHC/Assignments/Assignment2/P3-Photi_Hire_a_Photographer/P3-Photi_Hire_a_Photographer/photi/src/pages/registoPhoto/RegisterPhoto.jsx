
import React,{useState,useRef,useEffect} from 'react'
import "./registoPhoto.css"
import {AddPhotoAlternate} from '@mui/icons-material';
import Topbar from "../../components/topbar/Topbar"
import FooterContainer from "../../components/FooterContainer/FooterContainer";
import {useNavigate} from "react-router-dom"
import background from "./backhome1.jpg"
const styles = {
    container: {
        backgroundImage: `url(${background})`,
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        width: '99vw',
        height: '150vh'
    }
  };
export default function RegisterPhoto() {
    let navigate=useNavigate();

    const [nome,setNome]=useState()
    const [desc,setDesc]=useState()
    const [port,setLink]=useState()
    const [cidade,setCidade]=useState()
    const [preco1,setPreco]=useState()
    const [preco2,setPreco2]=useState()
    const [preco3,setPreco3]=useState()
    const [tipo1,setTipo1]=useState()
    const [tipo2,setTipo2]=useState()
    const [tipo3,setTipo3]=useState()
    const [tipo4,setTipo4]=useState()
    const [tipo5,setTipo5]=useState()
    const [tipo6,setTipo6]=useState()

    const top=useRef(null);
    
    const armazenar=(chave,valor)=>{
        localStorage.setItem(chave,valor)
    }

    const scrollToSection = (elementRef) => {
        window.scrollTo({
          top: elementRef.current.offsetTop,
          behavior: "smooth",
        });
      };
      useEffect(() => {
        
        scrollToSection(top);
      }, []);
    return (
        <>
        <div className='gcl'ref={top}>
        <Topbar />
        <div style={styles.container}> 
        <div className="rregiste" >
            
                <div className="rregisterWrappe">
                
                    <div className="rregisterLef">
                        
                        <h3 className="rregisterLog">Vamos agora criar o seu perfil de fotógrafo:</h3>
                        <span className="rregist"> Nota: Seja apelativo, direto, interessante e mais importante que tudo, seja voçê mesmo!!</span>
                        <br></br>
                        <div className="rregisterBo">
                            <span className="rregistoConten"> Nome artístico:</span>
                            <input placeholder="" className="rregisterInpu" value={nome} onChange={(e)=>setNome(e.target.value)}/>
                            <br></br>
                            <br></br>
                            <span className="rregistoConten">Breve descrição:</span>
                            <input placeholder="" className="rregisterInpu" value={desc} onChange={(e)=>setDesc(e.target.value)}/>
                            <br></br>
                            <br></br>
                            <span className="rregistoConten">Link Portfólio:</span>
                            <input placeholder="" className="rregisterInpu" value={port} onChange={(e)=>setLink(e.target.value)}/>
                            <br></br>   
                            <br></br>
                            <br></br>
                            <span className="rregistoConten">Cidade:</span>
                            <input placeholder="" className="rregisterInpu" value={cidade} onChange={(e)=>setCidade(e.target.value)}/>
                            <span className="rregistoConten">Tipo de eventos:</span>
                            <div className="cCheckBoxContaine">
                                <label for="checkbox">
                                <input className="checkbox-option" type="checkbox" value={tipo1} onChange={(e)=>setTipo1(e.target.checked)}/> Casamento
                                </label>
                                <label for="checkbox">
                                    <input className="checkbox-option" type="checkbox" value={tipo2} onChange={(e)=>setTipo2(e.target.checked)}/> Batizado
                                    </label>
                                
                                <label for="checkbox">
                                    <input className="checkbox-option" type="checkbox" value={tipo3} onChange={(e)=>setTipo3(e.target.checked)}/> Retrato
                                </label>
                                <label for="checkbox">
                                    <input className="checkbox-option" type="checkbox" value={tipo4} onChange={(e)=>setTipo4(e.target.checked)}/> Evento
                                </label>
                                <label for="checkbox">
                                    <input class="checkbox-option" type="checkbox" value={tipo5} onChange={(e)=>setTipo5(e.target.checked)}/> Publicidade
                                </label>
                                <label for="checkbox">
                                    <input class="checkbox-option" type="checkbox" value={tipo6} onChange={(e)=>setTipo6(e.target.checked)}/> Festa
                                </label>    
                            </div>
                            <span className="rregistoConten">Preço médio:</span>
                            <span className="infopreco">Nota: o preço é apenas representativo e não consta um valor fixo mas sim uma gama de valores.</span>
                            <span className="infopreco2">€ - valores menores que 200 euros.</span>
                            <span className="infopreco2">€€-€ - valores entre 200 e 500 euros.</span>
                            <span className="infopreco2">€€€-€€ - valores acima de 500 euros.</span>
                            <div className="cCheckBoxContaine">
                                <label for="checkbox">
                                <input className="checkbox-option" type="checkbox" value={preco1} onChange={(e)=>setPreco(e.target.checked)}/> €
                                </label>
                                <label for="checkbox">
                                    <input className="checkbox-option" type="checkbox" value={preco2} onChange={(e)=>setPreco2(e.target.checked)}/> €€-€
                                    </label>
                                
                                <label for="checkbox">
                                    <input className="checkbox-option" type="checkbox" value={preco3} onChange={(e)=>setPreco3(e.target.checked)}/> €€€-€€
                                </label>
    
                            </div>
                                <span className="rregistoConten">Introduza fotos representativas do seu trabalho:<AddPhotoAlternate size="5rem" /></span>
                            <br></br>
                            <br></br>
                            <label for="checkbox">
                            <input class="checkbox-option" type="checkbox" value=""/> Aceito os termos e condições 
                            </label>   
                            <br></br>
                            <button onClick={()=>{armazenar('ls_tipo6',tipo6);armazenar('ls_tipo5',tipo5);armazenar('ls_tipo4',tipo4);armazenar('ls_tipo3',tipo3);armazenar('ls_tipo2',tipo2);armazenar('ls_tipo1',tipo1);armazenar('ls_preco3',preco3);armazenar('ls_preco2',preco2);armazenar('ls_preco1',preco1);armazenar('ls_nome',nome);armazenar('ls_cidade',cidade);armazenar('ls_desc',desc);armazenar('ls_port',port);navigate("/profile")}} className="registerButton">Sign Up</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <FooterContainer/>
        
    </>    
    );
}