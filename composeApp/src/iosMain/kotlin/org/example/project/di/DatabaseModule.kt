package org.example.project.di


import com.gymtracker.Database
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDatabase(): Database {
    val driver = NativeSqliteDriver(
        schema = Database.Schema,
        name = "gymtracker.db"
    )
    return Database(driver)
}