.data
f1: .float 1.0
.text
.globl xtoy
xtoy:

addiu $sp, $sp, -8
sw $ra, 0($sp)
sw $s0, 4($sp)

move $s0, $a1	#s0 = y
move $t1, $a0	# $t2 = x

li $t0, 0	#i
l.s $f0, f1	# result

move $a0, $s0
jal abs

for: 	bge $t0, $v0, endfor
	if1:	ble $s0, $0, else
		l.s $f2, 0($t1)
		mul.s $f0, $f0, $f2
	else:
		div.s $f0, $f0, $f2
		
	addi $t0, $t0, 1
	j for
endfor:
lw $ra,0($sp)
lw $s0, 4($sp)
addiu $sp, $sp, 8
jr $ra





