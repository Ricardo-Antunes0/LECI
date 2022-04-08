void delay(unsigned int ms);

int main(void)
{
    unsigned int cnt1 = 0;
    unsigned int cnt5 = 0;
    unsigned int cnt10 = 0;

    while(1){
        delay(100); // 10 hz            100 ms = 0.1s    
        if(cnt10 % 2 == 1)cnt5++;
        if(cnt10 % 10 == 0) cnt1++;
        cnt10++;
        printInt(cnt10, 10 | 5 << 16);
        printChar(' ');
        printInt(cnt5, 10 | 5 << 16);
        printChar(' ');
        printInt(cnt1, 10 | 5 << 16);
        printChar('\r');
    }
    return 0;
}

void delay(unsigned int ms){
    resetCoreTimer();
    while(readCoreTimer() < 200000 * ms);

}
