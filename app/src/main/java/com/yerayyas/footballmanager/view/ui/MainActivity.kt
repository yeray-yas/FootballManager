package com.yerayyas.footballmanager.view.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.yerayyas.footballmanager.adapter.PlayerAdapter
import com.yerayyas.footballmanager.databinding.ActivityMainBinding
import com.yerayyas.footballmanager.model.Model
import com.yerayyas.footballmanager.network.PlayerClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        getData()

        val playerAdapter = PlayerAdapter(emptyList()){ player ->
            Toast.makeText(this@MainActivity, player.name, Toast.LENGTH_SHORT).show()
        }

        binding.rvTeams.adapter = playerAdapter

        thread{
            val popularPlayers = PlayerClient.apiInterface.listPlayers()
            val body = popularPlayers.execute().body()

            runOnUiThread {
                if (body != null)
                    playerAdapter.players = body.team.players
                playerAdapter.run { this.notifyDataSetChanged() }

            }
        }

    }

    private fun getData() {
        //Progress bar
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please, wait while data is fetch")
        progressDialog.show()

        PlayerClient.apiInterface.listPlayers().enqueue(object : Callback<Model?> {
            override fun onResponse(call: Call<Model?>, response: Response<Model?>) {
                if (response.isSuccessful) {
                    Log.d("response", "Getting response from server: " + response)
                    with(binding) {
                        tvTitle.text = response.body()?.team?.name.toString()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() } //capitalized
                        tvName.text = response.body()?.team?.name.toString()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                        tvCountry.text = response.body()?.team?.country.toString()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                        tvNumberOfPlayers.text = response.body()?.team?.players?.size.toString()
                    }


                    Glide.with(this@MainActivity).load(response.body()?.team?.icon)
                        .into(binding.ivIcon)

                    //Here we calculate the age average of players

                    val dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy")

                    var totalYears = 0
                    lateinit var now: LocalDate
                    lateinit var period: Period
                    val totalPlayers = response.body()?.team?.players?.size!!

                    for (i in 0 until response.body()?.team?.players?.size!!) {
                        val birthDay = LocalDate.parse(
                            response.body()?.team?.players?.get(i)?.birthday.toString(),
                            dtf
                        )
                        now = LocalDate.now()
                        period = Period.between(birthDay, now)
                        totalYears += period.years
                    }

                    //we put the average age into its place
                    "${((totalYears) / totalPlayers)} YEARS".also { binding.tvAverageAge.text = it }

                    //we quit the progressbar
                    progressDialog.dismiss()


                    Toast.makeText(this@MainActivity, "Success Response", Toast.LENGTH_LONG).show()


                }
            }

            override fun onFailure(call: Call<Model?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                Log.d("response", "Getting response from server: " + t)
                progressDialog.dismiss()

            }
        })
    }
}
