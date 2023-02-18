package com.yerayyas.footballmanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yerayyas.footballmanager.databinding.ActivityDetailBinding
import com.yerayyas.footballmanager.model.Model
import com.yerayyas.footballmanager.model.Player
import com.yerayyas.footballmanager.network.PlayerClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLAYER = "DetailActivity:player"

    }

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillPlayersDetails()


    }

    private fun fillPlayersDetails() {
        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        if (player != null) {

            val playerName = player.name
            val playerLastname = player.lastname
            val fullName = "$playerName $playerLastname"
            val dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy")
            val ptf = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)
            val now = LocalDate.now()
            val theBirthDay = LocalDate.parse(
                player.birthday, dtf
            )

            val period = Period.between(theBirthDay, now)

            val actualAge = "${period.years} YEARS"

            title = fullName



            Glide.with(this).load(player.photo).into(binding.ivPlayer)
            insertIcon()

            with(binding) {

                tvPlayerFullName.text = fullName
                playerPosition.text = player.position.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()//capitalized
                }
                playerNumber.text = player.number.toString()
                "${player.height} CM".also { heightPlayer.text = it }
                "${player.weight} KG".also { weightPlayer.text = it }

                if (player.rightFeet) {
                    "RIGHT".also { feetPlayer.text = it }
                } else "LEFT".also { feetPlayer.text = it }

                birthdayPlayer.text = theBirthDay.format(ptf).uppercase()
                agePlayer.text = actualAge

            }
        }
    }

    private fun insertIcon() {
        PlayerClient.apiInterface.listPlayers().enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                if (response.isSuccessful) {
                    Glide.with(this@DetailActivity).load(response.body()?.team?.icon)
                        .into(binding.ivTeamIcon)
                }
            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                Toast.makeText(this@DetailActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })
    }


}