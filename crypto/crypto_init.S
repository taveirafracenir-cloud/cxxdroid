; ==============================================
; CXXDROID CRYPTO ENGINE BOOTSTRAP
; crypto/crypto_init.S
; ==============================================
; Low-level CPU instructions for entropy seed and AES setup.
; ==============================================

BITS 32

section .data
crypto_msg db "CXXDROID CRYPTO ENGINE ACTIVE", 0xA
crypto_len equ $ - crypto_msg

section .text
global _start_crypto

_start_crypto:
    mov esp, 0x8EF00
    mov ebp, esp

    ; clear registers
    xor eax, eax
    xor ebx, ebx
    xor ecx, ecx
    xor edx, edx

    ; gather entropy using RDTSC
    rdtsc
    xor eax, edx
    mov [entropy_seed], eax

    ; print activation message
    mov eax, 4
    mov ebx, 1
    mov ecx, crypto_msg
    mov edx, crypto_len
    int 0x80

    hlt
    jmp $

section .bss
entropy_seed resd 1
