package jp.co.cyberagent.dojo2019

import android.app.Application
import com.facebook.stetho.Stetho

class myapp: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}