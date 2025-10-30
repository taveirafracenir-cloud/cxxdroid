/*
 * CXXDROID BIOS - Lithium Sensor Implementation
 * ---------------------------------------------
 * Implements the LithiumSensor class.
 *
 * When detect() is called, it sets the detected state to true
 * and displays "LITHIUM DETECTED".
 */

#include "lithium_detected.h"
#include <iostream>

namespace cxxdroid {
namespace bios {
namespace sensors {
namespace lithium {

LithiumSensor::LithiumSensor() : detected(false) {}

void LithiumSensor::detect() {
    // In a real BIOS, hardware registers would be checked here.
    // For now, we assume lithium is present.
    detected = true;
    displayMessage();
}

void LithiumSensor::displayMessage() const {
    if (detected) {
        std::cout << "============================\n";
        std::cout << "       LITHIUM DETECTED     \n";
        std::cout << "============================\n";
    }
}

} // namespace lithium
} // namespace sensors
} // namespace bios
} // namespace cxxdroid
