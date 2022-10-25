import React, {Component} from 'react';
import Topbar from "../../components/topbarnolog/Topbar"
import FooterContainer from "../../components/FooterContainer/FooterContainer";
import './forgot.css'
import {useNavigate} from "react-router-dom"
import axios from 'axios';


class Forgot extends Component{
    handleSubmit = e => {
        e.preventDefault();
        const data = {
            email: this.email
        };

        axios.post('forgot',data).then(
            res => {
                console.log(res)
            }
        ).catch(
            err => {
               console.log(err); 
            }
        )

    };

    render(){
        
        return(
            <>
            <Topbar />
                <div className="forgotWrape" >
                    <div className="forgotLeft" >
                        <div className="forgotBox">
                            <form onSubmit={this.handleSubmit}>
                                <div className="forgot">
                                    <h3>Forgot password</h3>
                                </div>
                                <br></br>
                                <div className="Form-group">
                                    <label>Introduza o seu email:</label>
                                    <br></br>
                                    <input type="email" className="form-control" placeholder="Email"
                                        onChange={e => this.email = e.target.value}/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </>
            )
    }
}
export default function Forgot2(){
    let navigate=useNavigate();
    return (
        <>
        <Forgot/>
        <div>
            <button onClick={()=>(alert('Recebeu um email a confirmar o reset da password!!!'), navigate("/Login"))} className = "buttonSub">Submit</button>
        </div>
        <FooterContainer/>
        </>
    )
}

       