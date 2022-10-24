import "./message.css"

export default function Message({own}) {
  return (
    <div className={own ? "message own" : "message"}>
        <div className="messageTop">
            <img className="messageImg" src="/assets/post/7.jpg" alt=""/>
            <p className="messageText">Boa tarde cara Filipa Freixo, gostaria de saber se teria disponibilidade para fotografar um casamento no dia 19 de Agosto de 2022. Desde já, muito obrigado! Cumprimentos</p>
            
        </div>
        <div className="messageBottomm">
          <p className="messagebot">há 1 hora</p>
        </div>
    </div>
  )
}
