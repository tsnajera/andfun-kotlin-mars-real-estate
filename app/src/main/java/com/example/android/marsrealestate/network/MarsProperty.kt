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

import com.squareup.moshi.Json

// Create data class that will have matching properties of the JSON response we are expecting
// Look at JSON response that we will call and model this class after it
// Try using field names that match the names of the response because Moshi does a direct name match
// If you will use a different name, then use the annotation as shown for img_src
class MarsProperty(
        val id: String,
        @Json(name = "img_src")
        val imgSrcUrl: String,
        val type: String,
        val price: Double
)
