package com.lyadskiy.security.hasing

interface HashingService {

    fun generatedSaltedHash(value: String, saltedLength: Int = 32): SaltedHash

    fun verify(value: String, saltedHash: SaltedHash): Boolean
}