package com.example.lloydstask

import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before

@ExperimentalCoroutinesApi
open class BaseTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    @Before
    open fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }
}