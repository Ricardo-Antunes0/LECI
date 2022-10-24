import socket
import threading
import random

from flask_login.utils import login_user
from website.models import User
from cryptography.hazmat.primitives import hashes
from cryptography.fernet import Fernet
import base64
import hashlib
from main import app
from flask import redirect, render_template,url_for,flash,request
# from flask import Flask, current_app
# from flask_sqlalchemy import SQLAlchemy




HEADER=64 
PORT=8081
SERVER="127.0.0.1"
ADDRESS=(SERVER,PORT)
FORMAT='utf-8'
DISCONNECT_MSG="!DISCONECT"

server=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
server.bind(ADDRESS)

def pass_div(N,password,x,len_final):
    s=''
    lista=[]

    for i in range(N):
        for j in range(x):    
            s+=password[0]
            password=password[1:]
        lista.append(s)
    
    if len_final!=0:
        lista.append(password)
    
    return lista

def handle_client(conn,addr):
    print(f"[NEW CONNECTION] {addr} connected.")
    
    while True:
        vezes=0
        msg_length = conn.recv(HEADER).decode(FORMAT)
        
        if msg_length:
            
            msg_length = int(msg_length)
            msg=conn.recv(msg_length).decode(FORMAT)
            
            if msg == DISCONNECT_MSG:
                return

            print(msg)
            with app.app_context():  
                teste=User.query.filter_by(email=msg).first()
                if teste==None:
                    mes='userNone'
                    message = mes.encode(FORMAT)
                    msg_length = len(message)
                    send_length= str(msg_length).encode(FORMAT)
                    send_length += b' ' * (HEADER - len(send_length))
                    
                    conn.send(send_length)
                    conn.send(message)
                    print("insucesso")
                    conn.close()
                    return
                pwd=teste.password

            
            pass1='pass'
            key = hashlib.md5(pass1.encode()).hexdigest()
            kkey = base64.urlsafe_b64encode(key.encode())
            f = Fernet(kkey)
            password = f.decrypt(pwd)
            password = password.decode('utf-8')
            print(password)

            check=[]
            value=32
            b_size=64
            if password != None:

                len_pass=len(password)
                N=9
                x=len_pass//N
                len_final=len_pass%N
                
                divided_pass=pass_div(N,password,x,len_final)

                #Gerar c1
                challenge1=''
                lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

                for i in range(value):
                    challenge1+=random.choice(lista)

                #Enviar c1
                message = challenge1.encode(FORMAT)
                msg_length = len(message)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message)

                #Receber c2
                msg_length = conn.recv(HEADER).decode(FORMAT)
                    
                if msg_length:
                    
                    msg_length = int(msg_length)
                    challenge2=conn.recv(msg_length).decode(FORMAT)
                    
                    if challenge2 == DISCONNECT_MSG:
                        return

                #Receber resposta (pass,c1,c2)
                msg_length = conn.recv(HEADER).decode(FORMAT) 

                if msg_length:

                    msg_length = int(msg_length)

                    res1=conn.recv(msg_length)

                    if res1 == DISCONNECT_MSG:
                        return


                #Calcular BLAKE2b(pass,c1,c2)
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[0].encode(FORMAT))
                digest.update(challenge1.encode(FORMAT))
                digest.update(challenge2.encode(FORMAT))
                
                message_=digest.finalize()

                #Verificar BLAKE2b(pass,c1,c2)
                if res1 != message_:
                    value=random.randint(21,40)
                else:
                    vezes+=1

                #Gerar C3
                challenge3=''
                lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

                for i in range(value):
                    challenge3+=random.choice(lista)

                #calcular hash pass,c2,c3
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[1].encode(FORMAT))
                digest.update(challenge2.encode(FORMAT))
                digest.update(challenge3.encode(FORMAT))

                message_=digest.finalize()

                #enviar c3
                message = challenge3.encode(FORMAT)
                msg_length = len(message)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message)

                #enviar hash (pass,c2,c3)
                msg_length = len(message_)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message_)

                #Receber c4
                msg_length = conn.recv(HEADER).decode(FORMAT)
                    
                if msg_length:
                    
                    msg_length = int(msg_length)
                    challenge4=conn.recv(msg_length).decode(FORMAT)
                    
                    if challenge4 == DISCONNECT_MSG:
                        return
                #receber hash (pass,c3,c4)
                msg_length = conn.recv(HEADER).decode(FORMAT) 

                if msg_length:

                    msg_length = int(msg_length)

                    res2=conn.recv(msg_length)

                    if res2 == DISCONNECT_MSG:
                        return
                
                #CALCULAR hash (pass,c3,c4)
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[2].encode(FORMAT))
                digest.update(challenge3.encode(FORMAT))
                digest.update(challenge4.encode(FORMAT))

                message_=digest.finalize()

                #verificar
                if res2!=message_:
                    value=random.randint(21,40)
                else:
                    vezes+=1

                #gerar c5
                challenge5=''
                lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

                for i in range(value):
                    challenge5+=random.choice(lista)

                #calcular hash (pass,c4,c5)
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[3].encode(FORMAT))
                digest.update(challenge4.encode(FORMAT))
                digest.update(challenge5.encode(FORMAT))

                message_=digest.finalize()

                #enviar c5 e hash (pass,c4,c5)
                message = challenge5.encode(FORMAT)
                msg_length = len(message)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message)

                msg_length = len(message_)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message_)

                #receber c6
                msg_length = conn.recv(HEADER).decode(FORMAT) 
                    
                if msg_length:
                    
                    msg_length = int(msg_length)
                    challenge6=conn.recv(msg_length).decode(FORMAT)
                    
                    if challenge6 == DISCONNECT_MSG:
                        return

                #Receber hash (pass,c5,c6)
                msg_length = conn.recv(HEADER).decode(FORMAT) 

                if msg_length:

                    msg_length = int(msg_length)

                    res3=conn.recv(msg_length)

                    if res3 == DISCONNECT_MSG:
                        return

                #verificar 
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[4].encode(FORMAT))
                digest.update(challenge5.encode(FORMAT))
                digest.update(challenge6.encode(FORMAT))

                message_=digest.finalize()

                if res3 != message_:
                    value=random.randint(21,40)
                else:
                    vezes+=1
                
                #gerar c7
                challenge7=''
                lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

                for i in range(value):
                    challenge7+=random.choice(lista)
                
                #calcular hash (pass,c6,c7)
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[5].encode(FORMAT))
                digest.update(challenge6.encode(FORMAT))
                digest.update(challenge7.encode(FORMAT))

                message_=digest.finalize()
                
                #enviar c7 e hash (pass,c6,c7)
                message = challenge7.encode(FORMAT)
                msg_length = len(message)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message)

                msg_length = len(message_)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message_)

                #receber c8 e hash (pass,c7,c8)
                msg_length = conn.recv(HEADER).decode(FORMAT) 
                    
                if msg_length:
                    
                    msg_length = int(msg_length)
                    challenge8=conn.recv(msg_length).decode(FORMAT)
                    
                    if challenge8 == DISCONNECT_MSG:
                        return

                msg_length = conn.recv(HEADER).decode(FORMAT) 

                if msg_length:

                    msg_length = int(msg_length)


                    res4=conn.recv(msg_length)

                    if res4 == DISCONNECT_MSG:
                        return
                #verificar
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[6].encode(FORMAT))
                digest.update(challenge7.encode(FORMAT))
                digest.update(challenge8.encode(FORMAT))

                message_=digest.finalize()

                if res4 != message_:
                    value=random.randint(21,40)
                else:
                    vezes+=1

                #gerar c9
                challenge9=''
                lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

                for i in range(value):
                    challenge9+=random.choice(lista)

                #calcular hash (pass,c8,c9)
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[7].encode(FORMAT))
                digest.update(challenge8.encode(FORMAT))
                digest.update(challenge9.encode(FORMAT))

                message_=digest.finalize()
                #enviar c9 e hash (pass,c8,c9)

                message = challenge9.encode(FORMAT)
                msg_length = len(message)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message)

                msg_length = len(message_)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message_)

                #receber c10 e hash (pass,c9,c10)
                msg_length = conn.recv(HEADER).decode(FORMAT)
                    
                if msg_length:
                    
                    msg_length = int(msg_length)
                    challenge10=conn.recv(msg_length).decode(FORMAT)
                    
                    if challenge10 == DISCONNECT_MSG:
                        return

                msg_length = conn.recv(HEADER).decode(FORMAT)

                if msg_length:

                    msg_length = int(msg_length)

                    res5=conn.recv(msg_length)

                    if res5 == DISCONNECT_MSG:
                        return
                #verificar
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[8].encode(FORMAT))
                digest.update(challenge9.encode(FORMAT))
                digest.update(challenge10.encode(FORMAT))

                message_=digest.finalize()

                if res5 != message_:
                    value=random.randint(21,40)
                else:
                    vezes+=1

                #gerar c11
                challenge11=''
                lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

                for i in range(value):
                    challenge11+=random.choice(lista)
                
                #Calcular hash(pass,c10,c11)
                digest= hashes.Hash(hashes.BLAKE2b(b_size))

                digest.update(divided_pass[9].encode(FORMAT))
                digest.update(challenge10.encode(FORMAT))
                digest.update(challenge11.encode(FORMAT))

                message_=digest.finalize()
                
                #enviar c11 e hash (pass,c10,c11)

                message = challenge11.encode(FORMAT)
                msg_length = len(message)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message)

                msg_length = len(message_)
                send_length= str(msg_length).encode(FORMAT)
                send_length += b' ' * (HEADER - len(send_length))
                
                conn.send(send_length)
                conn.send(message_)

                #ver vezes e enviar para site ou nao
                if vezes==5:

                    #Enviar para o site
                    mes='True'
                    message = mes.encode(FORMAT)
                    msg_length = len(message)
                    send_length= str(msg_length).encode(FORMAT)
                    send_length += b' ' * (HEADER - len(send_length))
                    
                    conn.send(send_length)
                    conn.send(message)
                    print("sucesso")
                    conn.close()
                    
                    return 
                else:
                    mes='False'
                    message = mes.encode(FORMAT)
                    msg_length = len(message)
                    send_length= str(msg_length).encode(FORMAT)
                    send_length += b' ' * (HEADER - len(send_length))
                    
                    conn.send(send_length)
                    conn.send(message)
                    print("insucesso")
                    conn.close()
                    return
            else:
                print("Algo errado aconteceu")
            
            print(f"[{addr}] {msg}")
    conn.close()

def start():
    server.listen()
    print(f"[LISTENING] Server is on {SERVER}.")
    
    while True:
        conn,addr=server.accept()
        thread=threading.Thread(target=handle_client,args=(conn,addr))
        thread.start()
        
        print(f"[ACTIVE CONNECTIONS] {threading.activeCount()-1}")

print("[STARTING] server is starting")
start()