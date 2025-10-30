# Simple Makefile to build BL/ for an ARM cross-toolchain (adjust prefix if needed)
CROSS_COMPILE ?= arm-none-eabi-
CC      := $(CROSS_COMPILE)gcc
LD      := $(CROSS_COMPILE)ld
AS      := $(CROSS_COMPILE)as
OBJCOPY := $(CROSS_COMPILE)objcopy
CFLAGS  := -nostdlib -nostartfiles -ffreestanding -O2 -Wall -Wextra -mcpu=cortex-a8 -mthumb
LDFLAGS := -T linker.ld

SRCS := kernel_main.c
ASMS := bootloader.S
OBJS := $(SRCS:.c=.o) $(ASMS:.S=.o)
OUT  := bl.elf

all: $(OUT)

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

%.o: %.S
	$(CC) $(CFLAGS) -c $< -o $@

$(OUT): $(OBJS)
	$(LD) $(LDFLAGS) -o $@ $(OBJS)

clean:
	rm -f $(OBJS) $(OUT)
