import Topbar from "../../components/topbar/Topbar";
import "./profile.css"
import Rightbar from "../../components/rightbar/Rightbar"
import ProfileContent from "../../components/profileContent/ProfileContent"
import FooterContainer from "../../components/FooterContainer/FooterContainer";
import React,{useRef, useEffect} from 'react'

export default function Profile(){
    const top=useRef(null);
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
            <Topbar />
            <div className="backgroundd"ref={top}>
                <div className="profileContainer" >
                    <ProfileContent/>
                    <Rightbar/>
                </div>
            </div>
            <div className="footerp">
                <FooterContainer/>
            </div>
        </>
    );
}