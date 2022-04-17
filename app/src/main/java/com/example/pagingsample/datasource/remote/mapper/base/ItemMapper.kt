package com.example.pagingsample.datasource.remote.mapper.base

interface ItemMapper<SERVER, RESULT> {

    fun map(serverData: SERVER) : RESULT?

}