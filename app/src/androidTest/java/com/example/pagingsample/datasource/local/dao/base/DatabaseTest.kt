package com.example.pagingsample.datasource.local.dao.base

import androidx.annotation.CallSuper
import com.example.pagingsample.datasource.local.AppDatabase
import com.example.pagingsample.datasource.local.ITransactionManager
import org.junit.After

interface DatabaseTest {

    /**
     * Target database for tests.
     */
    var db: AppDatabase

    val transactionManager: ITransactionManager
        get() = db

    /**
     * Free database after test.
     */
    @After
    @CallSuper
    fun teardown() = db.close()

}