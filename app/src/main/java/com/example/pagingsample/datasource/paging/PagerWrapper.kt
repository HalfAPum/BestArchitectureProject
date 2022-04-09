package com.example.pagingsample.datasource.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pagingsample.datasource.local.helper.GetPagingSourceDaoHelper
import com.example.pagingsample.model.interfaces.Identifiable
import javax.inject.Inject

class PagerWrapper<T : Identifiable> @Inject constructor(
    pagingConfig: PagingConfig,
    baseRemoteMediator: BaseRemoteMediator<T>,
    getPagingSourceDaoHelper: GetPagingSourceDaoHelper<T>
) {

    @OptIn(ExperimentalPagingApi::class)
    val pager = Pager(
        config = pagingConfig,
        remoteMediator = baseRemoteMediator,
        pagingSourceFactory = { getPagingSourceDaoHelper.getPagingSource() }
    )

    val flow = pager.flow

}