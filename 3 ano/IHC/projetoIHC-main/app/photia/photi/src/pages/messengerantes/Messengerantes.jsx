import "./messengerantes.css";
import Topbar from "../../components/topbar/Topbar";
import Conversationa from "../../components/conversations/Conversationa";
import Conversation2 from "../../components/conversations/Conversation2";
import Conversation3 from "../../components/conversations/Conversation3";
import Conversation4 from "../../components/conversations/Conversation4";
import Conversation5 from "../../components/conversations/Conversation5";

import FooterContainer  from "../../components/FooterContainer/FooterContainer";

export default function Messenger() {
  return (
    <>
        <Topbar />
        <div className="messenger">
            <div className="menu">
                <div className="menuWrapper">
                    <input placeholder="Search for a name" className="menuInput"/>
                    <div className="fa">
                        <Conversationa/>
                        
                    </div>
                    <Conversation2/>
                    <Conversation3/>
                    <Conversation4/>
                    <Conversation5/>
                </div>
            </div>
            <div className="box">
                <div className="boxWrapper">
                    <span className="aviso">Clique num chat para ver a conversa</span>
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
