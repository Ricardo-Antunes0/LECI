.equ SFR_BASE_HI, 0XBF88
.equ PORTB, 0X6050
.equ TRISB, 0X6040
.equ TRISE, 0X6100
.equ LATE, 0X6120

.data
.text
.globl main
main: 
    add $sp, $sp, -12
    sw $ra, 0($sp)
    sw $s0, 4($sp)
    sw $s1, 8($sp)

    lui $s0, SFR_BASE_HI

    #COnfigurar RB3 - 0 como entrada 
    lw $t1, TRISB($t0)          #BF886040 (BF88 FICA BF880000 e o 0X6040 fica 00006040)
    ori $t1, $t1, 0x000F        #(por o bit 3-0 a 1)
    sw $t1, TRISB($t0)

    #CONFIGURAR RE3-0 COMO SAIDA
    lw $t1, TRISE($t0)
    andi $t1, $t1, 0xFFF0      #tudo a 1 menos os bit 3-0
    sw $t1, TRISE($t0)


    li $s1, 0 #counter = 0
loop: 
    #Ler LateE e resetar o bit que queremos
    lw $t2, LATE($t0)
    andi $t2, $t2, 0xFFF0   # reset bit 3-0, o resto nao altero
    
    or $t2, $t2, $s1        #merge
    sw $t2, LATE($t0)

    li $a0, 500
    jal delay

    addi $s1, $s1, 1    #counter++
    andi $s1, $s1, 0x000F   #para o counter ter so 4 bits ( 0000... 1111) nao passa desses 4 1s
    j loop

jr $ra