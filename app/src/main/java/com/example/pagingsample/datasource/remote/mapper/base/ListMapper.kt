package com.example.pagingsample.datasource.remote.mapper.base

interface ListMapper<SERVER, RESULT> {

    fun map(serverData: SERVER) : List<RESULT>

}