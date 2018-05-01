/*
 * Copyright (c) 2018 Hossain Khan
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
 */

package com.hossainkhan.android.demo.data

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Application wide data storage and preferences backed by [SharedPreferences].
 */
class AppDataStore @Inject constructor(
        private val preferences: SharedPreferences) {

    private companion object {
        private const val PREF_KEY_IS_FIRST_TIME_USER = "KEY_IS_FIRST_TIME_USER"
    }

    fun isFirstTime(): Boolean {
        return preferences.getBoolean(PREF_KEY_IS_FIRST_TIME_USER, true)
    }

    fun updateFirstTimeUser(isFirstTime: Boolean) {
        preferences.edit().putBoolean(PREF_KEY_IS_FIRST_TIME_USER, isFirstTime).apply()
    }

}