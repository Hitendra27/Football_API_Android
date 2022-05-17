package com.example.football_api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.football_api.databinding.FragmentFootballTeamsBinding
import com.example.football_api.databinding.TeamStatsLayoutBinding
import com.example.football_api.model.FootballTeam
import com.example.football_api.model.Standing
import com.example.football_api.utils.loadImagefromUrl

class FootballTeamAdapter (
    private val listOfTeams: MutableList<Standing> = mutableListOf()
    ) : RecyclerView.Adapter<TeamsViewHolder>() {

    var onItemClick: ((Standing, Int) -> Unit)? = null

    fun loadData(listOfTeams: MutableList<Standing>) {
        this.listOfTeams.addAll(listOfTeams)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(
            TeamStatsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.setFootballTeamData(listOfTeams[position], position)
        holder.itemView.rootView.setOnClickListener {
            onItemClick?.invoke(listOfTeams[position], position)
        }
    }

    override fun getItemCount(): Int {
        return listOfTeams.size

    }
}

    class TeamsViewHolder(itemView: TeamStatsLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val teamName: TextView = itemView.teamName
        val teamDescription: TextView = itemView.teamDescription
        val teamRank: TextView = itemView.teamRank
        val teamLogo: ImageView = itemView.teamLogoImage

        fun setFootballTeamData(stand: Standing, positon: Int) {
            teamName.text = stand.team.displayName
            teamDescription.text = stand.note?.description?.toString()
            teamRank.text = stand.note?.rank?.toString()
            teamLogo.loadImagefromUrl(stand.team.logos.get(0).href)
        }
    }
