package ui.view.webkit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * CXXDROID WebView
 * ----------------
 * Minimal HTML viewer for CXXDROID OS.
 * This class reads a local HTML file and prints its content to console.
 *
 * Original code for CXXDROID.
 */
public class WebView {

    private String loadedContent = "";

    public WebView() {
        loadedContent = "";
    }

    /**
     * Load a local HTML file
     * @param filePath Path to HTML file
     * @return true if successful, false otherwise
     */
    public boolean loadUrl(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            loadedContent = sb.toString();
            System.out.println("[WebView] Loaded: " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("[WebView] Failed to load: " + filePath);
            return false;
        }
    }

    /**
     * Display the loaded HTML content
     */
    public void display() {
        if (loadedContent.isEmpty()) {
            System.out.println("[WebView] No content loaded.");
        } else {
            System.out.println("===== WebView Content Start =====");
            System.out.println(loadedContent);
            System.out.println("===== WebView Content End =====");
        }
    }

    /**
     * Clear the current content
     */
    public void clear() {
        loadedContent = "";
        System.out.println("[WebView] Content cleared.");
    }

    // Example test method (can be removed in integration)
    public static void main(String[] args) {
        WebView webView = new WebView();
        if (webView.loadUrl("example.html")) {
            webView.display();
        }
    }
}
