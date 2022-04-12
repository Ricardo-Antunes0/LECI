#include <detpic32.h>

void delay(int ms);void delay(int ms){
    for (;ms > 0; ms--){
        resetCoreTimer();
        readCoreTimer();
        while(readCoreTimer() < 20000);
    }
}

int main(void){

    TRISE = TRISE & 0xFFF0; //DEfinir os portos RE0 a RE3 a 0, ou seja como saida
    LATE = LATE & 0XFFF0; //APagar o que esta la nesses portos

    int count = 0;

    while(1){
        LATE = ((LATE & 0XFFF0) | count;   //Reset nos bits 0 a 3 e fazer merge (| count)
        delay(250);
        count++;
        if(count == 16) count = 0;
    }
    return 0;
}