package com.androidvoyage.zakat.di

import android.app.Application
import androidx.room.Room
import com.androidvoyage.zakat.feature_nisab.data.data_source.NisabDao
import com.androidvoyage.zakat.feature_nisab.data.data_source.NisabDatabase
import com.androidvoyage.zakat.feature_nisab.data.repository.NisabRepositoryImpl
import com.androidvoyage.zakat.feature_nisab.domain.repository.NisabRepository
import com.androidvoyage.zakat.feature_nisab.domain.use_case.AddNisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.DeleteNisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.GetAllNisab
import com.androidvoyage.zakat.feature_nisab.domain.use_case.GetNisab
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case.NisabUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NisabDatabase {
        return Room.databaseBuilder(
            app,
            NisabDatabase::class.java,
            NisabDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NisabDatabase): NisabRepository {
        return NisabRepositoryImpl(db.nisabDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NisabRepository): NisabUseCases {
        return NisabUseCases(
            getAllNisab = GetAllNisab(repository),
            deleteNisab = DeleteNisab(repository),
            addNisab = AddNisab(repository),
            getNisab = GetNisab(repository)
        )
    }
}