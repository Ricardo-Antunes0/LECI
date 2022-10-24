import React from 'react'
import Footer from './footerr'

import {Facebook,Instagram,YouTube,Twitter} from "@mui/icons-material";
import './footercontainer.css'

export default function FooterContainer() {
    return (
        <Footer>
            <Footer.Wrapper>
            <Footer.Row>
                <Footer.Column>
                <Footer.Title>About Us</Footer.Title>
                    <Footer.Link href="#">Story</Footer.Link>
                    <Footer.Link href="#">Clients</Footer.Link>
                    <Footer.Link href="#">Terms & Conditions</Footer.Link>
                    <Footer.Link href="#">Privacy Policy</Footer.Link>
                </Footer.Column>
                <Footer.Column>
                <Footer.Title>Services</Footer.Title>
                    <Footer.Link href="#">Marketing</Footer.Link>
                    <Footer.Link href="#">Consulting</Footer.Link>
                    <Footer.Link href="#">Development</Footer.Link>
                    <Footer.Link href="#">Design</Footer.Link>
                </Footer.Column>
                <Footer.Column>
                <Footer.Title>Contact Us</Footer.Title>
                    <Footer.Link href="#">Portugal</Footer.Link>
                    <Footer.Link href="#">Spain</Footer.Link>
                    <Footer.Link href="#">FAQ</Footer.Link>
                    <Footer.Link href="#">Support</Footer.Link>
                </Footer.Column>
                <Footer.Column>
                <Footer.Title>Social</Footer.Title>
                    <Footer.Link href="#"><Facebook className="icons" />Facebook</Footer.Link>
                    <Footer.Link href="#"><Instagram className="icons" />Instagram</Footer.Link>
                    <Footer.Link href="#"><YouTube className="icons" />Youtube</Footer.Link>
                    <Footer.Link href="#"><Twitter className="icons" />Twitter</Footer.Link>
                </Footer.Column>
            </Footer.Row>
            </Footer.Wrapper>
        </Footer>
    )
}
