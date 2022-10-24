import Homenolog from "./pages/homenolog/Homenolog";
import Home from "./pages/home/Home"
import Messenger from "./pages/messenger/Messenger"
import Messenger2 from "./pages/messenger2/Messenger"
import Messengerantes from "./pages/messengerantes/Messengerantes"
import Profile from "./pages/profile/Profile"
import ProfileFilipe from "./pages/profileFilipe/Profile"
import ProfileFilipe2 from "./pages/profileFilipenl/Profile"
import Register from "./pages/registoPhoto/RegisterPhoto"
import Retrato from "./pages/filterret/Filterpage"
import Retratonolog from "./pages/filternologret/Filterpage"
import Casamento from "./pages/filtercas/Filterpage"
import Casamentonolog from "./pages/filternologcas/Filterpage"
import Batizado from "./pages/filterbat/Filterpage"
import Batizadonolog from "./pages/filternologbat/Filterpage"
import Festa from "./pages/filterfestas/Filterpage"
import Festanolog from "./pages/filternologfestas/Filterpage"
import Publicidade from "./pages/filterpub/Filterpage"
import Publicidadenolog from "./pages/filternologpub/Filterpage"
import Evento from "./pages/filtereven/Filterpage"
import Eventonolog from "./pages/filternologeven/Filterpage"
import Registo from "./pages/registo/Registo"
import Registoph from "./pages/registo_ph/Registo"
import Login from "./pages/login/Login"
import Forgot from "./pages/forgot/Forgot"
import {BrowserRouter as Router,Routes,Route} from "react-router-dom"


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/"element={<Homenolog/>}/>
        <Route path="/home"element={<Home/>}/>
        <Route path="/chat/FilipaFreixo"element={<Messenger/>}/>
        <Route path="/chat"element={<Messengerantes/>}/>
        <Route path="/chat/FilipaFreixo2"element={<Messenger2/>}/>
        <Route path="/profile"element={<Profile/>}/>
        <Route path="/profileFilipeFreixo"element={<ProfileFilipe/>}/>
        <Route path="/profileFilipeFreixo2"element={<ProfileFilipe2/>}/>
        <Route path="/createProfile"element={<Register/>}/>
        <Route path="/photographers/Retrato"element={<Retrato/>}/>
        <Route path="/photographersnl/Retrato"element={<Retratonolog/>}/>
        <Route path="/photographers/Casamento"element={<Casamento/>}/>
        <Route path="/photographersnl/Casamento"element={<Casamentonolog/>}/>
        <Route path="/photographers/Batizado"element={<Batizado/>}/>
        <Route path="/photographersnl/Batizado"element={<Batizadonolog/>}/>
        <Route path="/photographers/Festa"element={<Festa/>}/>
        <Route path="/photographersnl/Festa"element={<Festanolog/>}/>
        <Route path="/photographers/Publicidade"element={<Publicidade/>}/>
        <Route path="/photographersnl/Publicidade"element={<Publicidadenolog/>}/>
        <Route path="/photographers/Evento"element={<Evento/>}/>
        <Route path="/photographersnl/Evento"element={<Eventonolog/>}/>

        <Route path="/Registo"element={<Registo/>}/>
        <Route path="/Registoph"element={<Registoph/>}/>
        <Route path="/Login"element={<Login/>}/>
        <Route path="/Forgot"element={<Forgot/>}/>

      </Routes>
    </Router>
  );
}

export default App;