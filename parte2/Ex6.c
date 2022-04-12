#include <detpic32.h>

int main(void){


    static const char display7Scodes[]={
                                    0x3F, //0
                                    0x06, //1
                                    0x5B, //2
                                    0x4F, //3
                                    0x66, //4
                                    0x6D, //5
                                    0x7D, //6
                                    0x07, //7
                                    0x7F, //8
                                    0x6F, //9
                                    0x77, //A
                                    0x7C, //b
                                    0x39, //C
                                    0x5E, //d
                                    0x79, //E
                                    0x71  //F
                                };

    TRISB = TRISB | 0x000F; // RB0-RB3
    TRISB = TRISB & 0x80FF; //RB8-RB14 output
    TRISD = TRISD & 0xFF9F; //RD5-RD6 output
    LATDbits.LATD5 = 1;     //select display low == display 5

    int index;
    int value;
    while(1){
        index = PORTB & 0x000F;
        value = display7Scodes[index];
        LATB = (LATB & 0x80FF) |((unsigned int)value) << 8;
    }
    return 0;
}