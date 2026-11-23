package com.atom.room.state

data class CronosState(
        val cronometroActivo : Boolean = false,
        val showSaveButton : Boolean = false,
        val showTextField : Boolean = false,
        val tittle : String
        )