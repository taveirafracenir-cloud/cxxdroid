; TestSys Assembly - CXXDROID low-level test
; Simple assembly program to validate CPU and system startup

section .data
msg db "TestSys Assembly Boot Successful!", 0xA
len equ $-msg

section .text
global _start

_start:
    ; write message to stdout
    mov eax, 4          ; sys_write
    mov ebx, 1          ; stdout
    mov ecx, msg        ; message
    mov edx, len        ; message length
    int 0x80

    ; exit
    mov eax, 1          ; sys_exit
    xor ebx, ebx
    int 0x80
