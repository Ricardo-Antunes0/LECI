.data
.text
.globl atoi
atoi:

li $v0, 0	# return res


while: lb $t0, 0($a0)
	blt $t0, '0' endw
	bgt $t0, '9', endw
	
	addi $t1, $t0, -0x30	#0x30 = 0
	mulu $v0, $v0, 10
	addu $v0, $v0, $t1	# res = 10*res + digit
	addi $a0, $a0, 1
	j while
endw:
jr $ra
