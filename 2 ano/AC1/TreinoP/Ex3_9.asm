.data
d1: .double 0.0
.text
.globl average
average:

move $t1, $a1 	# i = n
move $a0, $t2	# $f2 = ponteiro 


l.d $f2, d1
for: 	ble $t1, $0, endfor
	addi $t1, $t1, -1
	sll $t0, $t1, 3	# i * 8
	addu $t2, $t2, $t0	#array[i-1]
	l.d $f4, 0($t2) # passo para o copressor 1, ou seja, para os fs porque ele ja tinha valor double
	add.d $f2, $f2, $f4	# suma = sumar + array[i-1]
	j for
endfor:
mtc1 $t1, $f6
 cvt.d.w $f6, $f6	# double(n)
 div.d $f2, $f2, $f6

jr $ra

	
