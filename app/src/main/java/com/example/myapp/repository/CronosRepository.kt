package com.example.myapp.repository



import com.example.myapp.model.Cronos
import com.example.myapp.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CronosRepository @Inject constructor (private val cronosDatabaseDao:
                                            CronosDatabaseDao
){
    suspend fun addCrono(crono: Cronos) = cronosDatabaseDao.insert(crono)
    suspend fun updateCrono(crono:Cronos) = cronosDatabaseDao.update(crono)
    suspend fun deleteCrono(crono:Cronos)=cronosDatabaseDao.delete(crono)

    fun getAllCronos(): Flow<List<Cronos>> =
        cronosDatabaseDao.getCronos().flowOn(Dispatchers.IO)

    fun getCronosById(id:Long): Flow<Cronos> =
        cronosDatabaseDao.getCronosById(id).flowOn(Dispatchers.IO)
}