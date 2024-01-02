package com.florinda.data.data_source.local.dataStore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.florinda.domain.repository.IPreferenceDataStoreAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(
    name = "PreferenceDataStore"
)


class DataStoreImpl constructor(
    private val context: Context
) : IPreferenceDataStoreAPI {

    //dataSource access the DataStore file and does the manipulation based on our requirements.
    private val dataStore = context.dataStore

    override suspend fun <T> getPreferencs(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return  dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            val result = it[key] ?: defaultValue
            result
        }
    }

    override suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit {
            it.remove(key)
        }
    }

    override suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit {
            it[key] = value
        }
    }

    override suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T): T  =
        dataStore.data.first()[key] ?: defaultValue



    override suspend fun <T> clearAllPreference() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}