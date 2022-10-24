.data
.text
.globl max
max: 

move $t0, $a0
move $t1, $a1	#n

addi $t1, $t1, -1
sll $t1, $t1, 3	#(n-1)*8

addu $t2, $t0, $t1 	# ponteiro u

l.d $f0, 0($t0)		# $f0 = max = *p
sll $t0, $t0, 3		#enderecos + 8

for: bgt $t0, $t2, endfor
	l.d $f2,0($t0)
	if: c.le.d $f2, $f0
	bc1t endif
	mov.d $f0, $f2
	endif:
	sll $t0, $t0, 3	# p++
	j for
endfor:
	jr $ra