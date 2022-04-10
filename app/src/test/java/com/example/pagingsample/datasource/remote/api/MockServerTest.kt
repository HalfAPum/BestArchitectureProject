package com.example.pagingsample.datasource.remote.api

import com.HiltTest
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloHttpException
import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import com.example.CharactersPagingQuery
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MockServerTest : HiltTest {

    @Inject
    lateinit var mockServer: MockServer

    private lateinit var apolloClient: ApolloClient

    private lateinit var graphQLExecutor: GraphQLExecutor

    @Before
    override fun setup() {
        super.setup()
        runTest {
            apolloClient = ApolloClient.Builder()
                .serverUrl(mockServer.url()).build()
        }
        graphQLExecutor = GraphQLExecutor(apolloClient)
    }

    @After
    fun teardown() = runTest {
        mockServer.stop()
    }

    @Test
    fun `execute query with real data`() = runTest {
        mockServer.enqueue(
            MockResponse(
                """{"data": {"characters": {"results": [
                {"id": "1","name": "Rick Sanchez"}]}}}""".trimMargin()
            )
        )

        val result = graphQLExecutor.executeQuery(CharactersPagingQuery(0))

        assertThat(result).isNotNull()
    }

    @Test(expected = ApolloHttpException::class)
    fun `execute query with internal server exception`() = runTest {
        mockServer.enqueue(
            MockResponse(
            body = "Internal server error",
            statusCode = 500,
            delayMillis = 1000L,
        )
        )

        graphQLExecutor.executeQuery(CharactersPagingQuery(0))
    }

}