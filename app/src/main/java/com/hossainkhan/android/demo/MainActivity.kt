package com.hossainkhan.android.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hossainkhan.android.demo.data.AppDataStore
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var appDataStore: AppDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("Got data: ${appDataStore.isFirstTime()}")
        appDataStore.updateFirstTimeUser(false)
    }
}
