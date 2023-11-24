package uz.swlu.lugatapp.pref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPref @Inject constructor(
    @ApplicationContext context: Context
) : Pref {

    private val pref = context.getSharedPreferences("cache", Context.MODE_PRIVATE)

    var startScreen: Boolean
        set(value) {
            pref.edit().putBoolean("startScreen", value).apply()
        }
        get() = pref.getBoolean("startScreen", false)


    var tableType: Int
        set(value) {
            pref.edit().putInt("tableType", value).apply()
        }
        get() = pref.getInt("tableType", 1)

    var lastVersion: Int
        set(value) {
            pref.edit().putInt("lastVersion", value).apply()
        }
        get() = pref.getInt("lastVersion", 0)

}