import Topbar from "../../components/topbarnolog/Topbar";
import "./profile.css"
import Rightbar from "../../components/rightbarFilipa/Rightbarfilipa";
import ProfileContent from "../../components/profileContentfilipa/ProfileContentfilipa";
import FooterContainer from "../../components/FooterContainer/FooterContainer";
import React, { Component,useState,useEffect,useRef} from 'react'

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
        <div className='gcl'ref={top}>
            <Topbar/>
            <div className="backgroundd">
                <div className="profileContainer">
                    <ProfileContent/>
                    <Rightbar/>
                </div>
            </div>
            <div className="footerp">
                <FooterContainer/>
            </div>
        </div>
        </>
    );
}