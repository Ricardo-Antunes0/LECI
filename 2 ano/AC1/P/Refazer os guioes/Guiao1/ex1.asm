.data

.text

.globl main
main:

ori $t0, $0, 4		# x = 4
ori $t2, $0, 8		# $t2 = 8
add $t1, $t0, $t0	# y = x + x
add $t1, $t1, $t2 	# y = (x+x) + 8
jr $ra
