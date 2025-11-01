// crypto/crypto_service.aidl
package crypto;

/**
 * Interface for system-level cryptographic operations.
 */
interface ICryptoService {
    /**
     * Generates SHA-256 hash of input text.
     */
    String hash(String text);

    /**
     * Encrypt text using internal key (AES-256 or equivalent).
     */
    String encrypt(String text);

    /**
     * Decrypt previously encrypted text.
     */
    String decrypt(String text);
}
