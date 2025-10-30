; ==========================================
; CXXDROID HAL - CPU Initialization
; hal/cpu/cpu.S
; ==========================================

BITS 32                ; 32-bit mode

section .data
init_msg db "CXXDROID CPU Initialized!", 0xA
msg_len equ $ - init_msg

section .text
global _start

_start:
    ; -------------------------
    ; Set up stack
    ; -------------------------
    mov esp, 0x9FC00        ; stack pointer at 640 KB
    mov ebp, esp

    ; -------------------------
    ; Clear general purpose registers
    ; -------------------------
    xor eax, eax
    xor ebx, ebx
    xor ecx, ecx
    xor edx, edx
    xor esi, esi
    xor edi, edi

    ; -------------------------
    ; Write initialization message to screen (stdout, Linux style)
    ; -------------------------
    mov eax, 4              ; sys_write
    mov ebx, 1              ; stdout
    mov ecx, init_msg       ; message address
    mov edx, msg_len        ; message length
    int 0x80

    ; -------------------------
    ; Enter infinite loop (CPU idle)
    ; -------------------------
cpu_idle:
    hlt                     ; halt CPU until next interrupt
    jmp cpu_idle
