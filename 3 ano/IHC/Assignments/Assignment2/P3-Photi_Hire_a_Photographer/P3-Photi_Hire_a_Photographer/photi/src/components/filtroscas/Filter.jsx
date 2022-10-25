import React from 'react';
import "./Filter.css"
import Topbar from "../topbar/Topbar"
import{useRef,useState,useEffect} from "react"
import {FooterContainer} from "../FooterContainer/footer";
import {useNavigate} from "react-router-dom"
import * as ReactDOM from 'react-dom';
var photographers = [
    {
        name: 'Bruno Andrade',
        description: 'Adoro de tudo um pouco sobre multimédia',
        rating: 2,
        price: '2',
        city: 'Aveiro',
        events: ['Batizados','Publicidades', 'Casamentos','Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Joana Pedreiro',
        description: 'Design Moda e Fotografia são a minha paixão',
        rating: 5,
        price: '1',
        city: 'Aveiro',
        events: ['Batizados', 'Casamentos','Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Maria Vasconcelos',
        description: 'O meu pai passou-me o bichinho da fotografia!',
        rating: 4,
        price: '2',
        city: 'Lisboa',
        events: ['Festas', 'Publicidades'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Rafael Mendes',
        description: 'Adoro a felicidade dos meus clientes após uma bela foto.',
        rating: 4,
        price: '2',
        city: 'Lisboa',
        events: ['Batizados', 'Publicidades'],
        img: "/assets/person/1.jpeg"
    },

    {
        name: 'Beatriz Costa',
        description: 'Podes ver o meu portfólio no meu perfil! Não te vais arrepender.',
        rating: 5,
        price: '3',
        city: 'Évora',
        events: ['Batizados', 'Casamentos'],
        img: "/assets/person/1.jpeg"
    },

    {
        name: 'João Ferreira',
        description: 'Casamentos e Retratos é a minha praia.',
        rating: 4,
        price: '3',
        city: 'Aveiro',
        events: ['Retratos', 'Casamentos'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Fábio Coelho',
        description: 'Gosto de beber uns finos depois de tirar umas fotos',
        rating: 5,
        price: '1',
        city: 'Viseu',
        events: ['Festas', 'Eventos', 'Casamentos'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Marco Pereira',
        description: 'Gosto é de fotografar meninas ;)',
        rating: 1,
        price: '1',
        city: 'Setubal',
        events: ['Eventos', 'Publicidades', 'Casamentos'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Luis Carvalho',
        description: 'Sou fotógrafo à 7 anos, tenho preferência para fotografias de Retrato.',
        rating: 5,
        price: '3',
        city: 'Aveiro',
        events: ['Retratos', 'Casamentos','Batizados'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Raquel Mendes',
        description: 'Fotografar alegra-me o dia. Venha me alegrar!',
        rating: 3,
        price: '2',
        city: 'Porto',
        events: ['Eventos', 'Publicidades', 'Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Nuno Lebre',
        description: 'Fotografar Animais é super giro!',
        rating: 3,
        price: '2',
        city: 'Leiria',
        events: ['Eventos', 'Publicidades', 'Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Pedro Augusto',
        description: 'Fotografar e praia são os meus passatempos!',
        rating: 1,
        price: '1',
        city: 'Leiria',
        events: ['Casamentos', 'Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Ricardo Gomes',
        description: 'Adoro uma boa Fotografada',
        rating: 4,
        price: '2',
        city: 'Aveiro',
        events: ['Festas', 'Casamentos'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Diana Domingues',
        description: 'A noite e o luar são o meu mar.',
        rating: 4,
        price: '2',
        city: 'Aveiro',
        events: ['Retratos', 'Casamentos','Publicidades'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Bruno Caseiro',
        description: 'Eu estou para a fotografia, como o Ronaldo está para o Futebol.',
        rating: 5,
        price: '2',
        city: 'Aveiro',
        events: ['Retratos', 'Casamentos','Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Leonor Teodoro',
        description: 'Adoro Festas e o sentimento de comunidade',
        rating: 3,
        price: '1',
        city: 'Aveiro',
        events: ['Batizados', 'Casamentos','Festas'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Tiago Teodoro',
        description: 'Adoro o sentimento de comunidade',
        rating: 5,
        price: '2',
        city: 'Aveiro',
        events: ['Retratos', 'Casamentos','Festas','Eventos'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Filipa Freixo',
        description: 'Gosto de tirar fotos de casamentos, adoro o ar da natureza',
        rating: 3,
        price: '2',
        city: 'Aveiro',
        events: ['Casamentos', 'Festas'],
        img : "/assets/post/7.jpg"
        
    },
    {
        name: 'Henrique Ferreira',
        description: 'Adoro Aventuras e fotos de casamentos',
        rating: 4,
        price: '1',
        city: 'Aveiro',
        events: ['Batizados', 'Casamentos'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Inês Anjos',
        description: 'Ultimamente adoro a natureza e o sentimentos de liberdade',
        rating: 4,
        price: '1',
        city: 'Aveiro',
        events: ['Retratos', 'Casamentos','Publicidades'],
        img: "/assets/person/1.jpeg"
    },
    {
        name: 'Bárbara Garcia',
        description: 'Adoro ser livre, adoro o mar e um bom evento',
        rating: 3,
        price: '1',
        city: 'Aveiro',
        events: ['Eventos', 'Casamentos','Festas'],
        img: "/assets/person/1.jpeg"
    },


    {
        name: 'Raul Lemos',
        description: 'Fotos comigo não vai dizer que não',
        rating: 1,
        price: '1',
        city: 'Aveiro',
        events: ['Batizados','Eventos', 'Casamentos','Festas'],
        img: "/assets/person/1.jpeg"
    },
    
    
];

const List2=photographers;
let ListEvents=[];
export default function Filter (){
    {

        const [check1,setCheck1]=useState()
        const [check2,setCheck2]=useState(true)
        const [check3,setCheck3]=useState()
        const [check4,setCheck4]=useState()
        const [check5,setCheck5]=useState()
        const [check6,setCheck6]=useState()
        const [check7,setCheck7]=useState()
        const [check8,setCheck8]=useState()
        const [check9,setCheck9]=useState()
        const [check10,setCheck10]=useState()
        const [check11,setCheck11]=useState()
        const [check12,setCheck12]=useState()
        const [check13,setCheck13]=useState()
        const [check14,setCheck14]=useState()
        const [city1,setCity1]=useState()
        const [ordem,setOrdem]=useState("Ratingmaior")
        
        useEffect(() => {
            armazenar('ls_ordem',ordem);
            armazenar('ls_city',city1);
            armazenar('ls_check14',check14);
            armazenar('ls_check13',check13);
            armazenar('ls_check12',check12);
            armazenar('ls_check11',check11);
            armazenar('ls_check10',check10);
            armazenar('ls_check9',check9);
            armazenar('ls_check8',check8);
            armazenar('ls_check7',check7);
            armazenar('ls_check6',check6);
            armazenar('ls_check5',check5);
            armazenar('ls_check4',check4);
            armazenar('ls_check3',check3);
            armazenar('ls_check2',check2);
            armazenar('ls_check1',check1);
            changeFilter();
            
          }, []);

        const armazenar=(chave,valor)=>{
            localStorage.setItem(chave,valor)
        }
        const consultar=(chave)=>{
            localStorage.getItem(chave)
        }
        let navigate=useNavigate();
        const [list, setList] = React.useState(photographers);
        const [city, setCity] = React.useState();
        const [eventos, setEventos] = React.useState([]);

        function filterCity(city) {
            const newList=photographers.filter(p=> p.events.includes('Publicidades'));
            setList(newList);
        }

 
        function changeCity(e) {
            const city = e.target.value;
            setCity(city);
            filterCity(city);
        }
        function changeFilter(){

            var ls_check1=localStorage.getItem('ls_check1');
            var ls_check2=localStorage.getItem('ls_check2');
            var ls_check3=localStorage.getItem('ls_check3');
            var ls_check4=localStorage.getItem('ls_check4');
            var ls_check5=localStorage.getItem('ls_check5');
            var ls_check6=localStorage.getItem('ls_check6');
            var ls_check7=localStorage.getItem('ls_check7');
            var ls_check8=localStorage.getItem('ls_check8');
            var ls_check9=localStorage.getItem('ls_check9');
            var ls_check10=localStorage.getItem('ls_check10');
            var ls_check11=localStorage.getItem('ls_check11');
            var ls_check12=localStorage.getItem('ls_check12');
            var ls_check13=localStorage.getItem('ls_check13');
            var ls_check14=localStorage.getItem('ls_check14');
            var ls_city=localStorage.getItem('ls_city');
            var ls_ordem=localStorage.getItem('ls_ordem');
            ListEvents=[];
            const newList=[];
            if (ls_check1==="true"){
                const l=photographers.filter(p=> p.events.includes('Retratos'));
                if(ListEvents.length==0){
                    ListEvents.push("Retratos");
                }else{
                    ListEvents.push(", Retratos");
                }
                
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
            }
            if (ls_check2==="true"){
                const l=photographers.filter(p=> p.events.includes('Casamentos'));
                if(ListEvents.length==0){
                    ListEvents.push("Casamentos");
                }else{
                    ListEvents.push(", Casamentos");
                }
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
            }
            if (ls_check3==="true"){
                const l=photographers.filter(p=> p.events.includes('Batizados'));
                if(ListEvents.length==0){
                    ListEvents.push("Batizados");
                }else{
                    ListEvents.push(", Batizados");
                }
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
            }
            if (ls_check4==="true"){
                const l=photographers.filter(p=> p.events.includes('Festas'));
                if(ListEvents.length==0){
                    ListEvents.push("Festas");
                }else{
                    ListEvents.push(", Festas");
                }
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
            }
            if (ls_check5==="true"){
                const l=photographers.filter(p=> p.events.includes('Publicidades'));
                if(ListEvents.length==0){
                    ListEvents.push("Publicidades");
                }else{
                    ListEvents.push(", Publicidades");
                }
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
            }
            if (ls_check6==="true"){
                const l=photographers.filter(p=> p.events.includes('Eventos'));
                if(ListEvents.length==0){
                    ListEvents.push("Eventos");
                }else{
                    ListEvents.push(", Eventos");
                }
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
            }
            console.log(newList);
            if (newList.length===0){
                console.log(newList);
                const l= photographers;
                l.map((ph) => {
                    if(newList.includes(ph)){}else{
                        newList.push(ph)
                    }
                })
                
            }
            console.log(newList);
            if (ls_check7!=="true"&&ls_check8!=="true"&&ls_check9!=="true"&&ls_check10!=="true"&&ls_check11!=="true"&&ls_check12!=="true"){

            }else{
                if (ls_check7!=="true"){
                    newList.map((ph) => {
                        if(ph.rating===5){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }
                if (ls_check8!=="true"){
                    newList.map((ph) => {
                        if(ph.rating===4){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }
                if (ls_check9!=="true"){
                    newList.map((ph) => {
                        if(ph.rating===3){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }
                if (ls_check10!=="true"){
                    newList.map((ph) => {
                        if(ph.rating===2){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }
                if (ls_check11!=="true"){
                    newList.map((ph) => {
                        if(ph.rating===1){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }

            }
            if (ls_check12!=="true"&&ls_check13!=="true"&&ls_check14!=="true"){

            }else{
                if (ls_check12!=="true"){
                    newList.map((ph) => {
                        if(ph.price==='3'){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }
                if (ls_check13!=="true"){
                    newList.map((ph) => {
                        if(ph.price==='2'){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }
                if (ls_check14!=="true"){
                    newList.map((ph) => {
                        if(ph.price==='1'){
                            var index = newList.indexOf(ph); 
                            delete newList[index];
                        }
                        
                    })
                }


            }

            if(ls_city!=="undefined"){
                newList.map((ph) => {
                    if(ph.city!==ls_city){
                        var index = newList.indexOf(ph); 
                        delete newList[index];
                    }
                    
                })
            }
            if(ls_ordem!=="undefined"){
                if (ls_ordem==="Ratingmaior"){
                    newList.sort((a,b) => a.rating<b.rating ? 1: -1);
                }
                if (ls_ordem==="Ratingmenor"){
                    newList.sort((a,b) => a.rating>b.rating ? 1: -1);
                }
                if (ls_ordem==="Preçomaior"){
                    newList.sort((a,b) => a.price<b.price ? 1: -1);
                }
                if (ls_ordem==="Preçomenor"){
                    newList.sort((a,b) => a.price>b.price ? 1: -1);
                }
            }
            setList(newList);


        }
        function preco(e){
            var preco="";
                    if(e.price==='1'){
                        preco='€';
                    }
                    else if(e.price==='2'){
                        preco='€€---€';
                    }
                    else{
                        preco='€€€---€€'
                    }
            return preco;
            
        }

        const List = ({ list }) => (
            
            list.map(el =>
                <div class="header" onClick={()=>{navigate("/profileFilipeFreixo")}} >
                    <div className="profilepic">
                        <img onClick={()=>{navigate("/profileFilipeFreixo")}} src={el.img} alt="" className="profileimg" />
                    </div>
                    <div class="info">
                        <h2>{el.name}</h2>
                        <p>{el.description}</p>
                        <h2>Rating: {el.rating}★     Preço:{preco(el)}</h2>
                    </div>
                </div>
            )
            
        );
        return (<>
            <Topbar/>
            <div class="filterbar">

                <div class="cidades">
                    <h1>Filtros:</h1>
                    <h2> Cidades : </h2>
                    <div class = "dropdownn">
                        
                        <select name="format" id="format" value={city1} onChange={(e)=>setCity1(e.target.value)} onClick={()=>{armazenar('ls_ordem',ordem);armazenar('ls_city',city1);armazenar('ls_check14',check14);armazenar('ls_check13',check13);armazenar('ls_check12',check12);armazenar('ls_check11',check11);armazenar('ls_check10',check10);armazenar('ls_check9',check9);armazenar('ls_check8',check8);armazenar('ls_check7',check7);armazenar('ls_check6',check6);armazenar('ls_check5',check5);armazenar('ls_check4',check4);armazenar('ls_check3',check3);armazenar('ls_check2',check2);armazenar('ls_check1',check1);changeFilter()}} >
                            <option selected disabled>Escolha uma cidade </option>
                            <option value="Aveiro">Aveiro</option>
                            <option value="Lisboa">Lisboa</option> 
                            <option value="Porto">Porto</option> 
                            <option value="Braga">Braga</option>
                            <option value="Viseu">Viseu</option>
                            <option value="Algarve">Algarve</option>
                            <option value="Setubal">Setubal</option>
                            <option value="Coimbra">Coimbra</option>
                            <option value="Guarda">Guarda</option>
                            <option value="Cascais">Cascais</option>
                            <option value="Évora">Evora</option>
                        </select>
                    </div>
                    
                    <div class="checkconteiner">
                        <h3> Eventos : </h3>
                        <input type="checkbox"  align="right" name="Retratos"  value={check1} onChange={(e)=>setCheck1(e.target.checked)}/> Retrato&nbsp;&nbsp; <br></br>
                        <input type="checkbox" defaultChecked={check2} align="right" name="Casamentos" value={check2} onChange={(e)=>setCheck2(e.target.checked)}/> Casamentos&nbsp;&nbsp;<br></br>
                        <input type="checkbox" align="right" name="Batizados" value={check3} onChange={(e)=>setCheck3(e.target.checked)}/> Batizados&nbsp;&nbsp;<br></br>
                        <input type="checkbox" align="right" name="Festas" value={check4} onChange={(e)=>setCheck4(e.target.checked)}/> Festas&nbsp;&nbsp;<br></br>
                        <input type="checkbox" align="right" name="Publicidades" value={check5} onChange={(e)=>setCheck5(e.target.checked)}/> Publicidades&nbsp;&nbsp;<br></br>
                        <input type="checkbox" align="right" name="Eventos" value={check6} onChange={(e)=>setCheck6(e.target.checked)}/> Eventos&nbsp;&nbsp;<br></br>
                        <p></p>
                        <h3> Rating : </h3>
                        <input type="checkbox" name="5" value={check7} onChange={(e)=>setCheck7(e.target.checked)}/> 5★&nbsp;&nbsp; <br></br>
                        <input type="checkbox" name="4" value={check8} onChange={(e)=>setCheck8(e.target.checked)}/> 4★&nbsp;&nbsp;<br></br>
                        <input type="checkbox" name="3" value={check9} onChange={(e)=>setCheck9(e.target.checked)}/> 3★&nbsp;&nbsp;<br></br>
                        <input type="checkbox" name="2" value={check10} onChange={(e)=>setCheck10(e.target.checked)}/> 2★&nbsp;&nbsp;<br></br>
                        <input type="checkbox" name="1" value={check11} onChange={(e)=>setCheck11(e.target.checked)}/> 1★&nbsp;&nbsp;<br></br>

                        <h3> Preço : </h3>
                        <input type="checkbox" name="dollar3" value={check12} onChange={(e)=>setCheck12(e.target.checked)}/> €€€---€€&nbsp;&nbsp; <br></br>
                        <input type="checkbox" name="dollar2" value={check13} onChange={(e)=>setCheck13(e.target.checked)}/> €€---€&nbsp;&nbsp;<br></br>
                        <input type="checkbox" name="dollar1" value={check14} onChange={(e)=>setCheck14(e.target.checked)}/> €&nbsp;&nbsp;<br></br>
                    </div>
                    <button onClick={()=>{armazenar('ls_ordem',ordem);armazenar('ls_city',city1);armazenar('ls_check14',check14);armazenar('ls_check13',check13);armazenar('ls_check12',check12);armazenar('ls_check11',check11);armazenar('ls_check10',check10);armazenar('ls_check9',check9);armazenar('ls_check8',check8);armazenar('ls_check7',check7);armazenar('ls_check6',check6);armazenar('ls_check5',check5);armazenar('ls_check4',check4);armazenar('ls_check3',check3);armazenar('ls_check2',check2);armazenar('ls_check1',check1);changeFilter()}} className="bttn pesqButton">Filtrar</button>
                    <div style ={{backgroundColor:"Black",color:"cyan"}}>

                    </div>
                </div>
                <div class = "filtered_content">
                    <div class="header">
                    <h2>Evento: {ListEvents}</h2>
                        <select name="format" id="format" value={ordem} onChange={(e)=>setOrdem(e.target.value)} onClick={()=>{armazenar('ls_ordem',ordem);armazenar('ls_city',city1);armazenar('ls_check14',check14);armazenar('ls_check13',check13);armazenar('ls_check12',check12);armazenar('ls_check11',check11);armazenar('ls_check10',check10);armazenar('ls_check9',check9);armazenar('ls_check8',check8);armazenar('ls_check7',check7);armazenar('ls_check6',check6);armazenar('ls_check5',check5);armazenar('ls_check4',check4);armazenar('ls_check3',check3);armazenar('ls_check2',check2);armazenar('ls_check1',check1);changeFilter()}} >
                            <option selected disabled>Escolha uma ordem</option>
                            <option value="Ratingmaior">Rating ↑ Maior</option>
                            <option value="Ratingmenor">Rating ↑ Menor</option> 
                            <option value="Preçomaior">Preço ↑ Maior</option> 
                            <option value="Preçomenor">Preço ↑ Menor</option>
                        </select>
                    </div>
                    <div id="filter_profile1" class = "filter_profile1">
                        <List list={list}/>

                    </div>
                </div>

            
            </div>
            
        </>);
    }
}