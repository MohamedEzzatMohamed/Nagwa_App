package com.example.nagwaapp.di

//import android.content.Context
//import androidx.room.Room
//import com.example.nagwaapplicationtask.NagwaApplication
//import com.example.nagwaapplicationtask.network.ApiInterface
//import com.example.nagwaapplicationtask.utils.Constant
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton

/**
 * Application Module to set the provider for hilt to inject the dependency
 * for each the location viewModel and the Database
 * and make it a single object for the whole application
 */
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {

//    @Singleton
//    @Provides
//    fun provideLocationDataBase(
//        @ApplicationContext context: Context
//    ) = Room.databaseBuilder(
//        context,
//        LocationDatabase::class.java,
//        LOCATION_DATABASE_NAME
//    ).build()
//
//    @Singleton
//    @Provides
//    fun provideDAO(db : LocationDatabase) = db.locationDatabaseDao()

//    @Provides
//    @Singleton
//    fun provideApplication(@ApplicationContext app: Context): NagwaApplication{
//        return app as NagwaApplication
//    }

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit =
//        Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideRestaurantApi(retrofit: Retrofit): ApiInterface =
//        retrofit.create(ApiInterface::class.java)
//}