.data
.text
.globl main
main: 
	ori $t0, $0, 3
	ori $t1, $0, 2
	and $t2, $t0, $t1
	or  $t3, $t0, $t1	# $t3 = $t0 | $t1 (or bit a bit)
	nor $t4, $t0, $t1
	xor $t5, $t0, $t1
	#c)
	nor $t1, $t0, $0 #alinea c)
	#fim c)
	jr $ra         	#Fim do programa
	
