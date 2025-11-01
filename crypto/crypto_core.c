// ==============================================
// CXXDROID Crypto Core
// crypto/crypto_core.c
// ==============================================

#include <stdio.h>
#include <stdint.h>
#include <string.h>

// Simple SHA-256 (versão reduzida para HAL)
#include "sha256.h" // (você pode usar uma lib existente aqui)

void crypto_init() {
    printf("[CRYPTO] Core initialized.\n");
}

void crypto_hash(const char *input, char *output) {
    unsigned char hash[32];
    sha256(input, strlen(input), hash);

    for (int i = 0; i < 32; i++)
        sprintf(output + (i * 2), "%02x", hash[i]);

    output[64] = 0;
}

void crypto_test() {
    char result[65];
    crypto_hash("CXXDROID", result);
    printf("[CRYPTO] Hash of 'CXXDROID': %s\n", result);
}
