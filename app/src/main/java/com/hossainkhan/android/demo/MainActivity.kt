package com.hossainkhan.android.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hossainkhan.android.demo.data.AppDataStore
import com.hossainkhan.android.demo.layoutpositioning.PositioningHorizontalActivity
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

    // FIXME - Test code
    fun goSomewhere(view: View?) {
        startActivity(PositioningHorizontalActivity
                .createStartIntent(this, R.layout.activity_positioning_top_left))

    }
}
