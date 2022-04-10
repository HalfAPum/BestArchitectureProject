package com.example.pagingsample

import androidx.annotation.CallSuper
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

/**
 * Setups Hilt for test.
 */
interface InstrumentationHiltTest {

    /**
     * [Rule] order is 0 because [HiltAndroidRule] should always be first rule.
     */
    @get:Rule(order = 0)
    val hiltRule: HiltAndroidRule
        get() = HiltAndroidRule(this)

    @Before
    @CallSuper
    fun setup() {
        hiltRule.inject()
    }

}