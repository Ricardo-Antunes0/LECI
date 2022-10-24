.data

.text
.globl max
max:

move $t0, $a0		# $t0 = &p
move $t1, $a1		# $t1 = n

addi $t1, $t1, -1 	# n - 1

sll $t1, $t1,3		# (n-1)*8

addu $t2, $t0, $t1	# &u = &p+(n-1)*8

l.d $f0, 0($t0)		# max = *p
addiu	$t0, $t0, 8	# p = p + 8

for: 	bgt $t0, $t2, endfor
	l.d $f2, 0($t0)	#*p
	
	if:	c.le.d $f2, $f0	
		bc1t endif
		mov.d $f0, $f2	#max = *p
	endif: 
	addiu $t0, $t0, 8	#p++
	j for
	
endfor:
	jr $ra
