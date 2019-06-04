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

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object FirestoreDateFormatter {
    fun date(timestamp: Timestamp): String {
        val sfd = SimpleDateFormat("EEEE, MMMM d", Locale.CANADA)
        return sfd.format(timestamp.toDate())
    }

    fun date(date: Date): String {
        val sfd = SimpleDateFormat("MMMM d, yyyy", Locale.CANADA)
        return sfd.format(date)
    }
}