import { CheckBox } from "@mui/icons-material";
import React from 'react'
import "./login.css"
import {AddPhotoAlternate} from '@mui/icons-material';
import Topbar from "../../components/topbarnolog/Topbar"
import FooterContainer from "../../components/FooterContainer/FooterContainer";
import {useNavigate} from "react-router-dom"
import background from "./backhome1.jpg"
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import Visibility from "@material-ui/icons/Visibility";
import InputAdornment from "@material-ui/core/InputAdornment";
import VisibilityOff from "@material-ui/icons/VisibilityOff";
import Input from "@material-ui/core/Input";
  
const Login = () => {
  const [values, setValues] = React.useState({
    password: "",
    showPassword: false,
  });
  
  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword });
  };
  
  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };
  
  const handlePasswordChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };
  
  const styles = {
    container: {
        backgroundImage: `url(${background})`,
        backgroundPosition: 'center',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        width: '100vw',
        height: '100vh',
    }
  };


  let navigate=useNavigate();

    return (
        <>
        <Topbar />
        <div className="registe" style={styles.container}>
          <div className="registerWrappe">
            <div className="registerLef">
              <div className="registerBo">
                <span className="login">LOGIN</span>
                  <br></br>
                  <br></br>
                  <span className="registoConten"> Username:</span>
                  <br></br>
                  <input placeholder="" className="registerInpu" />
                  <br></br>
                  <span className="registoConten">Password:</span>
                  <br></br>
                  <Input
                      type={values.showPassword ? "text" : "password"}
                      onChange={handlePasswordChange("password")}
                      value={values.password}
                      endAdornment={
                      <InputAdornment position="end">
                          <IconButton
                          onClick={handleClickShowPassword}
                          onMouseDown={handleMouseDownPassword}
                          >
                          {values.showPassword ? <Visibility /> : <VisibilityOff />}
                          </IconButton>
                      </InputAdornment>
                      }
                  />
                  <br></br>
                  <br></br>
                  <div className="butsr">
                    <button onClick={()=>{navigate("/home")}} className="registerButto">Log In</button>
                    <a className="Forgot" href="Forgot" onClick={()=>{navigate("/Forgot")}}> Forgot password?</a>
                  </div>
                  <div className="rare">
                      <span className="linetext1">Ainda não tem conta?</span>
                      <button onClick={()=>{navigate("/Registo")}} className="registerButto">Registar</button>
                  </div>
                     

                  
              </div>
            </div>
            
          </div>
        </div>
          
        
        <FooterContainer/>
        
    </>    
    );
};

export default Login;