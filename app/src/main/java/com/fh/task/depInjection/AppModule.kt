package com.fh.task.depInjection

import android.content.Context
import androidx.room.Room
import com.fh.task.Utils.Constants
import com.fh.task.Utils.Constants.DATABASE_NAME
import com.fh.task.data.local.TaskDatabase
import com.fh.task.data.remote.TaskApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .client(client)
            .build()

    }


    @Singleton
    @Provides
    fun provideTaskApi(retrofit: Retrofit): TaskApi =
        retrofit.create(TaskApi::class.java)


    @Singleton
    @Provides
    fun provideTaskDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, TaskDatabase::class.java, DATABASE_NAME)
            .build()



    @Singleton
    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase) = taskDatabase.taskDao()


}