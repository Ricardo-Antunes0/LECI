.data
.equ READ_CORE_TIMER,11
.equ RESET_CORE_TIMER,12
.equ PUT_CHAR, 3
.equ PRINT_INT,6
.text
.globl main
main:

li $t0, 0   #counter = 0
    
while:    
    li $t1, 200000
    li $v0, READ_CORE_TIMER
    syscall
    blt $v0, $t1, while

    li $v0, RESET_CORE_TIMER
    syscall
    
    #PRINT_INT
    li $t2, 4
    li $t3, 10
    sll $t2, $t2, 16 # 4 << 16
    or $t3, $t3, $t2
    move $a0, $t0
    move $a1, $t3
    li $v0, 6
    syscall
    addi $t0, $t0, 5
    
    #PUT_CHAR
    la $a0, '\r'
    li $v0, 3
    syscall

    j while
    li $v0, 0
    jr $ra
