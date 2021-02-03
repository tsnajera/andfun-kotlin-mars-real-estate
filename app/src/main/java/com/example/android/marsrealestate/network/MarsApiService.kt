/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"

// Create Moshi builder so Moshi annotations can work properly with Kotlin
private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

// Use Retrofit Builder with ScalarConverterFactory and BASE_URL
private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi)) // Tells retrofit that it can use moshi to turn response into json object
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) // Tell RetroFit to produce Coroutine-based api. Tells to return something other than default Call class
        .baseUrl(BASE_URL)
        .build()

interface MarsApiService {

    @GET("realestate") // specifies the path/endpoint appended to base url that this method will use
    suspend fun getProperties(): List<MarsProperty> // turn function into suspend, meaning it can run without blocking

}

// To create a RetroFit service you call the create passing in the service interface api we created above
object MarsApi {

    val retrofitService : MarsApiService by lazy { // When you call MarsApi.retrofitService, it will return a RetroFit object that implements MarsApiService
        retrofit.create(MarsApiService::class.java)
    }

}
