package com.florinda.store

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.florinda.di.appModule
import com.florinda.di.dispatcherModules
import com.florinda.di.networkModules
import com.florinda.di.repositoryModules
import com.florinda.di.useCaseModules
import com.florinda.store.di.connectivityModule
import com.florinda.store.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.lazyModules
import timber.log.Timber

class FlorindaStoreApp : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoinDI(this@FlorindaStoreApp)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    // 10 % from remaining ram size
                    .maxSizePercent(0.1)
                    // image not be garbage collected
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    // 3% % of  remaining disk space
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }


    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}

/**startKoin { } - create a [KoinApplication] container configuration
 * and register it in the [GlobalContext] to allow the use of GlobalContext API
 * */

@OptIn(KoinExperimentalAPI::class)
fun startKoinDI(context: Context){
    startKoin {
        androidLogger()
        androidContext(context)
        lazyModules(
            connectivityModule,
            appModule,
            networkModules,
            dispatcherModules,
            repositoryModules,
            useCaseModules,
            viewModelModules
        )
    }
}