package com.example.pagingsample.datasource.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import com.apollographql.apollo3.exception.JsonDataException
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
suspend fun runPagingCatchingException(block: suspend () -> MediatorResult): MediatorResult {
    return try {
        block.invoke()
    } catch (exception: IOException) {
        MediatorResult.Error(exception)
    } catch (exception: JsonDataException) {
        MediatorResult.Error(exception)
    }
}


val Int.nextKey: Int
    get() = this + 1

val Int.prevKey: Int
    get() = this - 1


fun <T : Any> PagingState<Int, T>.findFirstLoadedItem() =
    pages.find { it.data.isNotEmpty() }?.data?.firstOrNull()

fun <T : Any> PagingState<Int, T>.findLastLoadedItem() =
    pages.findLast { it.data.isNotEmpty() }?.data?.lastOrNull()

fun <T : Any> PagingState<Int, T>.findClosestItemToCurrentPosition() =
    anchorPosition?.let { position -> closestItemToPosition(position) }


fun PageResult(page: Int) = LoadTypeResult.PageResult(page)

@OptIn(ExperimentalPagingApi::class)
fun MediatorSuccessResult(
    endOfPaginationReached: Boolean
) = LoadTypeResult.MediatorSuccessResult(
        MediatorResult.Success(endOfPaginationReached)
    )