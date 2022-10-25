import "./topbarn.css"
import Flag from 'react-world-flags'
import {useNavigate} from "react-router-dom"
export default function Topbar() {
  let navigate=useNavigate();
  return (
    <div className="topbarContainer">
      <div className="topbarLeft">
        <div className="logodiv">
        <img onClick={()=>{navigate("/")}} src="/assets/logo.png" alt="" className="topbarlogo" />
        </div>
      
      
      <div className="topbarLinks">
            <span onClick={()=>{navigate("/")}} className="topbarLinka">Homepage</span>
            <span onClick={()=>{navigate("/photographersnl/Retrato")}}  className="topbarLinka">Photographers</span>
        </div>
     
      </div>
      
      <div className="topbarCenter">
        <span onClick={()=>{navigate("/")}} className="logoo"> Photi</span>
      </div>
      <div className="topbarRight">


      <span onClick={()=> {navigate("/Login")}} className="topbarLinkk">Login</span>

      <Flag className="Flagg2" code="pt" fallback={ <span>Unknown</span> } height="20"/>
      </div>
    </div>
  )
}
