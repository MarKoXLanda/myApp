package com.example.myapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapp.model.Usuario
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUser(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("User_Data")

        val USUARIO = stringPreferencesKey("usuario")
        val PASS = stringPreferencesKey("pass")
        val NOMBRE = stringPreferencesKey("nombre")
        val CORREO = stringPreferencesKey("correo")
        val TELEFONO = stringPreferencesKey("telefono")
        val COMPLETO = booleanPreferencesKey("completo")
    }

    // Lee todo lo guardado y lo regresa como un objeto Usuario
    val getUser: Flow<Usuario> = context.dataStore.data.map { preferences ->
        Usuario(
            usuario = preferences[USUARIO] ?: "",
            pass = preferences[PASS] ?: "",
            nombre = preferences[NOMBRE] ?: "",
            correo = preferences[CORREO] ?: "",
            telefono = preferences[TELEFONO] ?: "",
            completo = preferences[COMPLETO] ?: false
        )
    }

    // Pantalla Login: solo usuario y contraseña
    suspend fun saveLogin(usuario: String, pass: String) {
        context.dataStore.edit { preferences ->
            preferences[USUARIO] = usuario
            preferences[PASS] = pass
        }
    }

    // Pantalla Registro: datos completos y marca el perfil como completo
    suspend fun saveDatos(nombre: String, usuario: String, pass: String, correo: String, telefono: String) {
        context.dataStore.edit { preferences ->
            preferences[NOMBRE] = nombre
            preferences[USUARIO] = usuario
            preferences[PASS] = pass
            preferences[CORREO] = correo
            preferences[TELEFONO] = telefono
            preferences[COMPLETO] = true
        }
    }

    // Botón borrar: limpia todo el DataStore
    suspend fun clear() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }
}
