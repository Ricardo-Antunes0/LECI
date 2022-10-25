import "./message.css"

export default function Message({own}) {
  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">A cerimónia seria na Igreja de Nossa Senhora da Ajuda e a festa seria na Adega Rama.</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">há 30 minutos</p>
        </div>
    </div>
  )
}
