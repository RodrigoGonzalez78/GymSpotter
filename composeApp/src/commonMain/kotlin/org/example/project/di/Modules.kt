package org.example.project.di

import org.example.project.data.datasources.ProfileDataSource
import org.example.project.domain.repositorios.ProfileRepository
import org.example.project.domain.usecases.CreateProfileUseCase
import org.example.project.domain.usecases.DeleteProfileUseCase
import org.example.project.domain.usecases.GetProfilesUseCase
import org.example.project.presentation.viewModels.ProfileViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(
            databaseModule,
            dataSourceModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}

val databaseModule = module {
    single { createDatabase() }
}

val dataSourceModule = module {
    single<ProfileDataSource> { SQLDelightProfileDataSource(get()) }
    // Other data sources
}

val repositoryModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    // Other repositories
}

val useCaseModule = module {
    factory { GetProfilesUseCase(get()) }
    factory { CreateProfileUseCase(get()) }
    factory { DeleteProfileUseCase(get()) }
    // Other use cases
}

val viewModelModule = module {
    factory { ProfileViewModel(get(), get(), get()) }
   // factory { WorkoutViewModel(get(), get()) }
    //factory { LoggingViewModel(get(), get()) }
}

expect fun createDatabase(): Database