import Topbar from "../../components/topbar/Topbar";
import "./profile.css"
import Rightbar from "../../components/rightbar/Rightbar"
import ProfileContent from "../../components/profileContent/ProfileContent"
import FooterContainer from "../../components/FooterContainer/FooterContainer";


export default function Profile(){
    return (
        <>
            <Topbar/>
            <div className="backgroundd">
                <div className="profileContainer">
                    <ProfileContent/>
                    <Rightbar/>
                </div>
            </div>
            <div className="footerp">
                <FooterContainer/>
            </div>
        </>
    );
}