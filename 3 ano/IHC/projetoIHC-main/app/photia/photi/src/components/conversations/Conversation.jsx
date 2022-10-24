import "./conversation.css"
import {useNavigate} from "react-router-dom"

export default function Conversation() {
    
    let navigate=useNavigate();
  return (
    <div onClick={()=>{navigate("/chat/FilipaFreixo")}}  className="conversation">
        <img className="conversationImg" src="/assets/post/7.jpg" alt=""/>
        
        <span className="conversationName">Filipa Freixo</span>


    </div>
  )
}
