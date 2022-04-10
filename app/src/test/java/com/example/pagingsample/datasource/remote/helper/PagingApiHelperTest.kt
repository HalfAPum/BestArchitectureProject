package com.example.pagingsample.datasource.remote.helper

import com.apollographql.apollo3.api.Query
import com.example.CharactersPagingQuery
import com.example.pagingsample.MockitoTest
import com.example.pagingsample.datasource.remote.api.BaseApi
import com.example.pagingsample.datasource.remote.mapper.base.ListMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class PagingApiHelperTest : MockitoTest {

    private lateinit var pagingApiHelper: PagingApiHelper<Query.Data, Any>

    @Mock
    lateinit var baseApi: BaseApi<Query.Data>

    @Mock
    lateinit var mapper: ListMapper<Query.Data, Any>

    @Before
    fun setUp() {
        pagingApiHelper = PagingApiHelper(baseApi, mapper)
    }

    @Test
    fun `verify network method was called with appropriate parameter`() = runTest {
        argumentCaptor<Int>().apply {
            pagingApiHelper.load(any())

            verify(baseApi).getPagingItems(capture())
        }
    }

    @Test
    fun `verify mapper method was called`() = runTest {
        whenever(baseApi.getPagingItems(any())).doReturn(
            CharactersPagingQuery.Data(
                CharactersPagingQuery.Characters(
                    listOf(CharactersPagingQuery.Result("123","testName"))
                )
            )
        )

        pagingApiHelper.load(any())

        verify(mapper).map(any())
    }

    @Test
    fun `check that if network request returns null then load() returns emptyList`() = runTest {
        val result = pagingApiHelper.load(any())

        assertThat(result).isEmpty()
    }

    @Test
    fun `compare ApiHelper pagingStart value and baseApi pagingStart value must be identical`() {
        argumentCaptor<Int>().apply {
            whenever(baseApi.pagingStart).doReturn(11)

            assertThat(pagingApiHelper.pagingStart).isEqualTo(baseApi.pagingStart)
        }
    }

}