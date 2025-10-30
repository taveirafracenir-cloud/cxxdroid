/*
 * BL/bootloader.S
 * Minimal ARM-like bootloader (GNU as / GAS syntax)
 *
 * Responsibilities:
 *  - set stack pointer
 *  - initialize .data from .rodata if needed (simple copy stub omitted)
 *  - clear .bss
 *  - call kernel_main (C function)
 *
 * This file is intentionally simple and educational. It is original CXXDROID code.
 */

    .section .text
    .syntax unified
    .cpu cortex-a8
    .thumb
    .global _start
    .type _start, %function

/* External symbol provided by C code */
    .extern kernel_main

/* Symbols provided by linker script (expected) */
    .extern __bss_start
    .extern __bss_end
    .extern _stack_top

_start:
    /* Set up the stack pointer from linker-provided symbol */
    ldr sp, =_stack_top

    /* Optional: Disable interrupts (platform-specific) */
    cpsid i
    cpsid f

    /* Clear BSS: set memory [__bss_start .. __bss_end) = 0 */
    ldr r0, =__bss_start      /* r0 = dst */
    ldr r1, =__bss_end        /* r1 = end */
    mov r2, #0                /* value 0 */

1:  cmp r0, r1
    bge 2f
    str r2, [r0], #4          /* store 0, r0 += 4 */
    b 1b
2:

    /* Optionally re-enable caches, MMU, etc. (omitted) */

    /* Call into the kernel main implemented in C */
    bl kernel_main

hang:
    /* If kernel_main returns, spin here */
    wfi
    b hang

    .size _start, .-_start

/* Provide small .bss and .stack areas for linking convenience if linker script absent.
   In a real build you should use a linker script that defines these addresses. */
    .section .bss
    .align 4
__bss_start:
    .space 1024
__bss_end:

    .section .stack
    .align 8
    .space 4096
_stack_top:
