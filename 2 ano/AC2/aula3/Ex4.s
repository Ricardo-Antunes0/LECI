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

    #COnfigurar RB3 - 0 como entrada 
    lw $t1, TRISB($t0)          #BF886040 (BF88 FICA BF880000 e o 0X6040 fica 00006040)
    ori $t1, $t1, 0x000F        #(por o bit 3-0 a 1)
    sw $t1, TRISB($t0)

    #CONFIGURAR RE3-0 COMO SAIDA
    lw $t1, TRISE($t0)
    andi $t1, $t1, 0xFFF0      #tudo a 1 menos o 1º bit
    sw $t1, TRISE($t0)


loop: 
    #LEITURA do portB e reseta todos os bits menos o que queremos
    lw $t1, PORTB($t0)
    andi $t1, $t1, 0x000F    #reset bits todos exceto bit 3-0, n altero
    xori $t1, $t1, 0x0009   # 0000000.... 01001

    #Ler LateE e resetar o bit que queremos
    lw $t2, LATE($t0)
    andi $t2, $t2, 0xFFF0   # reset bit 3-0, o resto nao altero
    or $t2, $t2, $t1         #merge
    sw $t2, LATE($t0)
    j loop