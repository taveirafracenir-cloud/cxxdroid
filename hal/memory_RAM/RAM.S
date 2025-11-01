; ===========================================================
; CXXDROID HAL - RAM Initialization
; hal/memory_RAM/RAM.S
; -----------------------------------------------------------
; Low-level RAM initialization and test pattern verification.
; ===========================================================

BITS 32

section .data
msg_init db "CXXDROID RAM Controller Active", 0xA
msg_len  equ $ - msg_init

section .bss
align 4
test_area resb 1024       ; small test memory area (1 KB)

section .text
global _start

_start:
    ; Setup stack
    mov esp, 0x8FC00
    mov ebp, esp

    ; Clear test area
    mov edi, test_area
    mov ecx, 256           ; 256 dwords (1024 bytes)
    xor eax, eax
    rep stosd

    ; Write test pattern
    mov edi, test_area
    mov ecx, 256
    mov eax, 0xAA55AA55
    rep stosd

    ; Verify pattern
    mov esi, test_area
    mov ecx, 256
verify_loop:
    mov eax, [esi]
    cmp eax, 0xAA55AA55
    jne memory_error
    add esi, 4
    loop verify_loop

    ; Print message if OK
    mov eax, 4          ; sys_write
    mov ebx, 1          ; stdout
    mov ecx, msg_init
    mov edx, msg_len
    int 0x80
    jmp halt_system

memory_error:
    mov eax, 4
    mov ebx, 1
    mov ecx, err_msg
    mov edx, err_len
    int 0x80

halt_system:
    hlt
    jmp halt_system

section .data
err_msg db "RAM TEST FAILED", 0xA
err_len equ $ - err_msg
