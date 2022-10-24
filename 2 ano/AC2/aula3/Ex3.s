.equ SFR_BASE_HI, 0XBF88
.equ PORTB, 0X6050
.equ TRISB, 0X6040
.equ TRISE, 0X6100
.equ LATE, 0X6120
.equ TRISD, 0x60C0
.equ PORTD, 0X60D0

.data
.text
.globl main
main: 

#Definir porto RD8 como entrada (TRISB)

lui $t0, SFR_BASE_HI
lw $t1, TRISD($t0)
ori $t1, $t1, 0x0100    #por o bit 8 a 1
sw $t1, TRISD($t0)

#CONFIGURAR RE0 COMO SAIDA

lw $t1, TRISE($t0)  #carregar o conteudo do TRISE para o $t1
andi $t1, $t1, 0xFFFE   #alterei o conteudo, bit 0 passou a 0 e o resto ta igual
sw $t1, TRISE($t0) #guardar essas alteracoes no registo TRIS

loop: 

#leitura do porto de entrada

lw $t1, PORTD($t0)
#andi $t1, $t1, 0x0100
xori $t1, $t1, 0x0100

#leitura do porto de SAIDA
lw $t2, LATE($t0)       
andi $t2, $t2, 0xFFFE   #reset bit 0

srl $t1, $t1, 8

or $t2, $t2, $t1    #merge
sw $t2, LATE($t0)   #guardar o que alterei
j loop

jr $ra