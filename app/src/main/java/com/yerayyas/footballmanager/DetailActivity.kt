package com.yerayyas.footballmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yerayyas.footballmanager.databinding.ActivityDetailBinding
import java.util.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val PLAYER_NAME = "DetailActivity:name"
        const val PLAYER_LAST_NAME = "DetailActivity:lastname"
        const val PLAYER_POSITION = "DetailActivity:position"
        const val PLAYER_NUMBER = "DetailActivity:number"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerName = intent.getStringExtra(PLAYER_NAME)
        val lastName = intent.getStringExtra(PLAYER_LAST_NAME)
        val fullName = "$playerName $lastName"
        val position = intent.getStringExtra(PLAYER_POSITION)
        val number = intent.extras?.getInt(PLAYER_NUMBER)


        with(binding) {

            tvPlayerFullName.text = fullName
            playerPosition.text = position.toString().replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()//capitalized
            }
            playerNumber.text = number.toString()
        }


    }
}