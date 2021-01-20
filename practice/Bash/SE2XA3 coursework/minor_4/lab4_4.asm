%include "simple_io.inc"
   
global  asm_main

SECTION .data

arr1: dq 1,2,3,4,5,6,7,8,9,10
arr2: dq 11,12,13,14,15,16,17,18,19,20
err1: db "incorrect number of command line arguments",10,0
err2: db "incorrect command line argument",10,0

SECTION .bss

SECTION .text

; subroutine display_array
; expects one parameter on stack, either index 1 or 2
; it is assumed that the parameter is OK
; if the parameter is 1, the subroutine traverses the array
; arr1 and displays its entries separated by comma, e.g.
; 1,2,3,4,5,6,7,8,9,10
; if the parameter is 2, the subroutine traverses the array
; arr2 and displays its entries separated by comma, e.g.
; 11,12,13,14,15,16,17,18,19,20

display_array:
   enter	0,0               ; setup routine
   saveregs                ; save all registers

   mov	rax, [rbp+16]     ; 1st parameter
   cmp	rax, qword 1
   jne 	NOT1
   ; so it is 1
   mov 	rbx, arr1
   jmp 	display
   NOT1: 
   mov 	rbx, arr2

  display:
   ; rbx points to the beginning of the right array
   ; we will use rcx to control the counting loop of 10
   mov	rcx, qword 1
   LOOP:
   	mov	rax, qword [rbx]
   	call	print_int
   	mov 	al, ','
   	call 	print_char
   	inc 	rcx
   	add 	rbx, qword 8
   	cmp 	rcx, qword 9
      jbe	LOOP
   ; so the loop is over, print the last entry
   mov	rax, qword [rbx]
   call 	print_int
   call 	print_nl

display_array_end:   
   restoregs
   leave
   ret

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
asm_main:
   enter	0,0             ; setup routine
   saveregs              ; save all registers
	
	;; the first param is in rdi, so rdi=argc
	cmp	rdi, qword 2         ; argc should be 2
   jne ERR1

   ; so we have the right number of arguments
	;; the second param is in rsi, so rsi=argv
   ;; so [rsi+8]=argv[1]
   ;; check that argv[1] is either "1" or "2"
   ;; check the first byte of argv[1], should be '1' or '2'
   ;; check the second byte of argv[1], should be 0
	mov	rbx, qword [rsi+8]
	mov	r12, qword 0
   mov 	r12b, byte [rbx]       ; 1st byte of argv[1]
   try1: 
		cmp	r12b, '1'
      jne	try2
   	; so the first byte is '1'
   	jmp byte_1_ok
   try2:
		cmp	r12b, '2'
   	jne	ERR2
   byte_1_ok:
   ; check the second byte
   mov	al, byte  [rbx+1]    ; 2nd byte of argv[1]
   cmp 	al, byte 0
   jne 	ERR2
   ; hence the argument is correct , turn it into a number
	sub	r12b, '0'
   ;; now r12 holds the number, pass it to display_array

	push	r12
	call	display_array
	add	rsp, 8

   jmp asm_main_end

 ERR1:
   mov rax, err1
   call print_string
   jmp asm_main_end

 ERR2:
   mov rax, err2
   call print_string
   jmp asm_main_end

 asm_main_end:
   restoregs                  ; restore all registers
   leave                     
   ret
