import React, { useState } from "react";
import "./profileContent.css";
import Images from "../images/Images";

import { height } from "@mui/system"

export default function ProfileContent() {

  const [selectedImg, setSelectedImg] = useState(Images[0]);

  return (
    <div className="profileContent">
      <div className="profileContentWrapper"></div> 
      <div className="profileContentCover">
        <img
          className="profileCotentImg"
          src="assets/post/7.jpg"
          alt=""
        />
      </div>
      <div className="profileInfo">
      <p class="div">
        <span className="profileContentDesc">Nome:</span>
        <span className="profileContentValue"> Filipa Freixo</span>
        <br></br>
        <br></br>
        <div className="profileContentItem">
          <span className="profileContentDesc">Descrição:</span>
          <span className="profileContentValue"> Olá, sou a Filipa Freixa, sou fotografa há 5 anos!</span>
        </div>
        <br></br>
        <div className="profileContentItem">
          <span className="profileContentDesc">Tipo de evento:</span>
          <span className="profileContentValue"> Batizado, Casamento.</span>
        </div>
        <br></br>  
        <span className="profileContentDesc">Link de portfólio:</span>
        <a href="https://pluralsight.com"> http://MyPortfólio_Filipa_Freixo.com</a>
      </p>
      <br></br>
      <br></br>
      </div>
      <div className="containerr">
        <img src={selectedImg} atl="Selected" className="selected"/>
        <div className="imgContainer">
          {Images.map((img,index) => (
            <img style={{border: selectedImg === img ? "4px solid green" : ""}} key={index} src={img} alt="Image"
            onClick={() => setSelectedImg(img)}
            />  
          ))}
          
        </div>
      </div>
    </div>
  )
}

          