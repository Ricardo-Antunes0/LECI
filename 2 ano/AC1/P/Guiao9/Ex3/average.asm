.data
k1: .double 0.0
.text
.globl average
average:

move $t0, $a1	# i = n
move $t1, $a0	# $t1 = &array

l.d $f2, k1	# sum = 0.0


for: blez $t0, endfor
sub $t2, $t0, 1
sll $t2, $t2, 3		# $t1 = (i-1)*8
addu $t4, $t1, $t2	# array[i-1] = &array[0] + (i-1)*8

l.d $f2, 0($t4)		# sum = *array[i-1]
add.d $f0, $f0, $f2	# $f0 += sum
addi $t0, $t0, -1
j for

endfor:
mtc1.d $a1, $f4
cvt.d.w $f4, $f4
div.d $f0, $f0, $f4
jr $ra

