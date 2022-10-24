import socket
import threading
import random
import sqlite3
from cryptography.hazmat.primitives import hashes
import base64
import os
from cryptography.fernet import Fernet
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
from flask import flash

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

def send(msg,password,pass_):
    mes=''
    etapa=""
    try:
        HEADER=64 
        PORT=8081
        FORMAT='utf-8'
        DISCONNECT_MSG="!DISCONECT"
        SERVER="127.0.0.1"
        ADDRESS=(SERVER,PORT)

        client=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        client.connect(ADDRESS)

        username=msg
        len_pass=len(password)
        N=9
        x=len_pass//N
        len_final=len_pass%N
        
        divided_pass=pass_div(N,password,x,len_final)

        #Enviar username
        etapa="Enviar username"
        message = msg.encode(FORMAT)
        msg_length = len(message)
        send_length= str(msg_length).encode(FORMAT)
        send_length += b' ' * (HEADER - len(send_length))
        
        client.send(send_length)
        client.send(message)
        value=32
        b_size=64
        vezes=0
        #Receber Challenge 1
        etapa="Receber Challenge 1"
        msg_length = client.recv(HEADER).decode(FORMAT) 
        if msg_length:

            msg_length = int(msg_length)
            challenge1=client.recv(msg_length).decode(FORMAT)

            if challenge1 == DISCONNECT_MSG:
                flash('Não foi possível conectar ao servidor')
                return False    

            #Gerar C2    
            etapa="Gerar C2"
            challenge2=''
            lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

            for i in range(value):
                challenge2+=random.choice(lista)

            
            #Calcular Hash pass_div,c1,c2
            etapa="Calcular Hash pass_div,c1,c2"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[0].encode(FORMAT))
            digest.update(challenge1.encode(FORMAT))
            digest.update(challenge2.encode(FORMAT))
            message=digest.finalize()

            #Enviar c2 para server
            etapa="Enviar c2 para server"
            msg_length = len(challenge2)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            challenge2_=challenge2.encode(FORMAT)
            
            client.send(send_length)
            client.send(challenge2_)

            #Enviar res para server
            etapa="Enviar res em c2 para server"
            msg_length = len(message)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            
            client.send(send_length)
            client.send(message)

            #Receber Challenge 3 e hash(c2,c3)
            etapa="Receber Challenge 3 e hash(c2,c3)"
            msg_length = client.recv(HEADER).decode(FORMAT) 
            if msg_length:

                msg_length = int(msg_length)
                challenge3=client.recv(msg_length).decode(FORMAT)

                if challenge3 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            msg_length = client.recv(HEADER).decode(FORMAT) 

            if msg_length:

                msg_length = int(msg_length)

                #print(msg_length)
                res1=client.recv(msg_length)

                if res1 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            #calcular hash(c2,c3)
            etapa="Calcular hash(c2,c3)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[1].encode(FORMAT))
            digest.update(challenge2.encode(FORMAT))
            digest.update(challenge3.encode(FORMAT))
            message=digest.finalize()

            #Verificar hash(c2,c3)
            etapa="Verificar hash(c2,c3)"
            if res1 != message:
                value=random.randint(0,20)
            else:
                vezes+=1

            #Gerar c4
            etapa="Gerar c4"
            challenge4=''
            lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

            for i in range(value):
                challenge4+=random.choice(lista)

            #calcular hash(c3,c4)
            etapa="Calcular hash(c3,c4)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[2].encode(FORMAT))
            digest.update(challenge3.encode(FORMAT))
            digest.update(challenge4.encode(FORMAT))
            message=digest.finalize()

            #Enviar c4 para server
            etapa="Enviar c4 para server"
            msg_length = len(challenge4)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            challenge4_=challenge4.encode(FORMAT)
            
            client.send(send_length)
            client.send(challenge4_)

            #Enviar res para server
            etapa="Enviar res em c4 para server"
            msg_length = len(message)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            
            client.send(send_length)
            client.send(message)

            #Receber Challenge 5 e hash(c4,c5)
            etapa="Receber Challenge 5 e hash(c4,c5)"
            msg_length = client.recv(HEADER).decode(FORMAT) 
            if msg_length:

                msg_length = int(msg_length)
                challenge5=client.recv(msg_length).decode(FORMAT)

                if challenge5 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            msg_length = client.recv(HEADER).decode(FORMAT) 

            if msg_length:

                msg_length = int(msg_length)

                #print(msg_length)
                res2=client.recv(msg_length)

                if res2 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            #calcular hash(c4,c5)
            etapa="Calcular hash(c4,c5)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[3].encode(FORMAT))
            digest.update(challenge4.encode(FORMAT))
            digest.update(challenge5.encode(FORMAT))
            message=digest.finalize()

            #Verificar hash(c4,c5)
            etapa="Verificar hash(c4,c5)"
            if res2 != message:
                value=random.randint(0,20)
            else:
                vezes+=1

            #Gerar c6
            
            etapa="Gerar c6"
            challenge6=''
            lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

            for i in range(value):
                challenge6+=random.choice(lista)

            #calcular hash(c5,c6)
            etapa="Calcular hash(c5,c6)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[4].encode(FORMAT))
            digest.update(challenge5.encode(FORMAT))
            digest.update(challenge6.encode(FORMAT))
            message=digest.finalize()

            #Enviar c6 para server
            etapa="Enviar c6 para server"
            msg_length = len(challenge6)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            challenge6_=challenge6.encode(FORMAT)
            
            client.send(send_length)
            client.send(challenge6_)

            #Enviar res para server
            etapa="Enviar res em c6 para server"
            msg_length = len(message)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            
            client.send(send_length)
            client.send(message)

            #Receber Challenge 7 e hash(c6,c7)
            etapa="Receber Challenge 7 e hash(c6,c7)"
            msg_length = client.recv(HEADER).decode(FORMAT) 
            if msg_length:

                msg_length = int(msg_length)
                challenge7=client.recv(msg_length).decode(FORMAT)

                if challenge7 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            msg_length = client.recv(HEADER).decode(FORMAT) 

            if msg_length:

                msg_length = int(msg_length)

                #print(msg_length)
                res3=client.recv(msg_length)

                if res3 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            #calcular hash(c6,c7)
            etapa="Calcular hash(c6,c7)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[5].encode(FORMAT))
            digest.update(challenge6.encode(FORMAT))
            digest.update(challenge7.encode(FORMAT))
            message=digest.finalize()

            #Verificar hash(c6,c7)
            etapa="Verificar hash(c6,c7)"
            if res3 != message:
                value=random.randint(0,20)
            else:
                vezes+=1

            #Gerar c8
            etapa="Gerar c8"
            challenge8=''
            lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

            for i in range(value):
                challenge8+=random.choice(lista)

            #calcular hash(c7,c8)
            etapa="Calcular hash(c7,c8)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[6].encode(FORMAT))
            digest.update(challenge7.encode(FORMAT))
            digest.update(challenge8.encode(FORMAT))
            message=digest.finalize()

            #Enviar c8 para server
            etapa="Enviar c8 para server"
            msg_length = len(challenge8)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            challenge8_=challenge8.encode(FORMAT)
            
            client.send(send_length)
            client.send(challenge8_)

            #Enviar res para server
            etapa="Enviar res em c8 para server"
            msg_length = len(message)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            
            client.send(send_length)
            client.send(message)

            #Receber Challenge 9 e hash(c8,c9)
            etapa="Receber Challenge 9 e hash(c8,c9)"
            msg_length = client.recv(HEADER).decode(FORMAT) 
            if msg_length:

                msg_length = int(msg_length)
                challenge9=client.recv(msg_length).decode(FORMAT)

                if challenge9 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            msg_length = client.recv(HEADER).decode(FORMAT) 

            if msg_length:

                msg_length = int(msg_length)


                res4=client.recv(msg_length)

                if res4 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False

            #calcular hash(c8,c9)
            etapa="Calcular hash(c8,c9)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[7].encode(FORMAT))
            digest.update(challenge8.encode(FORMAT))
            digest.update(challenge9.encode(FORMAT))
            message=digest.finalize()

            #Verificar hash(c8,c9)
            etapa="Verificar hash(c8,c9)"
            if res4 != message:
                value=random.randint(0,20)
            else:
                vezes+=1
            
            #Gerar c10
            etapa="Gerar c10"
            challenge10=''
            lista=['a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','x','X','y','Y','z','Z','1','2','3','4','5','6','7','8','9','0']

            for i in range(value):
                challenge10+=random.choice(lista)

            #calcular hash(c9,c10)
            etapa="Calcular hash(c9,c10)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[8].encode(FORMAT))
            digest.update(challenge9.encode(FORMAT))
            digest.update(challenge10.encode(FORMAT))
            message=digest.finalize()

            #Enviar c10 para server
            etapa="Enviar c10 para server"
            msg_length = len(challenge10)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            challenge10_=challenge10.encode(FORMAT)
            
            client.send(send_length)
            client.send(challenge10_)

            #Enviar res para server
            etapa="Enviar res em c10 para server"
            msg_length = len(message)
            send_length= str(msg_length).encode(FORMAT)
            send_length += b' ' * (HEADER - len(send_length))
            
            client.send(send_length)
            client.send(message)

            #Receber Challenge 11 e hash(c10,c11)
            etapa="Receber Challenge 11 e hash(c10,c11)"
            msg_length = client.recv(HEADER).decode(FORMAT) 
            if msg_length:

                msg_length = int(msg_length)
                challenge11=client.recv(msg_length).decode(FORMAT)

                if challenge11 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False
            
            msg_length = client.recv(HEADER).decode(FORMAT)

            if msg_length:

                msg_length = int(msg_length)

                res5=client.recv(msg_length)

                if res5 == DISCONNECT_MSG:
                    flash('Não foi possível conectar ao servidor')
                    return False

            #calcular hash(c10,c11)
            etapa="Calcular hash(c10,c11)"
            digest= hashes.Hash(hashes.BLAKE2b(b_size))

            digest.update(divided_pass[9].encode(FORMAT))
            digest.update(challenge10.encode(FORMAT))
            digest.update(challenge11.encode(FORMAT))
            message=digest.finalize()

            #Verificar hash(c10,c11)
            etapa="Verificar hash(c10,c11)"
            if res5 == message:
                vezes+=1
            if vezes==5:
                print("enviar para db")
                msg_length = client.recv(HEADER).decode(FORMAT) 
                if msg_length:

                    msg_length = int(msg_length)
                    mes=client.recv(msg_length).decode(FORMAT)

                    if mes == DISCONNECT_MSG:
                        flash('Não foi possível conectar ao servidor')
                        return False
            

                pass_ = pass_.encode(FORMAT)

                salt = os.urandom(16)

                kdf = PBKDF2HMAC(

                    algorithm=hashes.SHA256(),

                    length=32,

                    salt=salt,

                    iterations=390000,

                )

                key = base64.urlsafe_b64encode(kdf.derive(pass_))

                f = Fernet(key)

                pw = f.encrypt(password.encode(FORMAT))


                # algorithm = algorithms.ChaCha20(key, salt)

                # cipher = Cipher(algorithm, mode=None)

                # encryptor = cipher.encryptor()

                # pw = encryptor.update(b' ' *password)


                # print(salt)
                # print(pw)

                connect=sqlite3.connect('uap_data.db')
                c = connect.cursor()

                c.execute ('CREATE TABLE IF NOT EXISTS data (username text,password text)' )
                connect.commit()

                c.execute("SELECT * FROM data WHERE username=?",(username,))
                teste=c.fetchone()
                if teste:
                    if teste[0][1]!=pw:
                        c.execute("UPDATE data SET password=? WHERE username=?", (pw,username))
                        connect.commit()
                    else:
                    
                        if (mes=="True"):
                            c.execute("SELECT * FROM data WHERE username=?",(username,))
                            teste=c.fetchone()
                            c.close()
                            connect.close()
                            return teste
                        
                        else:
                            c.close()
                            connect.close()
                            return False
                        
                else:
                    c.execute("INSERT INTO data (username,password) VALUES (?,?)", (username,pw))
                    connect.commit()
                
                if (mes=="True"):
                    c.execute("SELECT * FROM data WHERE username=?",(username,))
                    teste=c.fetchone()
                    c.close()
                    connect.close()
                    return teste
                        
                else:
                    c.close()
                    connect.close()
                    return False
                        
            else:
                print("insucesso")
                #conn.close()
                return  False  
        return False
    except Exception as e:
        if mes=='userNone':
            flash('Invalid or incomplete credentials, try again!', category='error')

        x="Error"+ str(e)
        y="Etapa: "+ str(etapa)
        flash(x+" "+y,category='error')

        return False
#send(DISCONNECT_MSG) # para continuar sempre ligado basta apagar esta linha