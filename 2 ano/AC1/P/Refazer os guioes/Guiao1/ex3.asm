.data

.text

.globl main
main:


ori $v0, $0, 5		# x = read_int()
syscall

or $t0, $0, $v0
ori $t2, $0, 8		# $t2 = 8
add $t1, $t0, $t0	# y = x + x
sub $t1, $t1, $t2 	# y = (x+x) - 8

#or $a0, $0, $t1
#ori $v0, $0, 1		#print_int10(y)
#syscall 

#or $a0, $0, $t1
#ori $v0, $0, 34	#print_int16(y)
#syscall 

or $a0, $0, $t1
ori $v0, $0, 36		#print_intu10(y)
syscall 

jr $ra
