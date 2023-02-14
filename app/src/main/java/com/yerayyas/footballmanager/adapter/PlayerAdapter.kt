package com.yerayyas.footballmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yerayyas.footballmanager.databinding.ItemTeamBinding
import com.yerayyas.footballmanager.model.Player
import java.util.*

class PlayerAdapter(
    var players: List<Player>,
    private val movieClickedListener: (Player) -> Unit
) :

    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
        holder.itemView.setOnClickListener {
            movieClickedListener(player)
        }
    }

    override fun getItemCount(): Int = players.size


    class ViewHolder(private val binding: ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            val name = player.name
            val lastName = player.lastname
            "$name  $lastName".also { binding.tvPlayerFullName.text = it }
            binding.tvPlayerPosition.text = player.position
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } //capitalized
            Glide.with(binding.root.context)
                .load(player.photo)
                .into(binding.ivPlayer)
        }

    }


}