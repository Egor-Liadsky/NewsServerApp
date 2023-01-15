package com.lyadskiy

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

data class User(val username: String, val password: String)

class ApplicationTestJUnit {

    @Test
    fun `first test`() = testApplication {
        val response = client.get("/v1/news/categories")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}