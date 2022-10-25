
// import React from 'react';
// import {Checkbox,Collapse} from 'antd';
// const{Panel} = Collapse
// const cidades = [

//     {
//         "_id":1,
//         "name":"Lisboa"
//     },
//     {
//         "_id":2,
//         "name":"Viseu"
//     },
//     {
//         "_id":3,

//         "name":"Porto"
//     },
//     {
//         "_id":4,
//         "name":"Braga"
//     },
//     {
//         "_id":5,
//         "name":"Aveiro"
//     },
//     {
//         "_id":6,
//         "name":"Setubal"
//     },
//     {
//         "_id":7,
//         "name":"Algarve"
//     },

//     {
//         "_id":8,
//         "name":"Evora"
//     },
//     {
//         "_id":9,
//         "name":"Beja"
//     },
//     {
//         "_id":10,
//         "name":"Vila Real"
//     },
//     {
//         "_id":11,
//         "name":"Guarda"
//     },
//     {
//         "_id":12,
//         "name":"Leiria"
//     }
// ]
// const renderCheckboxList = () => {cidades.map ((value,index)=>(
//     <React.Fragment key={index}>
//         <Checkbox 
//             onChange
//             type="checkbox"
//             checked
//         />
//         <span>{value.name}</span>
//     </React.Fragment>
// ))}

// function CheckBox(){
//     return (
//         <div>
//             <Collapse defaultActiveKey={['0']} >
//                 <Panel header key="1">
//                     {renderCheckboxList()}
//                 </Panel>
//             </Collapse>
//         </div>
//     )
// }
// export default CheckBox