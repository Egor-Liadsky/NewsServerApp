package com.lyadskiy

import com.lyadskiy.dto.CategoryDTOReceive
import com.lyadskiy.utils.Constants
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class CategoriesTest {

    @Test
    fun `Get all categories`() = testApplication {
        val response = client.get("/v1/news/categories")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Create category`() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.post("/v1/news/categories") {
            header(HttpHeaders.Authorization, Constants.USER_TOKEN)
            header(HttpHeaders.ContentType, "application/json")
            setBody(CategoryDTOReceive(name = "Тестовая категория"))
        }
        assertEquals(HttpStatusCode.Created, response.status)
    }

    @Test
    fun `Update category`() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        val response = client.put("/v1/news/categories/12") {
            header(HttpHeaders.Authorization, Constants.USER_TOKEN)
            header(HttpHeaders.ContentType, "application/json")
            setBody(CategoryDTOReceive(name = "Тестовая категория"))
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `Delete category`() = testApplication {
        val response = client.delete("/v1/news/categories/12"){
            header(HttpHeaders.Authorization, Constants.USER_TOKEN)
            header(HttpHeaders.ContentType, "application/json")
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }
}

