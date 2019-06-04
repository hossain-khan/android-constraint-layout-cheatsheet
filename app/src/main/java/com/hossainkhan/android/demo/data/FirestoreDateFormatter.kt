/*
 * Copyright (c) 2019 Hossain Khan
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

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object FirestoreDateFormatter {
    fun date(timestamp: Long): String {
        val sfd = SimpleDateFormat("EEEE, MMMM d", Locale.CANADA)
        return sfd.format(Date(timestamp * 1000)) // NOTE: x1000 is Firebase specific conversion
    }

    fun time(timestamp: Long): String {
        val sfd = SimpleDateFormat("hh:mm aa", Locale.CANADA)
        return sfd.format(Date(timestamp * 1000)) // NOTE: x1000 is Firebase specific conversion
    }
}