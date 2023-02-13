package com.yerayyas.footballmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yerayyas.footballmanager.R
import com.yerayyas.footballmanager.model.Player

class TeamAdapter(players: List<Player>, applicationContext: Context) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    var players: List<Player> = ArrayList()
    private lateinit var context: Context



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = players[position]
        holder.bind(item, context)
        Glide.with(context).load(players[position].photo).into(holder.playerPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return players.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val playerName = view.findViewById(R.id.tvName) as TextView
        val playerPhoto = view.findViewById(R.id.ivPlayer) as ImageView

        fun bind(player: Player, context: Context) {
            playerName.text = player.name
            playerPhoto.loadUrl(player.photo)
        }

        fun ImageView.loadUrl(url: String) {
            Glide.with(context).load(url).into(this)
        }

    }


}