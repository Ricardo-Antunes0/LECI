import "./topbarc.css"
import Flag from 'react-world-flags'
import {Chat,Notifications,Logout} from "@mui/icons-material";
import {useNavigate} from "react-router-dom"


export default function Topbar() {
  let navigate=useNavigate();
  return (
    <div className="topbarContainer">
      <div className="topbarLeft">
      <img onClick={()=>{navigate("/home")}} src="/assets/logo.png" alt="" className="topbarlogo" />
      
      
      
      </div>
      <div className="topbarLinks">
            <span onClick={()=>{navigate("/home")}} className="topbarLink">Homepage</span>
            <span onClick={()=>{navigate("/photographers/Retrato")}}  className="topbarLink">Photographers</span>
        </div>
      
      <div className="topbarCenter">
        <span onClick={()=>{navigate("/home")}} className="logoo"> Photi</span>
      </div>
      <div className="topbarRight">

        <div className="topbarIcons">
            <div className="topbarIconItem1">
            <img onClick={()=>{navigate("/profile")}}  src="/assets/person/1.jpeg" alt="" className="topbarImg" />
            </div>
            <div className="topbarIconItem">
            <Chat onClick={()=>{navigate("/chat")}} size="large">
                </Chat>
              
            </div>
            <div className="topbarIconItem">
              <Notifications  size="large">
                </Notifications>
              <span className="topbarIconBadge"> 3</span>
            </div>
            <div className="topbarIconItem">
              <Logout onClick={()=>{navigate("/")}} size="large">
                </Logout>
                   
              
            </div>
            
        </div>
        <Flag className="Flagg" code="pt" fallback={ <span>Unknown</span> } height="20"/>
      </div>
    </div>
  )
}
