.equ SFR_BASE_HI, 0XBF88
.equ PORTB, 0X6050
.equ TRISB, 0X6040
.equ TRISE, 0X6100
.equ LATE, 0X6120

.data
.text
.globl main
main: 

    lui $t0, SFR_BASE_HI

    #COnfigurar RB0 como entrada 
    lw $t1, TRISB($t0)          #BF886040 (BF88 FICA BF880000 e o 0X6040 fica 00006040)
    ori $t1, $t1, 0x0001        #(por o bit 1 a 1)
    sw $t1, TRISB($t0)

    #CONFIGURAR RE0 COMO SAIDA
    lw $t1, TRISE($t0)
    andi $t1, $t1, 0xFFFE      #tudo a 1 menos o 1º bit
    sw $t1, TRISE($t0)


loop: 
    #LEITURA do portB e reseta todos os bits menos o que queremos
    lw $t1, PORTB($t0)
    andi $t1, $t1, 0x0001    #reset bits todos exceto bit 0, n altero

    #Ler LateE e resetar o bit que queremos
    lw $t2, LATE($t0)
    andi $t2, $t2, 0xFFFE    # reset bit 0, o resto nao altero
    or $t2, $t2, $t1         #merge
    sw $t2, LATE($t0)
    j loop

    #EU so quero ler o bit 0 logo os restantes reseto
    #Eu quero escrever no bit 0 do porto E logo tenho de resetar o bit 0 para n dar confusao dps ao escrever nele