package com.example.football_api.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.football_api.network.FootballTeamRepository
import com.example.football_api.utils.UIState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FootballTeamViewModelTest {


    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockRepository = mockk<FootballTeamRepository>(relaxed = true)

    private lateinit var target: FootballTeamViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        target = FootballTeamViewModel(mockRepository)
    }

    @After
    fun shutdown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get football teams names when trying to load from server returns loading state`() {
        // Assign
        val stateList = mutableListOf<UIState>()
        target.footballTeamLiveData.observeForever {
            stateList.add(it)
        }
        // Action
        target.subscribeToFootballTeamInfo()

        // Assert
        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[0]).isInstanceOf(UIState.LOADING::class.java)
    }

    @Test
    fun `get football id trying to load from server error state`() {
        // Assign
        every{ mockRepository.responseFlow} returns MutableStateFlow(UIState.ERROR(Exception("Error")))
        val stateList = mutableListOf<UIState>()
        target.footballTeamLiveData.observeForever {
            stateList.add(it)
        }

        // Action
        target.subscribeToFootballTeamInfo()

        // Assertion
        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(3)
        assertThat(stateList[1]).isInstanceOf(UIState.LOADING::class.java)
        assertThat(stateList[2]).isInstanceOf(UIState.ERROR::class.java)
    }

}