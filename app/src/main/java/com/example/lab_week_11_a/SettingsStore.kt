package com.example.lab_week_11_a

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settingsStore"
)

class SettingsStore(private val context: Context) {
    // The text flow is used to notify the view model when the text changes
    val text: Flow<String> = context.dataStore.data
        .map { preferences ->
            // Use the key defined in the companion object
            preferences[KEY_TEXT] ?: ""
        }

    // Save the text to the data store
    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            // Use the key defined in the companion object
            preferences[KEY_TEXT] = text
        }
    }

    // The key used to access the data in the data store
    companion object {
        // Correctly defines the key as a Preferences.Key<String>
        val KEY_TEXT = stringPreferencesKey("key_text")
    }
}