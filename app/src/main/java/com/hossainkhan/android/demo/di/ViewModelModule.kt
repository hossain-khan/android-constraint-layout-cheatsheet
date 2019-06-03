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

package com.hossainkhan.android.demo.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hossainkhan.android.demo.ui.browse.LayoutBrowseViewModel
import com.hossainkhan.android.demo.ui.layoutpreview.LayoutInfoViewModel
import com.hossainkhan.android.demo.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Uses dagger multi-binding to provide [ViewModel] instances used in the app.
 *
 * @see <a href="https://dagger.dev/multibindings">Multibindings</a>
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LayoutBrowseViewModel::class)
    abstract fun bindLayoutBrowserViewModel(layoutBrowseViewModel: LayoutBrowseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LayoutInfoViewModel::class)
    abstract fun bindLayoutInfoViewModel(layoutInfoViewModel: LayoutInfoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}
