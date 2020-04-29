%include "simple_io.inc"

global asm_main
extern rperm

section .data

prompt1: db "if you want to swap, enter a,b",10,0
prompt2: db "if you want to end, enter 0:",10,0
err1: db "incorrect input, the first number must be in [1,8]",10,0
err2: db "incorrect input, the second input must be a comma",10,0
err3: db "incorrect input, the second number must be in [1,8]",10,0
done_message: db "program done",10,0
size: dq 8

section .bss
	array: resq 8

section .text

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
space:			;function to display n spaces (n is the parameter)
	enter	0,0
	saveregs
	
	mov	rax, [rbp+16]
	mov	rbx, rax
	mov	rcx, qword 0   ;;;counter rcx
	
while3:
	cmp	rbx, rcx
	je	space_end
	mov	al, ' '
	call	print_char
	add	rcx, qword 1
	jmp	while3

space_end:
	restoregs
        leave
        ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
dot:			;function to display n spaces (n is the parameter)
	enter   0,0
        saveregs

        mov     rax, [rbp+16]
        mov     rbx, rax
        mov     rcx, qword 0   ;;;counter rcx

while4:
        cmp     rbx, rcx
        je      dot_end
        mov     al, '.'
        call    print_char
        add     rcx, qword 1
        jmp     while4

dot_end:
        restoregs
        leave
        ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
top_bottom:		;function to display the top/bottom respect to the parameter "n"
	enter	0,0
	saveregs

	mov	rax, [rbp+16]
	mov	rbx, rax
	cmp	rbx, qword 1
	je	last	;;;;if the parameter is 1, just print one '+'.
	;;;;;for those parameters that are not 1
	mov	rcx, qword 2   ;rcx as counter
	mov	al, '+'
	call	print_char
while:	
	cmp	rcx, rbx
	je	last
	mov	al, '-'
	call	print_char
	add	rcx, qword 1
	jmp	while

last:
	mov	al, '+'
	call	print_char
	jmp	top_bottom_end
	
top_bottom_end:
	restoregs
        leave
        ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
body:			;function to draw the body of a box with parameter n
	enter	0,0
	saveregs

	mov     rax, [rbp+16]
	mov     rbx, rax
	mov     rcx, qword 2   ;rcx as counter
	mov     al, '+'
	call    print_char

while2:
        cmp     rcx, rbx
        je      last2
        mov     al, ' '
        call    print_char
        add     rcx, qword 1
        jmp     while2

last2:
        mov     al, '+'
        call    print_char
        jmp     body_end

body_end:
	restoregs
        leave             
        ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
line:			;function to display portion of each element
	enter	0,0
	saveregs

	;;;;;;;takes two parameters size and level
	mov	rcx, [rbp+16]   ;;;level
	mov	rbx, [rbp+24]   ;;;size
	mov	r12, rcx	;;;level
	mov	r13, rbx	;;;size
	;;;test
	;;;mov	rax, rcx
	;;;call	print_int
	;;;call	print_nl
	;;;mov	rax, rbx
	;;;call	print_int
	;;;call	print_nl
	
level_1:
	cmp	r12, qword 1
	jne	level_not1
	cmp	r13, qword 1
	je	level_1_size_1
	cmp	r13, qword 2
	je	level_1_size_2
	cmp	r13, qword 3
	je	level_1_size_3
	cmp     r13, qword 4
        je      level_1_size_4
	cmp     r13, qword 5
        je      level_1_size_5
	cmp     r13, qword 6
        je      level_1_size_6
	cmp     r13, qword 7
        je      level_1_size_7
	cmp     r13, qword 8
        je      level_1_size_8
	level_1_size_1:
		push	qword 5
		call	dot	;;;print 5 dots
		add	rsp, 8
		push	qword 1
		call	top_bottom ;;;print the '+'
		add	rsp, 8
		push	qword 4
		call	dot
		add	rsp, 8
		jmp	line_end
	level_1_size_2:
                push    qword 5
                call    dot     ;;;print 5 dots
                add     rsp, 8
                push    qword 2
                call    top_bottom ;;;print the bottom 
                add     rsp, 8
                push    qword 3
                call    dot  
                add     rsp, 8
		jmp	line_end
	level_1_size_3:
                push    qword 4
                call    dot     ;;;print dots
                add     rsp, 8
                push    qword 3
                call    top_bottom ;;;print the bottom
                add     rsp, 8
                push    qword 3
                call    dot  
                add     rsp, 8
                jmp     line_end
	level_1_size_4:
                push    qword 4
                call    dot     ;;;print dots  
                add     rsp, 8
                push    qword 4
                call    top_bottom ;;;print the bottom
                add     rsp, 8
                push    qword 2
                call    dot
                add     rsp, 8
                jmp     line_end
	level_1_size_5:
                push    qword 3
                call    dot     ;;;print dots  
                add     rsp, 8
                push    qword 5
                call    top_bottom ;;;print the bottom
                add     rsp, 8
                push    qword 2
                call    dot
                add     rsp, 8
                jmp     line_end
	level_1_size_6:
                push    qword 3
                call    dot     ;;;print dots  
                add     rsp, 8
                push    qword 6
                call    top_bottom ;;;print the bottom
                add     rsp, 8
                push    qword 1
                call    dot
                add     rsp, 8
                jmp     line_end
	level_1_size_7:
                push    qword 2
                call    dot     ;;;print dots  
                add     rsp, 8
                push    qword 7
                call    top_bottom ;;;print the bottom
                add     rsp, 8
                push    qword 1
                call    dot
                add     rsp, 8
                jmp     line_end
	level_1_size_8:
                push    qword 2
                call    dot     ;;;print dots
                add     rsp, 8
                push    qword 8
                call    top_bottom ;;;print the bottom
                add     rsp, 8
		jmp	line_end

level_not1:
	cmp	r12, qword 1
	ja	level_g1
	jmp	level_0

level_g1:
	cmp	r13, r12
	jb	size_less_than_level
	cmp	r13, r12
	je	size_equal_to_level
	cmp	r13, r12
	ja	size_greater_than_level

	size_less_than_level:
		push	qword 10
		call	space
		add	rsp, 8
		jmp	line_end

	size_equal_to_level:
		cmp     r13, qword 1
        	je      level_e_size_1
        	cmp     r13, qword 2    
        	je      level_e_size_2
        	cmp     r13, qword 3   
        	je      level_e_size_3               
        	cmp     r13, qword 4                  
        	je      level_e_size_4 
        	cmp     r13, qword 5
        	je      level_e_size_5
        	cmp     r13, qword 6   
        	je      level_e_size_6
        	cmp     r13, qword 7  
        	je      level_e_size_7  
        	cmp     r13, qword 8
        	je      level_e_size_8
		level_e_size_1:
			push	qword 5
			call	space
			add	rsp, 8
			push	qword 1
			call	top_bottom
			add	rsp, 8
			push	qword 4
			call	space
			add	rsp, 8
			jmp	line_end
		level_e_size_2:
                        push    qword 5
                        call    space
                        add     rsp, 8
                        push    qword 2
                        call    top_bottom
                        add     rsp, 8
                        push    qword 3
                        call    space
                        add     rsp, 8
                        jmp     line_end
		level_e_size_3:
                        push    qword 4
                        call    space
                        add     rsp, 8
                        push    qword 3
                        call    top_bottom
                        add     rsp, 8
                        push    qword 3
                        call    space
                        add     rsp, 8
                        jmp     line_end
		level_e_size_4:
                        push    qword 4
                        call    space
                        add     rsp, 8
                        push    qword 4
                        call    top_bottom
                        add     rsp, 8
                        push    qword 2
                        call    space
                        add     rsp, 8
                        jmp     line_end
		level_e_size_5:
                        push    qword 3
                        call    space
                        add     rsp, 8
                        push    qword 5
                        call    top_bottom
                        add     rsp, 8
                        push    qword 2
                        call    space
                        add     rsp, 8
                        jmp     line_end
		level_e_size_6:
                        push    qword 3
                        call    space
                        add     rsp, 8
                        push    qword 6
                        call    top_bottom
                        add     rsp, 8
                        push    qword 1
                        call    space
                        add     rsp, 8
                        jmp     line_end
		level_e_size_7:
                        push    qword 2
                        call    space
                        add     rsp, 8
                        push    qword 7
                        call    top_bottom
                        add     rsp, 8
                        push    qword 1
                        call    space
                        add     rsp, 8
                        jmp     line_end
		level_e_size_8:
                        push    qword 2
                        call    space
                        add     rsp, 8
                        push    qword 8
                        call    top_bottom
                        add     rsp, 8
                        jmp     line_end

	size_greater_than_level:
		;;;we don't need the case when size = 1, since the size 1 box only appear in level 1         
        	cmp     r13, qword 2                 
        	je      level_g_size_2        
        	cmp     r13, qword 3            
        	je      level_g_size_3                        
        	cmp     r13, qword 4           
        	je      level_g_size_4        
        	cmp     r13, qword 5          
        	je      level_g_size_5         
        	cmp     r13, qword 6              
        	je      level_g_size_6        
        	cmp     r13, qword 7           
        	je      level_g_size_7               
        	cmp     r13, qword 8          
        	je      level_g_size_8
		level_g_size_2:
			push	qword 5
			call	space
			add	rsp, 8
			push	qword 2
			call	body
			add	rsp, 8
			push	qword 3
			call	space
			add	rsp, 8
			jmp	line_end
		 level_g_size_3:
                        push    qword 4
                        call    space
                        add     rsp, 8
                        push    qword 3
                        call    body
                        add     rsp, 8
                        push    qword 3
                        call    space
                        add     rsp, 8
                        jmp     line_end
		 level_g_size_4:
                        push    qword 4
                        call    space
                        add     rsp, 8
                        push    qword 4
                        call    body
                        add     rsp, 8
                        push    qword 2
                        call    space
                        add     rsp, 8
                        jmp     line_end
		 level_g_size_5:
                        push    qword 3
                        call    space
                        add     rsp, 8
                        push    qword 5
                        call    body
                        add     rsp, 8
                        push    qword 2
                        call    space
                        add     rsp, 8
                        jmp     line_end
		 level_g_size_6:
                        push    qword 3
                        call    space
                        add     rsp, 8
                        push    qword 6
                        call    body
                        add     rsp, 8
                        push    qword 1
                        call    space
                        add     rsp, 8
                        jmp     line_end
		 level_g_size_7:
                        push    qword 2
                        call    space
                        add     rsp, 8
                        push    qword 7
                        call    body
                        add     rsp, 8
                        push    qword 1
                        call    space
                        add     rsp, 8
                        jmp     line_end
		 level_g_size_8:
                        push    qword 2
                        call    space
                        add     rsp, 8
                        push    qword 8
                        call    body
                        add     rsp, 8
                        jmp     line_end



level_0:
        cmp     r13, qword 1   
        je      level_0_size_1
        cmp     r13, qword 2  
        je      level_0_size_2 
        cmp     r13, qword 3
        je      level_0_size_3
        cmp     r13, qword 4    
        je      level_0_size_4 
        cmp     r13, qword 5   
        je      level_0_size_5               
        cmp     r13, qword 6  
        je      level_0_size_6              
        cmp     r13, qword 7
        je      level_0_size_7  
        cmp     r13, qword 8   
        je      level_0_size_8
	level_0_size_1:
		push	qword 5
		call	space
		add	rsp, 8
		mov	al, '1'
		call	print_char
		push	qword 4
		call	space
		add	rsp, 8
		jmp	line_end
	level_0_size_2:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '2'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end
	level_0_size_3:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '3'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end
	level_0_size_4:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '4'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end
	level_0_size_5:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '5'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end
	level_0_size_6:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '6'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end
	level_0_size_7:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '7'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end
	level_0_size_8:
                push    qword 5
                call    space 
                add     rsp, 8
                mov     al, '8'
                call    print_char
                push    qword 4
                call    space
                add     rsp, 8
                jmp     line_end

line_end:
	restoregs
	leave
	ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
create_line:		;function to create a whole line with parameters "array" and "level"
	enter	0,0
	saveregs
	
	mov	rax, [rbp+16] ;array
	mov	rbx, [rbp+24] ;level
	mov	r12, rax      ;array
	mov	r13, [rbx]      ;level
	mov	rcx, qword 0  ;counter
	mov	r15, rax      ;moving pointer of array

loop_create_line:
	cmp	rcx, qword 8
	je	create_line_end

	
	push	qword 0
	push	r15	;size
	push	r13	;level
	call	line
	add	rsp, 24
	add	r15, 8
	add	rcx, qword 1
	jmp	loop_create_line	
	

create_line_end:
	call	print_nl
	restoregs
	leave
	ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
display:		;function to display the graph of numbers in array
	enter	0,0
	saveregs

	mov	rax, [rbp+16]  ;;;the size
	mov	rbx, [rbp+24]  ;;;the array address
	mov	r12, [rax]     ;;;the size
	mov	r13, rbx     ;;;the array address

	;mov	rax, r13	;;;test to print out the parameter and check
	;call	print_int

	mov	r14, r12	;;;store the size in r14 used for counter
	mov	rcx, r12	;;;store the size in rcx, used for counter, also as levels
	mov	r15, r13	;;;store the array pointer in r15, use for moving pointer
	
	
	;;;;test;;;;;
        ;mov    rax, [r15]
        ;call   print_int              
        ;call   print_nl               
        ;add	r15, 8
	;mov    rax, [r15]              
        ;call   print_int               
        ;call   print_nl     
	;jmp	display_end

	;;;test
	;mov	rax, r14
	;call	print_int
	;call	print_nl
	;mov	rax, rcx
	;call	print_int
	;call	print_nl
	;mov	rax, [r15]
	;call	print_int
	;call	print_nl
	;jmp	display_end

loop_levels:			;;;loop through each levels
	cmp	rcx, qword 0
	je	display_level_0
	jmp	loop_lines
	;mov	r15, r13	;;;restore the pointer of array
	
	loop_lines:		;;;;loop through each elements
		cmp	r14, qword 0
		je	loop_levels_continue
		mov	rax, [r15]	;;;array
		mov	rbx, rcx	;;;level
		push	qword 0
		push	rax
		push	rbx
		call	line
		add	rsp,24
		add	r15, 8
		sub	r14, qword 1
		jmp	loop_lines

loop_levels_continue:
	mov	r14, r12
	sub	rcx, qword 1
	call	print_nl
	mov	r15, r13	;;;restore the pointer array
	jmp	loop_levels

display_level_0:	
	cmp	r14, qword 0
	je	display_end
	mov	rax, [r15]
	push	qword 0
	push	rax
	push	qword 0
	call	line
	add	rsp, 24
	add	r15, qword 8
	sub	r14, qword 1
	jmp	display_level_0

display_end:
	call	print_nl
	restoregs
        leave
        ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
display_array:           ;function to print out the content of array(use for checking)
	enter	0,0
	saveregs
	
	mov rax, array    
	mov rbx, rax       ;store the array in rbx
	mov r12, rbx	   ;store in r12
	mov rcx, qword 1     ;use rcx as counter

LOOP:
	mov	rax, qword [r12]
	call	print_int    ;print the number in the array
	mov	al, ','      
	call	print_char   ;print the comma
	inc	rcx	     ;increase the counter by one
	add	r12, qword 8 ;move the pointer to the next one
	cmp 	rcx, qword 7
	jbe	LOOP
	;the loop prints out 7 elements
out_of_loop:	;print out the last element
	mov	rax, qword [r12]
	call	print_int
	call	print_nl

display_array_end:
	restoregs
	leave
	ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
asm_main:	
	enter	0,0
	saveregs

	mov	rdi, array     ;1st param for rperm
	mov	rsi, qword 8   ;2nd param for rperm
	call rperm

	;; now the array 'array' is randomly initialzed  	

	push	qword 0
	push	array
	push	size
	call	display
	add	rsp, 24
	

prompt:
	mov	rax, prompt1
	call	print_string
	mov	rax, prompt2
	call	print_string

read:
	;;;;;;check the first argument
	call	read_char
	cmp	al, '0'
	je	asm_main_end
	cmp	al, '1'
	jb	error1
	cmp	al, '8'
	ja	error1
	mov	r12, 0
	mov	r12b, al
	sub	r12b, '0'   ;;;turn the first input in to number and store in r12b
	call	read_char   ;;;check if the second input is a comma
	cmp	al, ','
	jne	error2
	call	read_char   ;;;check the second number input is in [1,8] or not
	cmp	al, '1'
	jb	error3
	cmp	al, '8'
	ja	error3
	;;;store the number in r13b
	mov	r13, 0
	mov	r13b, al
	sub	r13b, '0'
	
	;;;;;now swap the two input numbers' position in the array
swap:
	mov	r14, array
	
	Loop1:
	  cmp	[r14], r12	;;;find the pointer address of the first input number
	  je	Loop2		;;;store it in r14
	  add	r14, 8
	  jmp	Loop1
	
	Loop2:
	  mov	r15, array
	
	Loop3:
	  cmp	[r15], r13	;;;find the pointer address of the first input number
	  je	Loop4		;;;store it in r14
	  add	r15, 8
	  jmp	Loop3
	
	Loop4:
	  mov	[r14], r13	;;;swap the two numbers in the array
	  mov	[r15], r12
	  jmp	continue
continue:
	call	print_nl
	push    qword 0
        push    array
        push    size
        call    display
        add     rsp, 24
	;call	display
	call	print_nl
	;;;clean the buffer
	jmp	L1  


error1:
	;;;;;print the error1 message
	call	print_nl
	mov	rax, err1
	call	print_string
	;;;empty the buffer
	L1:
	  cmp	al, 10
	  je	L2
	  call	read_char
	  jmp	L1
	L2:
	  jmp	prompt

error2:
	;;;print the error2 message
	call	print_nl
	mov	rax, err2
	call	print_string
	;;;empty the buffer
	jmp	L1

error3:
	;;;print the error3 message
	call	print_nl
	mov	rax, err3
	call	print_string
	;;;empty the buffer
	jmp	L1



asm_main_end:
	mov	rax, done_message
	call	print_string
	
	restoregs
	leave
	ret
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;