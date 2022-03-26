package com.example.pagingsample.datasource.local

import androidx.room.Room
import androidx.room.Transaction
import com.example.pagingsample.utils.SuspendVoidCallback

interface ITransactionManager {

    /**
     * Executes the specified [SuspendVoidCallback] in a database [Transaction]. The [Transaction] will be
     * marked as successful unless an [Exception] is thrown in the [SuspendVoidCallback].
     *
     * [Room] will only perform at most one [Transaction] at a time.
     */
    suspend fun runInTransaction(action: SuspendVoidCallback)

}