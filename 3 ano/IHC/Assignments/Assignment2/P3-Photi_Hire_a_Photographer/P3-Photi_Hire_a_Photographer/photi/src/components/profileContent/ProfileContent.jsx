import React, { useState } from "react";
import "./profileContent.css";
import Images from "../images/Images";

import { height } from "@mui/system"

export default function ProfileContent() {

  const [selectedImg, setSelectedImg] = useState(Images[0]);
  const [nome,setNome]=useState()

  const armazenar=(chave,valor)=>{
      localStorage.setItem(chave,valor)
  }
  const consultar=(chave)=>{
      return localStorage.getItem(chave)
  }
  function tipo(){
    var tipo1=localStorage.getItem('ls_tipo1');
    var tipo2=localStorage.getItem('ls_tipo2');
    var tipo3=localStorage.getItem('ls_tipo3');
    var tipo4=localStorage.getItem('ls_tipo4');
    var tipo5=localStorage.getItem('ls_tipo5');
    var tipo6=localStorage.getItem('ls_tipo6');

    var list="";
    var f=true;
    if (tipo1==="true"){
      f=false
      list +="Casamento" 
    }
    if (tipo2==="true"){
      if (f===false){
        list+=", "
      }
      else f=false
      list +="Batizado" 
    }
    if (tipo3==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="Retrato" 
    }
    if (tipo4==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="Evento" 
    }
    if (tipo5==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="Publicidade" 
    }
    if (tipo6==="true"){
      if (f===false){
        list+=", "
      }else f=false
      list +="Festa" 
    }
    return list;

  }
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
        <span className="profileContentDesc">Nome: </span>
        <span className="profileContentValue" >{consultar('ls_nome')} </span>
        <br></br>
        <br></br>
        <div className="profileContentItem">
          <span className="profileContentDesc">Descrição:</span>
          <span className="profileContentValue"> {consultar('ls_desc')} </span>
        </div>
        <br></br>
        <div className="profileContentItem">
          <span className="profileContentDesc">Tipo de evento:</span>
          <span className="profileContentValue"> {tipo()}</span>
        </div>
        <br></br>  
        <span className="profileContentDesc">Link de portfólio:</span>
        <a href="https://pluralsight.com"> {consultar('ls_port')}</a>
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

          