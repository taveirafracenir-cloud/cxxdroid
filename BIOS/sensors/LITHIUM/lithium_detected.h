/*
 * CXXDROID BIOS - Lithium Sensor Header
 * -------------------------------------
 * Declares the LithiumSensor class responsible for detecting
 * lithium battery and displaying status message.
 *
 * Original code for CXXDROID.
 */

#ifndef CXXDROID_BIOS_SENSORS_LITHIUM_LITHIUM_DETECTED_H
#define CXXDROID_BIOS_SENSORS_LITHIUM_LITHIUM_DETECTED_H

#include <string>

namespace cxxdroid {
namespace bios {
namespace sensors {
namespace lithium {

class LithiumSensor {
public:
    LithiumSensor();

    // Perform lithium detection and display message
    void detect();

private:
    bool detected;

    // Display the "LITHIUM DETECTED" message
    void displayMessage() const;
};

} // namespace lithium
} // namespace sensors
} // namespace bios
} // namespace cxxdroid

#endif // CXXDROID_BIOS_SENSORS_LITHIUM_LITHIUM_DETECTED_H
