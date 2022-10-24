import "./message.css"

export default function Message({own}) {
  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">Sim, é possível. Em que local seria a cerimónia?</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">há 40 minutos</p>
        </div>
    </div>
  )
}
