#include <detpic32.h>

void delay(int ms);void delay(int ms){
    for (;ms > 0; ms--){
        resetCoreTimer();
        readCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int main(void){

    TRISB = TRISB & 0x80FF;
    TRISD = TRISD & 0xFF9F;

    LATDbits.LATD5 = 1;
    LATDbits.LATD6 = 0;

    LATB = LATB & 0x80FF;

    while(1){
        char in = getChar();

        switch(in){
            case '0':
                LATDbits.LATD5 = 1;
                LATDbits.LATD6 = 0;
                break;
            case '1':
                LATDbits.LATD5 = 0;
                LATDbits.LATD6 = 1;
                break;
            case 'a': 
                LATB = (LATB & 0x80FF) | 0x0100;
                break;
            case 'b':
                LATB = (LATB & 0x80FF) | 0x0200;
                break;
            case 'c':
                LATB = (LATB & 0x80FF) | 0x0400;
                break;
            case 'd':
                LATB = (LATB & 0x80FF) | 0x0800;
                break;
            case 'e':
                LATB = (LATB & 0X80FF) | 0x1000;
                break;
            case 'f':
                LATB = (LATB & 0x80FF) | 0x2000;
                break;
            case 'g':
                LATB = (LATB & 0x80FF) | 0x4000;
                break;
        }
    }
    return 0;
}