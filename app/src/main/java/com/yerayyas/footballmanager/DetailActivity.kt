package com.yerayyas.footballmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.yerayyas.footballmanager.databinding.ActivityDetailBinding
import com.yerayyas.footballmanager.model.Player
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLAYER = "DetailActivity:player"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)


        if (player != null) {

            val playerName = player.name
            val playerLastname = player.lastname
            val fullName = "$playerName $playerLastname"


            Glide.with(this).load(player.photo).into(binding.ivPlayer)

            with(binding) {

                tvPlayerFullName.text = fullName
                playerPosition.text = player.position.toString().replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()//capitalized
                }
                playerNumber.text = player.number.toString()
                "${player.height} CM".also { heightPlayer.text = it }
                "${player.weight} KG".also { weightPlayer.text = it }

                if(player.rightFeet){
                    "RIGHT".also { feetPlayer.text = it }
                }else "LEFT".also { feetPlayer.text = it }



            }
        }


    }
}