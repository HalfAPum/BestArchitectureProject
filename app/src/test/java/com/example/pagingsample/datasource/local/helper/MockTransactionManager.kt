package com.example.pagingsample.datasource.local.helper

import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.utils.SuspendVoidCallback

open class MockTransactionManager : ITransactionManager {

    /**
     * Dummy implementation of [runInTransaction].
     */
    override suspend fun runInTransaction(
        action: SuspendVoidCallback
    ) = action.invoke()

}