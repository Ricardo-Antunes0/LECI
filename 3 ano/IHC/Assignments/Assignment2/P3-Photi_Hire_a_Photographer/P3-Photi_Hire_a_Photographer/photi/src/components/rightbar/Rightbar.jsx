import "./rightbar.css"
import StarBorderIcon from '@mui/icons-material/StarBorder';
import {useNavigate} from "react-router-dom"

export default function Rightbar() {
  let navigate=useNavigate();

  const consultar=(chave)=>{
    return localStorage.getItem(chave)
  }
  function preco(){
    var preco1=localStorage.getItem('ls_preco1');
    var preco2=localStorage.getItem('ls_preco2');
    var preco3=localStorage.getItem('ls_preco3');
    var list="";
    var f=true;
    if (preco1==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="€" 
    }
    if (preco2==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="€€-€" 
    }
    if (preco3==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="€€€-€€" 
    }
    return list;

  }
  return (
    <>
      <div className="rightbarInfo">
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Rating:</span>
          <span className="rightbarInfoValue"> Ainda não tem reviews </span>
          <StarBorderIcon className="Iconn"/>
        </div>
        <br></br>
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Price:</span>
          <span className="rightbarInfoValue">{preco()}</span>
        </div>
        <br></br>
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Location:</span>
          <span className="rightbarInfoValue">{consultar('ls_cidade')}</span>
        </div>
        <br></br>
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Para comunicar clique aqui:</span>
          <a onClick={()=>{navigate("/chat/FilipaFreixo")}}  class="btnn btnn1">CHAT</a>
        </div>
      </div>
    </>
  );
}

