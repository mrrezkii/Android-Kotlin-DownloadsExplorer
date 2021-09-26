package id.ac.telkomuniversity.mrrezki

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber

class FileApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Timber.plant(Timber.DebugTree())
        Timber.e("RUN BASE APPLICATION")
    }
}