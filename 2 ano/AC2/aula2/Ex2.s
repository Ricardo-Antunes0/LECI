.data
.equ READ_CORE_TIMER,11
.equ RESET_CORE_TIMER,12
.text
.globl main
main: 

addiu $sp, $sp, -8
sw $ra, 0($sp)
sw $s0, 4($sp)

li $s0, 0

while:                                  #       while(1) {
        li      $a0, 1000               #               $a0 = 1000;
        jal     delay                   #               delay(1000);

        addi    $s0, $s0, 1             #
        move    $a0, $s0                #               $a0 = ++counter;
        li      $a1, 0x0004000A         #               $a1 = 10 | 4 << 16
        li      $v0, 6                  #                $v0 = 6 (printInt());
        syscall                         #               printInt(++counter, 10 | 4 << 16);
        li      $a0, '\r'               #               $a0 = '\r'
        li      $v0, 3                  #               $v0 = 3 (putchar());
        syscall                         #               putChar('\r');
        j       while                   #       }
        li      $v0, 0                  #       return 0;
        lw      $ra, 0($sp)             #       report o $ra
        lw      $s0, 4($sp)             #       repor o $s0
        addiu   $sp, $sp, 8             #       repor espaco da pilha
        jr      $ra                     # }

delay:
move $t0, $a0

for: ble $t0, 0, endfor
    li $v0, 12
    syscall

read:
    li $v0, 11
    syscall
    blt $v0, 20000, read
    addi $t0, $t0, -1
    j for

endfor: jr $ra