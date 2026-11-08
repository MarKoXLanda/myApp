package com.example.myapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapp.model.Partido
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreMarcador(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Marcador")

        val LOCAL = stringPreferencesKey("local")
        val VISITA = stringPreferencesKey("visita")
        val PTS_LOCAL = intPreferencesKey("pts_local")
        val PTS_VISITA = intPreferencesKey("pts_visita")
        val FALTAS_LOCAL = intPreferencesKey("faltas_local")
        val FALTAS_VISITA = intPreferencesKey("faltas_visita")
        val CUARTO = intPreferencesKey("cuarto")
        val TIEMPO = intPreferencesKey("tiempo")

        const val ULTIMO_CUARTO = 5

        fun duracion(cuarto: Int): Int = if (cuarto > 4) 300 else 600
    }

    val getPartido: Flow<Partido> = context.dataStore.data.map { p ->
        Partido(
            local = p[LOCAL] ?: "",
            visita = p[VISITA] ?: "",
            puntosLocal = p[PTS_LOCAL] ?: 0,
            puntosVisita = p[PTS_VISITA] ?: 0,
            faltasLocal = p[FALTAS_LOCAL] ?: 0,
            faltasVisita = p[FALTAS_VISITA] ?: 0,
            cuarto = p[CUARTO] ?: 1,
            tiempo = p[TIEMPO] ?: duracion(p[CUARTO] ?: 1)
        )
    }

    suspend fun nuevoPartido(local: String, visita: String) {
        context.dataStore.edit { p ->
            p[LOCAL] = local
            p[VISITA] = visita
            p[PTS_LOCAL] = 0
            p[PTS_VISITA] = 0
            p[FALTAS_LOCAL] = 0
            p[FALTAS_VISITA] = 0
            p[CUARTO] = 1
            p[TIEMPO] = duracion(1)
        }
    }

    suspend fun sumarPuntos(esLocal: Boolean, cantidad: Int) {
        val key = if (esLocal) PTS_LOCAL else PTS_VISITA
        context.dataStore.edit { p ->
            p[key] = ((p[key] ?: 0) + cantidad).coerceAtLeast(0)
        }
    }

    suspend fun sumarFalta(esLocal: Boolean, cantidad: Int) {
        val key = if (esLocal) FALTAS_LOCAL else FALTAS_VISITA
        context.dataStore.edit { p ->
            p[key] = ((p[key] ?: 0) + cantidad).coerceIn(0, 99)
        }
    }


    suspend fun cambiarCuarto(cuarto: Int) {
        val nuevo = cuarto.coerceIn(1, ULTIMO_CUARTO)
        context.dataStore.edit { p ->
            p[CUARTO] = nuevo
            p[TIEMPO] = duracion(nuevo)
            p[FALTAS_LOCAL] = 0
            p[FALTAS_VISITA] = 0
        }
    }


    suspend fun restarSegundo(): Int {
        var quedan = 0
        context.dataStore.edit { p ->
            val actual = p[TIEMPO] ?: duracion(p[CUARTO] ?: 1)
            quedan = (actual - 1).coerceAtLeast(0)
            p[TIEMPO] = quedan
        }
        return quedan
    }

    suspend fun reiniciarReloj() {
        context.dataStore.edit { p ->
            p[TIEMPO] = duracion(p[CUARTO] ?: 1)
        }
    }

    suspend fun borrarPartido() {
        context.dataStore.edit { p -> p.clear() }
    }
}
