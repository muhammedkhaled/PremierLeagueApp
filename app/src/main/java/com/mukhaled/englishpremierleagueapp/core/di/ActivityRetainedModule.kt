package com.mukhaled.englishpremierleagueapp.core.di


import com.mukhaled.englishpremierleagueapp.core.domain.repositories.HomeRepositoryInterface
import com.mukhaled.englishpremierleagueapp.core.data.HomeRepository
import com.mukhaled.englishpremierleagueapp.core.utils.CoroutineDispatchersProvider
import com.mukhaled.englishpremierleagueapp.core.utils.DispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindRepository(repository: HomeRepository): HomeRepositoryInterface

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider):
            DispatchersProvider

    companion object {
        @Provides
        fun provideCompositeDisposable() = CompositeDisposable()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun provideCoroutineScope() =
            CoroutineScope(Dispatchers.Default + SupervisorJob())

    }
}
