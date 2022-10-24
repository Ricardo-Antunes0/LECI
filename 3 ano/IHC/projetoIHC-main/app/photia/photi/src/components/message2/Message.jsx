import "./message.css"

export default function Message({own}) {
  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">Boa tarde, sim, no dia 19 de Agosto de 2022 terei disponibilidade para tal. Seria para o dia todo ou apenas para durante o casamento?</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">há 1 hora</p>
        </div>
    </div>
  )
}
