import "./rightbar.css"
import StarBorderIcon from '@mui/icons-material/StarBorder';


import {useNavigate} from "react-router-dom"

export default function Rightbar() {
  let navigate=useNavigate();
  return (
    <>
      <div className="rightbarInfo">
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Rating:</span>
          <span className="rightbarInfoValue">3 </span>
          <StarBorderIcon className="Iconn"/>
        </div>
        <br></br>
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Price:</span>
          <span className="rightbarInfoValue">€€-€</span>
        </div>
        <br></br>
        <div className="rightbarInfoItem">
          <span className="rightbarInfoRating">Location:</span>
          <span className="rightbarInfoValue">Aveiro</span>
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
