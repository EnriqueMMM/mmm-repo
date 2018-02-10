package com.app.test.testapp

import android.content.Context
import android.preference.PreferenceManager
import com.app.test.testapp.data.local.LocalDataSource
import com.app.test.testapp.repository.TestRepository
import org.koin.android.module.AndroidModule

/**
 * Created by Enrique on 2/9/2018.
 */
class AppModule : AndroidModule() {

    override fun context() = applicationContext {

        provide { provideLocalDataSource(androidApplication.applicationContext) }

        provide { provideTestRepository(get()) }

//        provide { provideRemoteAPIServiceInterface(get()) }
//        provide { provideGSON() }
//        provide { provideOkHttp() }
//        provide { provideRetrofit(get(), get()) }

        provide { PreferenceManager.getDefaultSharedPreferences(androidApplication) }
    }


    private fun provideLocalDataSource(context: Context) = LocalDataSource.buildPersistentInstance(context)
    private fun provideTestRepository(localDataSource: LocalDataSource) = TestRepository(localDataSource)

//    private fun provideRemoteAPIServiceInterface(retrofit: Retrofit): RemoteAPIServiceInterface = retrofit.create(RemoteAPIServiceInterface::class.java)
//
//    private fun provideGSON(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().setLenient().create()
//
//    private fun provideOkHttp(): OkHttpClient {
//        val okHttp = OkHttpClient.Builder()
//        okHttp.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
////        okHttp.addInterceptor { p0 ->
////            val original = p0.request()
////            // Customize the request
////            val request = original?.newBuilder()
//////                            ?.method(original.method(), original.body())
////                    ?.build()
////            val response = p0.proceed(request)
////            Log.v("Response", response.toString())
////            // Customize or return the response
////            response
////        }.build()
//        okHttp.readTimeout(30, TimeUnit.SECONDS)
//        okHttp.writeTimeout(20, TimeUnit.SECONDS)
//        okHttp.connectTimeout(20, TimeUnit.SECONDS)
//
//        return okHttp.build()
//    }
//
//    private fun provideRetrofit(gson: Gson, ok: OkHttpClient): Retrofit = Retrofit.Builder()
//            .baseUrl(RemoteAPIContract.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .client(ok)
//            .build()
}