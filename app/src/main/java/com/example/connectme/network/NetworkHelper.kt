package com.example.connectme.network

import android.content.Context
//import coil.util.CoilUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import uy.kohesive.injekt.injectLazy
import java.io.File
import java.util.concurrent.TimeUnit

class NetworkHelper(context: Context) {

//    private val preferences: PreferencesHelper by injectLazy()

    private val cacheDir = File(context.cacheDir, "network_cache")

    private val cacheSize = 5L * 1024 * 1024 // 5 MiB

    val cookieManager = AndroidCookieJar()

    private val baseClientBuilder: OkHttpClient.Builder
        get() {
            val builder = OkHttpClient.Builder()
                .cookieJar(cookieManager)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(UserAgentInterceptor())


//                PREF_DOH_CLOUDFLARE -> builder.dohCloudflare()
//                PREF_DOH_GOOGLE -> builder.dohGoogle()
//                PREF_DOH_ADGUARD -> builder.dohAdGuard()
//            }

            return builder
        }

    val client by lazy { baseClientBuilder.cache(Cache(cacheDir, cacheSize)).build() }

//    val coilClient by lazy { baseClientBuilder.cache(CoilUtils.createDefaultCache(context)).build() }

}
