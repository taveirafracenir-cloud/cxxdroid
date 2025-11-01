// hal/memory_RAM/RAM.ts
// CXXDROID HAL - Memory RAM Controller (TypeScript Layer)
// Provides read/write, allocation, and verification utilities.

export class RAM {
  private memory: Uint8Array;
  private size: number;
  private initialized: boolean = false;

  constructor(sizeInMB: number = 64) {
    this.size = sizeInMB * 1024 * 1024;
    this.memory = new Uint8Array(this.size);
  }

  init(): boolean {
    if (this.initialized) return true;
    this.memory.fill(0);
    this.initialized = true;
    console.log(`[RAM] Initialized ${this.size / (1024 * 1024)} MB`);
    return true;
  }

  write(addr: number, data: number): void {
    if (!this.initialized) throw new Error("RAM not initialized");
    if (addr < 0 || addr >= this.size) throw new RangeError("Invalid address");
    this.memory[addr] = data & 0xFF;
  }

  read(addr: number): number {
    if (!this.initialized) throw new Error("RAM not initialized");
    if (addr < 0 || addr >= this.size) throw new RangeError("Invalid address");
    return this.memory[addr];
  }

  testPattern(): boolean {
    if (!this.initialized) this.init();
    console.log("[RAM] Performing integrity test...");
    for (let i = 0; i < 1024; i++) {
      this.memory[i] = 0xAA;
    }
    for (let i = 0; i < 1024; i++) {
      if (this.memory[i] !== 0xAA) {
        console.error("[RAM] Integrity test failed at offset " + i);
        return false;
      }
    }
    console.log("[RAM] Integrity test passed.");
    return true;
  }

  clear(): void {
    if (!this.initialized) return;
    this.memory.fill(0);
    console.log("[RAM] Cleared all memory.");
  }

  getUsage(): string {
    const used = this.memory.reduce((acc, val) => acc + (val !== 0 ? 1 : 0), 0);
    const percent = ((used / this.size) * 100).toFixed(2);
    return `[RAM] Usage: ${used} / ${this.size} bytes (${percent}%)`;
  }

  shutdown(): void {
    this.memory.fill(0);
    this.initialized = false;
    console.log("[RAM] Controller shutdown.");
  }
}

/* Example Usage:
const ram = new RAM(128);
ram.init();
ram.write(0x100, 0x42);
console.log("Read:", ram.read(0x100));
ram.testPattern();
console.log(ram.getUsage());
ram.shutdown();
*/
