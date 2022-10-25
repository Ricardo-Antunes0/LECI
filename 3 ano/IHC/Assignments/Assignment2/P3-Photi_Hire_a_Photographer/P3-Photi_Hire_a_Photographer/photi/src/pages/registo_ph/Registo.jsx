
import React, {useState,useEffect,useRef} from 'react'
import "./registo.css"
import Topbarnolog from "../../components/topbarnolog/Topbar"
import FooterContainer from "../../components/FooterContainer/FooterContainer";
import {useNavigate} from "react-router-dom"
import apagar from "../../pages/registo/apagar.png";
import background from "./backhome1.jpg";
import "./registo.css"

const styles = {
    container: {
        backgroundImage: `url(${background})`,
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        width: '100vw',
        height: '100vh',
  
    }};
  

export default function RegisterPhoto(){
    const [password, setPassword] = useState('');
    const [cPassword, setCPassword] = useState('');
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [cPasswordClass, setCPasswordClass] = useState('form-control');
    const [isCPasswordDirty, setIsCPasswordDirty] = useState(false);
    const top=useRef(null);
    useEffect(() => {
        if (isCPasswordDirty) {
            if (password === cPassword) {
                setShowErrorMessage(false);
                setCPasswordClass('form-control is-valid')
            } else {
                setShowErrorMessage(true)
                setCPasswordClass('form-control is-invalid')
            }
        }
    }, [cPassword])

    const handleCPassword = (e) => {
        setCPassword(e.target.value);
        setIsCPasswordDirty(true);
    }
    let navigate=useNavigate();
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
        <Topbarnolog />
        <div className="register" style={styles.container}  >

                <div className="registerWrapper">

                        <h3 className="registerLogo">Vamos agora criar o seu perfil:</h3>
                        <br></br>
                        <div className="registerBox">
                            <br></br>
                            <div className="regileft">
                                <span className="registoContent"> Nome:</span>
                                <input placeholder="" className="registerInput" />
                                <span className="registoContent"> Apelido:</span>
                                <input placeholder="" className="registerInput" />
                                <h5 className='notaa'>*Exemplo: Nome: João, Apelido:Manuel Correia</h5>
                                <span className="registoContent2">Telemóvel:</span>
                                <input placeholder="" className="registerInput" />
                                <span className="registoContent"> Nº de contribuinte:</span>
                                <input placeholder="" className="registerInput" />
                                <span className="registoContent">Email:</span>
                                <input placeholder="" className="registerInput" />
                            
                            </div>
                            <div className="regicenter">
                                <span className="registoContent"> Código postal:</span>
                                <input placeholder="" className="registerInput" />
                                <span className="registoContent">Morada:</span>
                                <textarea placeholder="" className="registerInput1" />
                                <span className="registoContent">Localidade:</span>
                                <input placeholder="" className="registerInput" />
                                <label htmlFor="password" className="registoContent">Password</label>
                                <input type="password" className="registerInput" id="password" value={password}
                                    onChange={(e) => { setPassword(e.target.value) }} />
                                
                                <label htmlFor="confirmPassword" className="registoContent">Confirmar Password</label>
                                <input type="password"  className="registerInput" id="confirmPassword" value={cPassword}
                                    onChange={handleCPassword} />
                            </div>
                            <div className="regiright">
                                <span className="registoContentf"> Foto de perfil:</span> 
                                <img
                                    className="registoContent3"
                                    src={apagar}
                                    alt=""
                                />
                            </div>
                            
                            
                            <div className="checks">
                        <label for="checkbox">
                            <br></br>
                            <br></br>
                            <input class="checkbox-option" type="checkbox" value=""/> Aceito os termos e condições 
                            </label>
                            <br></br>
                            <label for="checkbox">
                                <input class="checkbox-option" type="checkbox" value=""/> Pretendo receber newsletter.
                            </label>
                            <br></br>
                            <button onClick={()=>{navigate("/createProfile")}} className="registerButton">Sign Up</button>

                            <span className='linetext'>Já tem conta?</span>
                            <button onClick={()=>{navigate("/Login")}} className="registerButton">Login</button>
                    </div>

                           
                       
                        
                    </div>
                   
                    </div>
                
            </div>

        </div>
        <FooterContainer/>
    </>    
    );
}
