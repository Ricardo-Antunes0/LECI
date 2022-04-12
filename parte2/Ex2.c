#include <detpic32.h>

void delay(int ms);void delay(int ms){
    for (;ms > 0; ms--){
        resetCoreTimer();
        readCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int main(void){

    unsigned char segment;
    TRISB = TRISB & 0x80FF;
    TRISD = TRISD & 0xFF9F;

    LATDbits.LATD5 = 1;
    LATDbits.LATD6 = 0;

    LATB = LATB & 0x80FF;
    int i;
    

    while(1){
        segment = 1;

        for(i = 0; i < 7; i++){
            LATB = (LATB & 0x80FF) | (((unsigned int)(segment)) << 8);
            delay(500);
            segment = segment << 1;
        }
        LATDbits.LATD5 = !LATDbits.LATD5;   // se o RD5 = 1 passa a 0 e vice versa
        LATDbits.LATD6 = !LATDbits.LATD6;   // se o RD6 = 0 passa a 1 e vice versa
    }
    return 0;
}
