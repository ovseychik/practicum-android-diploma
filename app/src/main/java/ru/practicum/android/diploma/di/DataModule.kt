package ru.practicum.android.diploma.di

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.SettingsStorage
import ru.practicum.android.diploma.data.db.AppDatabase
import ru.practicum.android.diploma.data.db.convertors.FavoriteVacanciesConvertors
import ru.practicum.android.diploma.data.network.HHApi
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.data.settings.SettingsStorageSharedPref
import ru.practicum.android.diploma.util.GlobalConstant

const val BASE_URL = "https://api.hh.ru"
const val HH_USER_AGENT: String = "HH-User-Agent: Get a job! (morozov@rtr.spb.ru)"

val dataModule = module {

    val headers = Headers.Builder().add("Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
        .add("HH-User-Agent: Get a job! (morozov@rtr.spb.ru)").build()
    val httpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val request = chain.request().newBuilder().headers(headers).build()
        chain.proceed(request)
    })

    single<HHApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(HHApi::class.java)
    }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .build()
    }

    single<NetworkClient> {
        RetrofitNetworkClient(hhService = get(), androidContext())
    }

    factory { Gson() }

    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            GlobalConstant.SETTINGS_APP,
            AppCompatActivity.MODE_PRIVATE
        )
    }
    single {
        FavoriteVacanciesConvertors(json = get())
    }
    single<SettingsStorage> {
        SettingsStorageSharedPref(json = get(), settingssharedPref = get())
    }
}
