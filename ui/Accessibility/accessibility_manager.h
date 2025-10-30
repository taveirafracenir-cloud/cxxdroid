/*
 * CXXDROID UI Accessibility Module
 * --------------------------------
 * Header for managing accessibility features such as
 * screen readers, high-contrast mode, and sound feedback.
 *
 * Original code for CXXDROID.
 */

#ifndef CXXDROID_UI_ACCESSIBILITY_MANAGER_H
#define CXXDROID_UI_ACCESSIBILITY_MANAGER_H

#include <string>

namespace cxxdroid {
namespace ui {
namespace accessibility {

class AccessibilityManager {
public:
    AccessibilityManager();

    // Enable accessibility for users with visual or motor impairments
    void enable();

    // Disable all accessibility features
    void disable();

    // Check if accessibility is currently enabled
    bool isEnabled() const;

    // Display status information on screen
    void showStatus() const;

private:
    bool enabled;
};

} // namespace accessibility
} // namespace ui
} // namespace cxxdroid

#endif // CXXDROID_UI_ACCESSIBILITY_MANAGER_H
