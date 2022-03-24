package com.example.coffeeshop

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
class ApiClass {
    private val client = HttpClient()

    fun getData(success: (List<CoffeeShop>) -> Unit) {
        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val token = postToken()
                val requestResult = client.get<String>(apiUrl + getPath) {
                    headers {
                        append(HttpHeaders.Accept, "application/json")
                        append(HttpHeaders.Authorization, "token")
                    }
                    parameter("token", token)
                }
                val list = Json.decodeFromString<List<CoffeeShop>>(requestResult)

                success(list)
            } catch (e: Throwable) {
                // handle network error
            }
        }
    }

    private suspend fun postToken(): String {
        val requestResult = client.post<String>(apiUrl + postPath) {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
        }
        return Json.decodeFromString<TokenHolder>(requestResult).token
    }

    companion object {
        private const val apiUrl = "https://blue-bottle-api-test.herokuapp.com/"
        private const val getPath = "v1/coffee_shops"
        private const val postPath = "v1/tokens"
    }
}