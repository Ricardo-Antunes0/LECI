import "./message.css"

export default function Message({own}) {

  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">{localStorage.getItem('ls_mes')}</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">agora mesmo</p>
        </div>
    </div>
  )
}
