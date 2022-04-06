import asyncio
import getpass
import json
import os
from game import *
from shape import Shape
from collections import deque
import time

import websockets
from time import sleep

baixo=[[b,30] for b in range(10)]
lados=[[0,l] for l in range(30)]
lados.extend([[9,l] for l in range(30)])
grid=baixo + lados

def bumpiness(heights):
    bump=0
    heights.reverse()

    for x,y in heights:
        if x==8:
            break
        x_,y_=heights[x]
        bump+=abs(y_-y) 
        
        
    return bump


def completeLines(state):
    li=0
    lines=[[1,0],[2,0],[3,0],[4,0],[5,0],[6,0],[7,0],[8,0],[9,0],[10,0],[11,0],[12,0],[13,0],[14,0],[15,0],[16,0],[17,0],[18,0],[19,0],[20,0],[21,0],[22,0],[23,0],[24,0],[25,0],[26,0],[27,0],[28,0],[29,0],[30,0]]

    for x,y in state:
        for x_,y_ in lines:
            if y==x_:
                lines[x_-1]=[x_,y_+1]
    for m,n in lines:
        if n==8:
            li+=1
    return li
def almostlines(state):
    li=0
    for x,y in state:
        aux=0
        for x_,y_ in state:
            if y==y_:
                aux+=1
            if aux>=7:
                li+=1
                break
    return li
              
def get_max_height_column(state):
    heights=get_max_height_pos_column(state)
    for x,y in heights:
        heights[x-1]=[x,30-y]
    return heights


def TotalHoles(state,heights,a):
    heights.reverse()
    h=0
    for x_h,y_h in heights:
        for x_s,y_s in state:
            if x_h==x_s:
                h+=1
    return a-h

def get_max_height_pos_column(state):
    starter_positions=[[1,30],[2,30],[3,30],[4,30],[5,30],[6,30],[7,30],[8,30]]
    for x,y in state: 
        for x_,y_ in starter_positions:
            if x_==x:
                if y_>y:
                    starter_positions[x_-1] = [x_,y]
    return starter_positions


#
#Heuristica
#
def heuristics(state,letra):
    heights = get_max_height_column(state)
    a=aggregateHeight(heights)
    b=completeLines(state)
    c=TotalHoles(state,heights,a)
    d=bumpiness(heights)
    if letra=="s":
        return (-0.410066*a)+(0.760666*b)+(-0.45663*c) + (-0.284483*d)
    if letra=="z":
        return (-0.410066*a)+(0.760666*b)+(-0.45663*c) + (-0.284483*d)
    if letra=="i":
        return (-0.210066*a)+(0.760666*b) + (-0.35663*c) + (-0.084483*d)
    if letra=="o":
        return (-0.310066*a)+(0.760666*b)+(-0.45663*c) + (-0.284483*d)
    if letra=="j":
        return (-0.410066*a)+(0.760666*b) + (-0.45663*c)+ (-0.184483*d)
    if letra=="t":
        return (-0.310066*a)+(0.760666*b)+(-0.45663*c) + (-0.284483*d)
    if letra=="l":
        return (-0.410066*a)+(0.760666*b) + (-0.35663*c)+ (-0.184483*d)
    return (-0.510066*a)+(0.760666*b) + (-0.45663*c) + (-0.124483*d)

def aggregateHeight(heights):
    total=0
    for x,y in heights:
        total=total+y
    return total

def pheight(heights):
    return maxHeightColumns(heights) - minHeightColumns(heights)
    
def maxHeightColumns(heights):
    max=0
    for x,y in heights:
        if y>max:
            max=y
    return max

def minHeightColumns(heights):
    min=30
    for x,y in heights:
        if y<min:
            min=y
    return min

def getPiece(state):
    if (state['piece']==[[4, 2], [4, 3], [5, 3], [5, 4]]):
        letra="s"
        currentPiece=SHAPES[0] #S
    elif ( state['piece']==[[4, 2], [3, 3], [4, 3], [3, 4]]):
        currentPiece=SHAPES[1] #Z
        letra="z"
    elif (state['piece']==[[2, 2], [3, 2], [4, 2], [5, 2]]):
        currentPiece=SHAPES[2] #I
        letra="i"
    elif ( state['piece']==[[3, 3], [4, 3], [3, 4], [4, 4]]):
        letra="o"
        currentPiece=SHAPES[3]   #O    
    elif( state['piece']==[[4, 2], [5, 2], [4, 3], [4, 4]]):
        letra="j"
        currentPiece=SHAPES[4]  #J
    elif( state['piece']==[[4, 2], [4, 3], [5, 3], [4, 4]]):
        letra="t"
        currentPiece=SHAPES[5]  #T
    elif(state['piece']==[[4, 2], [4, 3], [4, 4], [5, 4]]):
        letra="l"
        currentPiece=SHAPES[6]  #L
    else:
        currentPiece=None
        letra="l"
    return currentPiece,letra

####################
def bestplace(state,grid):
    MaxScore=-9999
    start=time.time()
    if (state['game']) == []:
        if (state['piece'] == [[4, 3], [4, 4], [5, 4], [4, 5]]):
            best_position = {'x':-3, 'rotate':3}
        elif (state['piece'] == [[4, 3], [3, 4], [4, 4], [3, 5]]):
            best_position = {'x':3, 'rotate':1}
        elif (state['piece'] == [[2, 3], [3, 3], [4, 3], [5, 3]]):
            best_position = {'x':-3, 'rotate':0}
        elif (state['piece'] == [[3, 4], [4, 4], [3, 5], [4, 5]]):
            best_position = {'x':-2, 'rotate':0}
        elif (state['piece'] == [[4, 3], [5, 3], [4, 4], [4, 5]]):
            best_position = {'x':4, 'rotate':2}
        elif (state['piece'] == [[4, 2], [4, 3], [5, 3], [4, 4]]):
            best_position = {'x':3, 'rotate':3}
        else:
            best_position = {'x':-3, 'rotate':0}
        return best_position
    currentPiece,letra=getPiece(state)
    
    if(currentPiece is None): return None

    currentPiece.set_pos(2,0)
    i=0
    best_position={'x':0,'rotate':0}
  
    while i<len(currentPiece.plan):
        if(time.time()-start)>0.15:
            return best_position
        left=0
        right=0
        piece=[]
        min_X=10
        max_X=-1
        for (x,y) in currentPiece.positions:
            piece+=[[x,y]]
            if x<min_X:
                min_X=x
            if x>max_X:
                max_X=x

        left=-(min_X-1)
        right=(8-max_X)
        while left <= right:
            if(time.time()-start)>0.15:
                return best_position
            position=[[x+left,y] for [x,y] in piece]
            virtualgame=pos(state['game'],grid,position)
            score=heuristics(virtualgame,letra)

            if(score>MaxScore):
                MaxScore=score
                #print(" FOI O MEHLOR")
                best_position={'x':left,'rotate':i}
            if(time.time()-start)>0.15:
                return best_position
            
            left+=1
        currentPiece.rotate()
        
        i+=1
    #print (best_position)
    return best_position

def pos(game,grid,piece):
    for y in range(30):
        new_piece=[[x,y+1] for [x,y] in piece]
        
        if any(fragment in new_piece for fragment in game) or any(fragment in new_piece for fragment in grid):
            
            return game+piece
        else:
            piece=new_piece

def move_piece(state,new_piece,grid):
    s=""
    if(state['piece']==None):
        new_piece=True
    if (new_piece and state['piece']!=None):
        new_piece=False
        bestmove=bestplace(state,grid)
        if(bestmove is None):
            return ""
        if (bestmove!=None):
            x=bestmove.get('x')
            rotation=bestmove.get('rotate')
            r=0
            while(r<rotation):
                s+='w'
                r+=1
            
            if x>0:
                right=0
                while right<x:
                    s+= 'd'
                    right+=1

            elif x<0:
                left=0
                while left>x:
                    s+= 'a'
                    left-=1
                
            if(rotation is None):
                rotation=0
            
            s+='s'
            #print(stack)
    return s
            
async def agent_loop(server_address="localhost:8000", agent_name="student"):
    async with websockets.connect(f"ws://{server_address}/player") as websocket:
        # Receive information about static game properties
        await websocket.send(json.dumps({"cmd": "join", "name": agent_name}))
        i=0
        new_piece=True
        while True:
            try:
                state = json.loads(await websocket.recv())  # receive game update, this must be called timely or your game will get out of sync with the server
                #game=posiçoes ocupadas, piece=posiçao peça atual, next_pieces=proximas peças, 
                key=""
                if i>1:

                    if(state!=oldstate):
                        if(state['game_speed']>13):
                            
                            move_it_move_it=move_piece(state,new_piece,grid)
                            while move_it_move_it=="":
                                state = json.loads(await websocket.recv())
                                move_it_move_it=move_piece(state,new_piece,grid)
                            if len(move_it_move_it)>0:
                                
                                for key in move_it_move_it:
                            
                                    json.loads(await websocket.recv())
                                    await websocket.send(json.dumps({"cmd": "key", "key": key}))

                        else:
                            move_it_move_it=move_piece(state,new_piece,grid)
                            
                            if len(move_it_move_it)>0:
                                
                                for key in move_it_move_it:
                                    await websocket.send(json.dumps({"cmd": "key", "key": key}))
                                    json.loads(await websocket.recv())
                            
                            
                            
                            
                i=i+1
                oldstate=state
                await websocket.send(
                    json.dumps({"cmd": "key", "key": key})
                )  # send key command to server - you must implement this send in the AI agent
            except websockets.exceptions.ConnectionClosedOK:
                print("Server has cleanly disconnected us")
                return


# DO NOT CHANGE THE LINES BELLOW
# You can change the default values using the command line, example:
# $ NAME='arrumador' python3 client.py
loop = asyncio.get_event_loop()
SERVER = os.environ.get("SERVER", "localhost")
PORT = os.environ.get("PORT", "8000")
NAME = os.environ.get("NAME", getpass.getuser())
loop.run_until_complete(agent_loop(f"{SERVER}:{PORT}", NAME))