=====================================================
CXXDROID Vendor Interface
=====================================================

This directory contains vendor-specific extensions
for CXXDROID OS. These files allow hardware
manufacturers to extend and customize the system
without modifying the core framework.

Folders:
- drivers/   : Low-level vendor drivers
- configs/   : JSON or XML configuration files
- services/  : Java/Kotlin HAL extensions

All vendor contributions must follow CXXDROID HAL
and ABI compatibility guidelines.
