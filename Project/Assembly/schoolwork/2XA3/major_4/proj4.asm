%include "simple_io.inc"
   
global  asm_main

SECTION .data
err1: db "incorrect number of command line arguments",10,0
err2: db "incorrect length of the argument",10,0
err3: db "incorrect first letter of the argument (should be an upper case letter)",10,0 
err4: db "incorrect second letter of the argument (should be 3 or 5 or 7 or 9)",10,0


SECTION .bss

SECTION .text

display_line:
	enter	0,0
	saveregs
	
	mov rax, [rbp+16]
	mov rbx, [rbp+24]
	mov rcx, [rbp+32]
	call	print_int
	jmp	asm_main_end

	mov r12b, byte [rax]
	mov r13b, byte [rbx]
	mov r14b, byte [rcx]
	mov r15b, byte 0
	mov r11b, byte 0
	jmp loop1
   loop1:
	mov al, ' '
	call	print_char
	inc	r15b
	cmp	r15b, r12b
	jb	loop1
	jnb	loop2
   loop2:
	mov al, r14b
	call	print_char
	inc	r11b
	cmp	r11b, r13b
	jb	end_line
	jnb	loop2

   end_line:
	call	print_nl
	restoregs
	leave
	ret

display_shape:
	mov rax, [rbp+16]
	mov rbx, [rbp+24]
	
	jmp asm_main_end
   end_shape:
	restoregs
	leave 
	ret
asm_main:
   enter	0,0
   saveregs
	cmp rdi, qword 2
	jne	ERR1

	mov rbx, qword [rsi+8]
	mov r12b, byte [rbx+2]
	cmp r12b, byte 0
	jne	ERR2

	mov rbx, qword [rsi+8]
	mov r12b, byte [rbx]
	cmp r12b, 'A'
	jb ERR3
	cmp r12b, 'Z'
	ja ERR3
	
	;mov rbx, qword [rsi+8]
	;mov r12b, byte [rbx+1]
	;cmp r12b, byte '1'
	;;;;;;;;;
	;jna ERR4
	;mov rbx, qword [rsi+8]
        ;mov r12b, byte [rbx+1]
        ;cmp r12b, byte '10'
        ;jnb ERR4
	;;;;;;;;;;;;
	mov rbx, qword [rsi+8]
        mov r12b, byte [rbx+1]
        cmp r12b, byte '3'
	je display
        jne next1
	;;;;;;;;;;
   next1:
	mov rbx, qword [rsi+8]
        mov r12b, byte [rbx+1]
        cmp r12b, byte '5'
        je display
	jne next2
	;;;;;;;;;
   next2:
	mov rbx, qword [rsi+8]
        mov r12b, byte [rbx+1]
        cmp r12b, byte '7' 
        je display
	jne next3
	;;;;;;;;;
   next3:
	mov rbx, qword [rsi+8]
        mov r12b, byte [rbx+1]
        cmp r12b, byte '9' 
	je display
	jne ERR4
	jmp asm_main_end

   display:
	mov rbx, qword [rsi+8]
	mov r12b, byte [rbx]
	mov r13b, byte [rbx+1]
	push	r12b
	push	r13b
	call	display_shape
	add	rsp,8
	jmp	asm_main_end



   ERR1:
	mov rax, err1
	call print_string
	jmp asm_main_end

   ERR2:
	mov rax, err2
	call print_string
	jmp asm_main_end

   ERR3:
	mov rax, err3
	call print_string
	jmp asm_main_end

   ERR4:
	mov rax, err4
	call print_string
	jmp asm_main_end
  
   asm_main_end:
	restoregs
	leave
	ret
