package crypto;

import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * CXXDROID Crypto Manager
 * Implements ICryptoService using Java cryptography.
 */
public class CryptoManager extends ICryptoService.Stub {
    private static final String TAG = "CXXDROID.CryptoManager";
    private SecretKey secretKey;

    public CryptoManager() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            secretKey = keyGen.generateKey();
            Log.i(TAG, "CryptoManager initialized (AES-256)");
        } catch (Exception e) {
            Log.e(TAG, "CryptoManager init failed", e);
        }
    }

    @Override
    public String hash(String text) throws RemoteException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            Log.e(TAG, "hash() failed", e);
            return "";
        }
    }

    @Override
    public String encrypt(String text) throws RemoteException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(text.getBytes());
            return Base64.encodeToString(encrypted, Base64.NO_WRAP);
        } catch (Exception e) {
            Log.e(TAG, "encrypt() failed", e);
            return "";
        }
    }

    @Override
    public String decrypt(String text) throws RemoteException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decoded = Base64.decode(text, Base64.NO_WRAP);
            return new String(cipher.doFinal(decoded));
        } catch (Exception e) {
            Log.e(TAG, "decrypt() failed", e);
            return "";
        }
    }
}
