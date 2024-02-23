package com.jocmp.feedbinclient

import com.jocmp.feedbinclient.api.Subscription
import com.jocmp.feedbinclient.api.Tagging
import com.squareup.moshi.Moshi
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.io.File

interface Feedbin {
    @GET("v2/entries.json")
    suspend fun entries(@Query("page") page: String? = null): Response<List<Entry>>

    @GET("v2/authentication.json")
    suspend fun authentication(@Header("Authorization") authentication: String): Response<Void>

    @GET("v2/subscriptions.json")
    suspend fun subscriptions(): Response<List<Subscription>>

    @GET("v2/taggings.json")
    suspend fun taggings(): Response<List<Tagging>>

    companion object {
        private const val DEFAULT_URL = "https://api.feedbin.com/"

        fun create(
            client: OkHttpClient,
            baseURL: String = DEFAULT_URL
        ): Feedbin {
            val moshi = Moshi.Builder().build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
        }

        suspend fun verifyCredentials(
            username: String,
            password: String,
            client: OkHttpClient = OkHttpClient(),
            baseURL: String = DEFAULT_URL
        ): Boolean {
            val feedbin = create(client = client, baseURL = baseURL)

            val authentication = Credentials.basic(username, password)

            return feedbin
                .authentication(authentication = authentication)
                .isSuccessful
        }
    }
}
