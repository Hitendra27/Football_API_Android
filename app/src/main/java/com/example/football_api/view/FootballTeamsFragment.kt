package com.example.football_api.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football_api.R
import com.example.football_api.adapter.FootballTeamAdapter
import com.example.football_api.databinding.FragmentFootballTeamsBinding
import com.example.football_api.model.FootballTeamResponse
import com.example.football_api.utils.UIState
import com.example.football_api.viewmodel.FootballTeamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FootballTeamsFragment : Fragment() {

    private val binding by lazy {
        FragmentFootballTeamsBinding.inflate(layoutInflater)
    }

    private val footballTeamViewModel: FootballTeamViewModel by viewModel()
    private lateinit var footballTeamAdapter: FootballTeamAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_football_teams, container, false)

        footballTeamAdapter = FootballTeamAdapter()
        binding.footballteamList.layoutManager=LinearLayoutManager(context)
        binding.footballteamList.adapter = footballTeamAdapter
        footballTeamViewModel.footballTeamLiveData.observe(viewLifecycleOwner,{ response ->
            when (response) {
                is UIState.LOADING -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.footballteamList.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    val footballData = response.success as FootballTeamResponse
                    footballTeamAdapter.loadData(footballData.data.standings)
                    footballTeamAdapter.notifyDataSetChanged()
                    binding.progressCircular.visibility = View.GONE
                    binding.footballteamList.visibility = View.VISIBLE
                }
                is UIState.ERROR -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(context, response.error.message,
                    Toast.LENGTH_LONG).show()
                }
            }

        })
        footballTeamViewModel.subscribeToFootballTeamInfo()
        return binding.root
    }
}