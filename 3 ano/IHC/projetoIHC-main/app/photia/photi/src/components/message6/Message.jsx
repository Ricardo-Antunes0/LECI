import "./message.css"

export default function Message({own}) {
  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">Ok, tudo bem. Teria disponibilidade para nos podermos encontrar para conversar melhor e discutir preços?</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">há 15 minutos</p>
        </div>
    </div>
  )
}
