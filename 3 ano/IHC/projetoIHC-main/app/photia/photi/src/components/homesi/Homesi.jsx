import "./homesi.css"
import background from "./backhome1.jpg"
import background2 from "./backhome.jpg"
import { height, style, width } from "@mui/system"
import ScrollButton from './ScrollButton.js'
import{useRef} from "react"
import {FooterContainer} from "../FooterContainer/footer";
import {useNavigate} from "react-router-dom"


const styles = {
  container: {
      backgroundImage: `url(${background})`,
      backgroundPosition: 'center',
      backgroundSize: 'cover',
      backgroundRepeat: 'no-repeat',
      width: '100vw',
      height: '100vh',

  },
  containerph: {
    backgroundImage: `url(${background2})`,
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: '100vw',
    height: '100vh'
}
};

export default function Homesi() {
  let navigate=useNavigate();
  const foto=useRef(null);
  const scrollToSection = (elementRef) => {
    window.scrollTo({
      top: elementRef.current.offsetTop,
      behavior: "smooth",
    });
  };
  return (
    <>
    <div className="homepage">
      <div className="home" style={styles.container}>
      <div className="fr">
        <span className="frase">Escolha o tipo de fotografia que deseja</span>
      </div>
      
      <div className="events"  >
        <div className="buttons">
          <div className="cima">
            <button onClick={()=>{navigate("/photographers/Retrato")}} className="btn retrato" > Retrato</button>
            
          </div>
          <div className="meio">
            <button onClick={()=>{navigate("/photographers/Casamento")}} className="btn casamento"> Casamento</button>
            <button onClick={()=>{navigate("/photographers/Publicidade")}} className="btn publicidade"> Publicidade</button>
          </div>
          <div className="meio2">
            <button onClick={()=>{navigate("/photographers/Batizado")}} className="btn batizado"> Batizado</button>
            <button onClick={()=>{navigate("/photographers/Evento")}} className="btn evento"> Eventos</button>

          </div>
          <div className="baixo">
            <button onClick={()=>{navigate("/photographers/Festa")}} className="btn festas"> Festas</button>
          </div>

          <div className="textoApp">
            <span className="texto"> Esta aplicação tem como função oferecer ao utilizador uma vasta variedade de fotógrafos
              para diversos tipos de fotografia/eventos. Aqui poderá contratar um fotográfo para a ocasião que pretende,
              pode escolher por localidade, rating, preço, tipo e disponibilidade. Poderá também conversar com os fotógrafos, 
              receber mensagens e responder. 
            </span>
          </div>
          

        </div>
        <div className="scroll">
            <span className="ph">És fotógrafo?</span>
            <button onClick={() => scrollToSection(foto)} className="btscroll">clica aqui</button>  
        </div>
      </div>
      <div className="phts">
        <div className="frph" ref={foto}>
          <span className="fraseph">Quer mostrar o seu trabalho?</span>
        </div>
        <div className="frph2">
          <span className="fraseph">Junte-se a nós!!</span>
        </div>
        <div className="textph">
            <span className="textoph"> Sê o teu próprio chefe! És fotógrafo? Pretendes partilhar o teu trabalho? Queres arranjar clientes? Estás no local certo!!
             Aqui poderás criar aderir ao nosso programa para fotógrafos, onde poderás criar uma conta e um perfil, mostrar alguns
              exemplos do teu trabalho e descrever o mesmo. Poderás também colocar um link para o teu portfólio e receber mensagens
               de clientes que pretendem o seu orçamento ou se mostrem interessados no teu trabalho.  
            </span>
            
        </div>
        <div className="butadere">
          <a onClick={()=>{navigate("/createProfile")}}  class="btn adere">ADERE JÁ</a>
        </div>
        
        

      </div>
      <div className="footer">
          <FooterContainer/>
    </div>
    </div>
    
    </div>
    
    </>
    
  )
}
