package com.example.pagingsample.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pagingsample.datasource.local.helper.ClearAllItemsAndKeysDaoHelper
import com.example.pagingsample.datasource.local.helper.GetPagingSourceDaoHelper
import com.example.pagingsample.datasource.local.helper.RemoteMediatorDaoHelper
import com.example.pagingsample.datasource.local.helper.SaveItemsWithRemoteKeysDaoHelper
import com.example.pagingsample.datasource.paging.BaseRemoteMediator
import com.example.pagingsample.datasource.paging.PagerWrapper
import com.example.pagingsample.datasource.remote.api.query.*
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.repository.PagingRepository
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import timber.log.Timber
import java.lang.reflect.ParameterizedType

/**
 * Generic factory for one generic type object.
 */
inline fun <reified T> Module.genericFactory(
    noinline definition: Definition<T>
) = factory(genericQualifier<T>(), definition)

inline fun <reified T : Any> Scope.genericGet(): T {
    return get(genericQualifier<T>())
}

@RequiresApi(Build.VERSION_CODES.P)
inline fun <reified T> genericQualifier(): Qualifier? {
    val classType = T::class.java
    val parameterizedType = (classType.genericSuperclass as? ParameterizedType)
        ?: classType.genericInterfaces.firstOrNull() as? ParameterizedType
    Timber.d("tag1 LOL WTF ${parameterizedType?.actualTypeArguments?.firstOrNull() ?: parameterizedType?.actualTypeArguments?.get(1)}")
    return parameterizedType?.actualTypeArguments?.firstOrNull()?.let { named(it.toString()) }
}

val webServiceModule = module {
    genericFactory { CharacterApiQuery() }
    genericFactory { LocationApiQuery() }
    genericFactory { EpisodeApiQuery() }

    genericFactory { CharacterDetailsApiQuery() }
    genericFactory { LocationDetailsApiQuery() }
    genericFactory { EpisodeDetailsApiQuery() }

//    genericFactory<ItemApiHelper<*, CharacterWithDetails>> { ItemApiHelper(get(), CharacterDetailsMapper()) }
//    genericFactory<ItemApiHelper<*, LocationWithDetails>> { ItemApiHelper(get(), LocationDetailsMapper()) }
//    genericFactory<ItemApiHelper<*, EpisodeWithDetails>> { ItemApiHelper(get(), EpisodeDetailsMapper()) }

    factory { PagingRepository<Character>(get()) }

    factory { PagerWrapper<Character>(get(), get(), genericGet()) }

    genericFactory { GetPagingSourceDaoHelper<Character>(genericGet()) }

    factory { BaseRemoteMediator<Character>(get(), genericGet()) }

    factory { RemoteMediatorDaoHelper<Character>(get(), get(), get()) }

    factory { SaveItemsWithRemoteKeysDaoHelper<Character>(genericGet(), get(), get()) }

    factory { ClearAllItemsAndKeysDaoHelper<Character>(genericGet(), get(), get()) }
}