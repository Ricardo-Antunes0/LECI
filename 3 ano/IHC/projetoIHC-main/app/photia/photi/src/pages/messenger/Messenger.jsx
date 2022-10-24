import "./messenger.css";
import Topbar from "../../components/topbarchat/Topbar";
import Conversation from "../../components/conversations/Conversation";
import Conversation2 from "../../components/conversations/Conversation2";
import Conversation3 from "../../components/conversations/Conversation3";
import Conversation4 from "../../components/conversations/Conversation4";
import Conversation5 from "../../components/conversations/Conversation5";
import Message1 from "../../components/message1/Message";
import Message2 from "../../components/message2/Message";
import Message3 from "../../components/message3/Message";
import Message4 from "../../components/message4/Message";
import Message5 from "../../components/message5/Message";
import Message6 from "../../components/message6/Message";
import FooterContainer  from "../../components/FooterContainer/FooterContainer";
import{useRef,useState} from "react"

import {Navigate, useNavigate} from "react-router-dom"

export default function Messenger() {
    const [mes,setMes]=useState()
        
        
        const armazenar=(chave,valor)=>{
            localStorage.setItem(chave,valor)
        }
        const consultar=(chave)=>{
            localStorage.getItem(chave)
        }
        let navigate=useNavigate();
  return (
    <>
        <Topbar />
        <div className="messenger">
            <div className="menu">
                <div className="menuWrapper">
                    <input placeholder="Search for a name" className="menuInput"/>
                    <Conversation/>
                    <Conversation2/>
                    <Conversation3/>
                    <Conversation4/>
                    <Conversation5/>
                </div>
            </div>
            <div className="box">
                <div className="boxWrapper">
                    <div className="boxTop">
                        <Message1 own={true}/>
                        <Message2/>
                        <Message3 own={true}/>
                        <Message4 />
                        <Message5 own={true}/>
                        <Message6/>
                    </div>
                    <div className="boxBottom">
                        <textarea className="chatMessageInput" placeholder="Escreva algo para enviar..." value={mes} onChange={(e)=>setMes(e.target.value)}/>
                        <button className="chatSubmitButton" onClick={()=>{armazenar('ls_mes',mes);navigate("/chat/FilipaFreixo2")}}>Enviar</button>
                    </div>
                </div>
            </div>
            <div className="right">
                <div className="rightWrapper"></div>
            </div>
        </div>
        <div className="footer">
            <FooterContainer/>
        </div>
        
    </>
  );
}
