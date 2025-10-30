// hal/gpu/gpu.ts
// TypeScript GPU HAL (userland/test harness)
// Provides a simple framebuffer-like API usable in Node or browser (with small adaptation).
// Original CXXDROID HAL helper for testing and bindings.

export type Color = number; // 0xAARRGGBB

export interface GpuConfig {
  width: number;
  height: number;
}

export class GPU {
  private width: number = 0;
  private height: number = 0;
  private framebuffer: Uint32Array | null = null;
  private initialized: boolean = false;

  constructor() {}

  init(cfg: GpuConfig): boolean {
    if (cfg.width <= 0 || cfg.height <= 0) return false;
    this.width = cfg.width;
    this.height = cfg.height;
    this.framebuffer = new Uint32Array(this.width * this.height);
    this.initialized = true;
    console.log(`[GPU] Initialized ${this.width}x${this.height}`);
    return true;
  }

  getResolution(): { width: number; height: number } {
    return { width: this.width, height: this.height };
  }

  clear(color: Color = 0xff000000) {
    if (!this.framebuffer) return;
    this.framebuffer.fill(color);
  }

  setPixel(x: number, y: number, color: Color) {
    if (!this.framebuffer) return;
    if (x < 0 || y < 0 || x >= this.width || y >= this.height) return;
    this.framebuffer[y * this.width + x] = color;
  }

  drawRect(x: number, y: number, w: number, h: number, color: Color) {
    if (!this.framebuffer) return;
    const maxX = Math.min(this.width, x + w);
    const maxY = Math.min(this.height, y + h);
    for (let yy = Math.max(0, y); yy < maxY; yy++) {
      for (let xx = Math.max(0, x); xx < maxX; xx++) {
        this.framebuffer[yy * this.width + xx] = color;
      }
    }
  }

  // Very small text drawing (monospace 6x8) using a minimal built-in bitmap font
  // For brevity the font is extremely small and only implements ASCII 32-127 partially.
  drawText(x: number, y: number, text: string, color: Color = 0xffffffff) {
    if (!this.framebuffer) return;
    // For a real implementation, use a proper font rasterizer.
    // Here we paint one pixel per character as a simple placeholder to show positions.
    for (let i = 0; i < text.length; i++) {
      const px = x + i * 6; // character spacing
      const py = y;
      // draw a simple 4x6 filled block representing the glyph
      this.drawRect(px, py, 4, 6, color);
    }
  }

  // Present the framebuffer: in Node.js we can optionally dump to PNG (not implemented here)
  // or return the raw buffer for higher-level code to blit to a Canvas in the browser.
  getFrameBuffer(): Uint32Array | null {
    return this.framebuffer;
  }

  shutdown() {
    this.framebuffer = null;
    this.initialized = false;
    console.log("[GPU] Shutdown complete.");
  }
}

/* Example usage (Node):
import { GPU } from './gpu';
const gpu = new GPU();
gpu.init({width: 320, height: 240});
gpu.clear(0xff101010);
gpu.drawRect(10,10,100,50,0xffff0000);
gpu.drawText(12,12,"Hello CXXDROID",0xffffffff);
const fb = gpu.getFrameBuffer();
// Save or present framebuffer with a separate utility.
*/
