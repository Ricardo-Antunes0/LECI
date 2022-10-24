.data
f1: .float 0.0
.text
.globl var
var:

addiu $sp, $sp, -12
sw $ra, 0($sp)
sw $s0, 4($sp)
sw $s1, 8($sp)

move $s0, $a0	#$s0 = *array
move $s1, $a1	#$s1 = nval

move $a0, $s0
move $a1, $s1
jal average

mtc1 $v0, $f2
cvt.s.w $f2, $f2	# media 

li $t0, 0	# i
l.s $f6, f1
	for: bge $t0, $s1, endfor

		sll $t1, $t0, 3	# i = i* 8
		addu  $s0, $s0, $t1	#array[i]
		l.d $f4,0($s0)
		cvt.s.d $f4, $f4
		sub.s $f12, $f4, $f2
		li $a0, 2
		jal xtoy
		
		add.s $f6, $f6, $f0
	
		addi $t0, $t0, 1
		j for		
	endfor:
	mtc1 $s1, $f8
	cvt.d.w $f8, $f8
	cvt.d.s $f6, $f6
	div.d $f0, $f6, $f8
	
	lw $ra, 0($sp)
	lw $s0, 4($sp)
	lw $s1, 8($sp)
	addiu $sp, $sp, 12
	jr $ra


