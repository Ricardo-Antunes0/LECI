.data
.text
.globl main
main:
li $t0, 0x1234
li $t1, 0x000F
and $t2, $t0, $t1
or $t3, $t0, $t1
nor $t5, $t0, $t1
xor $t4, $t0, $t1

jr $ra