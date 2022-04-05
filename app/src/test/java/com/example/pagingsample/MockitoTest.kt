package com.example.pagingsample

import org.junit.Rule
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

interface MockitoTest {

    /**
     * Base rule for [Mockito] test.
     */
    @get:Rule
    val mockitoRule: MockitoRule
        get() = MockitoJUnit.rule()

}