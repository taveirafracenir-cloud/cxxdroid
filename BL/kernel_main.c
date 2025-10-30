/*
 * BL/kernel_main.c
 * Minimal kernel entry point that is called by bootloader.S
 *
 * Keep this file small and self-contained. It prints a simple message using
 * a very small console helper (if running in a simulator/host that provides
 * a serial output routine). Otherwise, it toggles a heartbeat in memory.
 *
 * This is original CXXDROID code (no Android content).
 */

#include <stdint.h>

/* Platform-provided putchar-like function could be declared here.
   If not available, this file will fall back to looping. */
/* extern void putc_serial(char c); */

/* Weak implementation of a very small serial writer.
   If platform provides putc_serial, link that instead. */
__attribute__((weak))
void putc_serial(char c) {
    /* No-op default: silence if platform doesn't implement console. */
    (void)c;
}

/* Simple string output using putc_serial */
static void puts_simple(const char *s) {
    while (*s) {
        putc_serial(*s++);
    }
}

/* Kernel main called from assembly */
void kernel_main(void) {
    /* Very small early init */
    puts_simple("CXXDROID Kernel: early init...\n");

    /* Initialize minimal subsystems (placeholders) */
    puts_simple("CXXDROID Kernel: initializing drivers...\n");

    /* In a real kernel we'd initialize memory manager, scheduler, etc. */
    puts_simple("CXXDROID Kernel: ready.\n");

    /* Example: simple heartbeat loop to indicate alive state */
    volatile uint32_t *heartbeat = (uint32_t*)0x40000000; /* example MMIO / RAM addr */
    for (;;) {
        /* Toggle heartbeat value to show activity on platforms that map heartbeat */
        if (heartbeat) {
            *heartbeat ^= 0x1;
        }
        /* Busy-wait delay (very small) */
        for (volatile int i = 0; i < 100000; ++i) { asm volatile("nop"); }
    }

    /* Should never return */
}
