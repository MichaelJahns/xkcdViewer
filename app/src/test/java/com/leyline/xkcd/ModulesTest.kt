package com.leyline.xkcd

import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class ModulesTest : KoinTest {
    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }
    @Test
    fun checkAllModules() = checkModules {
        modules(networkingModule)
    }
}