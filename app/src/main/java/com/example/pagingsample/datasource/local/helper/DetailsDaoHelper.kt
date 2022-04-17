package com.example.pagingsample.datasource.local.helper

import com.example.pagingsample.datasource.local.ITransactionManager
import com.example.pagingsample.datasource.local.dao.EpisodeCharacterCrossRefDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.datasource.local.dao.character.CharacterDetailsDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDao
import com.example.pagingsample.datasource.local.dao.episode.EpisodeDetailsDao
import com.example.pagingsample.datasource.local.dao.location.LocationDao
import com.example.pagingsample.datasource.local.dao.location.LocationDetailsDao
import com.example.pagingsample.model.character.CharacterWithDetails
import com.example.pagingsample.model.crossref.EpisodeCharacterCrossRef
import com.example.pagingsample.model.episode.EpisodeWithDetails
import com.example.pagingsample.model.location.LocationWithDetails
import javax.inject.Inject

class SaveCharacterDetailsDaoHelper @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterDetailsDao: CharacterDetailsDao,
    private val transactionManager: ITransactionManager,
) : SaveDaoHelper<CharacterWithDetails> {

    override suspend fun save(item: CharacterWithDetails) {
        transactionManager.runInTransaction {
            characterDao.insertIgnore(item.character)
            characterDetailsDao.insert(item.details)
        }
    }

}

class SaveLocationDetailsDaoHelper @Inject constructor(
    private val locationDao: LocationDao,
    private val locationDetailsDao: LocationDetailsDao,
    private val transactionManager: ITransactionManager,
) : SaveDaoHelper<LocationWithDetails> {

    override suspend fun save(item: LocationWithDetails) {
        transactionManager.runInTransaction {
            locationDao.insertIgnore(item.location)
            locationDetailsDao.insert(item.details)
        }
    }

}

class SaveEpisodeDetailsDaoHelper @Inject constructor(
    private val episodeCharacterCrossRefDao: EpisodeCharacterCrossRefDao,
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val episodeDetailsDao: EpisodeDetailsDao,
    private val transactionManager: ITransactionManager,
) : SaveDaoHelper<EpisodeWithDetails> {

    override suspend fun save(item: EpisodeWithDetails) {
        transactionManager.runInTransaction {
            episodeDao.insertIgnore(item.episode)
            episodeDetailsDao.insert(item.detailsWithCharacters.details)
            characterDao.insertItemsIgnore(item.detailsWithCharacters.characters)
            val crossRef = item.detailsWithCharacters.characters.map {
                EpisodeCharacterCrossRef(item.episode.id, it.id)
            }
            episodeCharacterCrossRefDao.insertItems(crossRef)
        }
    }

}

class LoadCharacterWithDetailsFlowDaoHelper @Inject constructor(
    private val itemDao: CharacterDetailsDao
) : LoadFlowDaoHelper<CharacterWithDetails> {

    override fun flow(id: String) = itemDao.flow(id)

}

class LoadLocationWithDetailsFlowDaoHelper @Inject constructor(
    private val itemDao: LocationDetailsDao
) : LoadFlowDaoHelper<LocationWithDetails> {

    override fun flow(id: String) = itemDao.flow(id)

}

class LoadEpisodeWithDetailsFlowDaoHelper @Inject constructor(
    private val itemDao: EpisodeDetailsDao
) : LoadFlowDaoHelper<EpisodeWithDetails> {

    override fun flow(id: String) = itemDao.flow(id)

}