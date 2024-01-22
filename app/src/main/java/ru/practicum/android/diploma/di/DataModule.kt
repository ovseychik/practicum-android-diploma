package ru.practicum.android.diploma.di

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.network.HHApi
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.util.GlobalConstant


val dataModule = module {
    single<HHApi> {
        Retrofit.Builder()
            .baseUrl(GlobalConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HHApi::class.java)
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
}
