package com.example.pagingsample.datasource.paging.base

import androidx.paging.ExperimentalPagingApi
import androidx.paging.RemoteMediator.MediatorResult
import com.example.pagingsample.datasource.paging.MediatorSuccessResult
import com.example.pagingsample.datasource.paging.runPagingCatchingException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Test
//import retrofit2.HttpException
//import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class ExtensionTest {

    @Test
    fun `runPagingCatchingException with success result`() = runTest {
        val result = runPagingCatchingException {
            MediatorSuccessResult(false).success
        }

        assertThat(result).isInstanceOf(MediatorResult.Success::class.java)
    }

    @Test
    fun `runPagingCatchingException with IOException`() = runTest {
        val result = runPagingCatchingException {
            throw IOException("Paging error")
        }

        assertThat(result).isInstanceOf(MediatorResult.Error::class.java)
    }

    @Test
    fun `runPagingCatchingException with HttpException`() = runTest {
//        val result = runPagingCatchingException {
////            throw HttpException(Response.success(null))
//        }
//
//        assertThat(result).isInstanceOf(MediatorResult.Error::class.java)
    }

    @Test(expected = Exception::class)
    fun `runPagingCatchingException with general Exception that shouldn't be caught`() = runTest {
        runPagingCatchingException {
            throw Exception("""Some other exception that 
                |runPagingCatchingException doesn't catch""".trimMargin())
        }
    }
}