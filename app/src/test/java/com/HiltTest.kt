package com

//import dagger.hilt.android.testing.HiltAndroidRule
import androidx.annotation.CallSuper
import org.junit.Before

/**
 * Setups Hilt for test.
 */
interface HiltTest {

//    /**
//     * [Rule] order is 0 because [HiltAndroidRule] should always be first rule.
//     */
//    @get:Rule(order = 0)
//    val hiltRule: HiltAndroidRule
//        get() = HiltAndroidRule(this)
//
    @Before
    @CallSuper
    fun setup() {
//        hiltRule.inject()
    }

}