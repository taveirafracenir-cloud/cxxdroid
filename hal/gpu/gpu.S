; hal/gpu/gpu.S
; Minimal GPU HAL - writes a startup message to VGA text buffer
; NASM/GAS-friendly (BITS 32 / Linux protected-mode simple demo)
;
; Notes:
; - Intended as a low-level HAL stub to show GPU init.
; - Writes "CXXDROID GPU INITIALIZED!" to VGA text mode memory (0xB8000).
; - For real hardware/platform adapt the framebuffer address and mode setup.

BITS 32

section .data
msg db "CXXDROID GPU INITIALIZED!", 0x0
msg_len equ $ - msg

section .text
global _start

_start:
    ; Set stack
    mov esp, 0x90000
    mov ebp, esp

    ; VGA text buffer base (color text mode)
    mov edi, 0x000B8000   ; destination pointer (linear) - may require identity mapping in some environments

    ; Attribute byte: light gray on black
    mov al, 0x07
    mov ah, al            ; store attribute in AH for word writes (char+attr)
    mov ebx, msg          ; source pointer to message

    ; loop through message and write to VGA buffer as pairs (char, attr)
.write_loop:
    mov al, [ebx]
    cmp al, 0
    je .done_write
    ; write character and attribute (2 bytes per cell)
    mov [edi], al         ; character byte
    mov byte [edi+1], 0x07 ; attribute byte
    add ebx, 1
    add edi, 2
    jmp .write_loop

.done_write:
    ; Optionally, provide a simple frame buffer clear or other init steps here.

    ; Exit (if running under Linux userspace)
    mov eax, 1            ; sys_exit
    xor ebx, ebx
    int 0x80
