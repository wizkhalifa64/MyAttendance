package com.example.myattendance.datahandler

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataHandler(private val context: Context) {
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        val TOKEN = stringPreferencesKey("token")

    }

    suspend fun setToken(token: String) {
        context.dataStore.edit { settings ->
            settings[TOKEN] = token
        }
    }

    val getToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[TOKEN] ?: ""
    }

}