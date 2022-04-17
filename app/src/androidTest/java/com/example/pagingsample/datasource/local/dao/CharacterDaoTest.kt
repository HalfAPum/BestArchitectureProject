package com.example.pagingsample.datasource.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pagingsample.datasource.local.dao.base.BaseDaoTest
import com.example.pagingsample.datasource.local.dao.character.CharacterDao
import com.example.pagingsample.model.character.Character
import com.example.pagingsample.utils.EmulatedData
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
class CharacterDaoTest: BaseDaoTest<Character, CharacterDao>() {

    override val singleItem = EmulatedData.character
    override val itemList = EmulatedData.characterList

    override fun Character.transform() = copy(name = "TEST VALUE THAT DO NOT REPEAT")

}