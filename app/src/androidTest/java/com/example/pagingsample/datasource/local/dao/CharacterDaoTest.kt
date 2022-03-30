package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoTest
import com.example.pagingsample.model.local.character.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class CharacterDaoTest: BaseDaoTest<Character>() {

    override val singleItem = EmulatedData.character
    override val itemList = EmulatedData.characterList

    override fun initDao() { dao = db.characterDao() }

    override fun Character.transform() = copy(name = "TEST VALUE THAT DO NOT REPEAT")

}