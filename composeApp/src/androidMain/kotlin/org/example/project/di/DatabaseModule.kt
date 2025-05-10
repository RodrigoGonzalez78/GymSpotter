package org.example.project.di

import android.content.Context
import com.gymtracker.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual fun createDatabase(): Database {
    val driver = AndroidSqliteDriver(
        schema = Database.Schema,
        context = androidContext(),
        name = "gymtracker.db"
    )
    return Database(driver)
}

fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(
            databaseModule,
            dataSourceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}