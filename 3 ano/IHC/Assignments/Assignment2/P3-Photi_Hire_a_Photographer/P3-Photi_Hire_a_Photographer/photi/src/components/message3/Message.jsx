import "./message.css"

export default function Message({own}) {
  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">Seria para o dia inteiro, fotografar os preparitivos e durante a festa toda! Queria tabém, se possível, no final da noite, poder passar as fotos tiradas durante o dia num projetor.</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">há 1 hora</p>
        </div>
    </div>
  )
}
