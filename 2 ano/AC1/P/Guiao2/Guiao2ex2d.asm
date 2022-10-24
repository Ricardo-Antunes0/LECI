.data
.text
.globl main

main: 

li $t0, 7

move $t1, $t0 #num = gray

srl $t3, $t1, 4 #$t3 tem como objectivo guardar o shift right do num
xor $t1, $t1, $t3

srl $t3, $t1, 2
xor $t1, $t1, $t3

srl $t3, $t1, 1
xor $t1, $t1, $t3

move $t2, $t1 #bin = num

jr $ra