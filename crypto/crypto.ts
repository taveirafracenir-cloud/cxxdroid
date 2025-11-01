// crypto/crypto.ts
// TypeScript version of the crypto manager for CXXDROID web/sim layer

import crypto from "crypto";

export class CryptoManager {
  static hash(text: string): string {
    return crypto.createHash("sha256").update(text).digest("hex");
  }

  static encrypt(text: string, key: string): string {
    const cipher = crypto.createCipher("aes-256-ctr", key);
    let encrypted = cipher.update(text, "utf8", "hex");
    encrypted += cipher.final("hex");
    return encrypted;
  }

  static decrypt(text: string, key: string): string {
    const decipher = crypto.createDecipher("aes-256-ctr", key);
    let decrypted = decipher.update(text, "hex", "utf8");
    decrypted += decipher.final("utf8");
    return decrypted;
  }
}
