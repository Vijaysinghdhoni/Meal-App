package com.vijaydhoni.meal.model.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealDBServiceTest {

    private lateinit var mealDBService: MealDBService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        mealDBService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("")).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealDBService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getMealByName_sentMealname_reciveMealDetail() {
        runBlocking {
            enqueMockResponse("mockResponse.json")
            val responseBody = mealDBService.getMealByName("Arrabiata").body()
            val request = mockWebServer.takeRequest()

            assertThat(responseBody).isNotNull()

            assertThat(request.path).isEqualTo("/search.php?s=Arrabiata")
        }
    }


    private fun enqueMockResponse(
        filename: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()

        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }
}