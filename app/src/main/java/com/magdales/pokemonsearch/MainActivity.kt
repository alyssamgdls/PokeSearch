package com.magdales.pokemonsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var pokemonName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_abilities.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        list_stats.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        button_search.setOnClickListener({
            val pokemonName = editText_pokemon.text.toString()
            progressBar.visibility = View.VISIBLE

            val url = "https://pokeapi.co/api/v2/pokemon/" + pokemonName
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient.Builder().build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Toast.makeText(this@MainActivity, "Request failed", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }
                override fun onResponse(call: Call?, response: Response?) {
                    if (response != null && response.isSuccessful) {
                        val jsonObj = response.body()?.string()
                        getPokemon(jsonObj)
                    }
                }
            })
        })
    }

    private fun getPokemon(jsonObj: String?) {
        runOnUiThread {
            val gsonObj = GsonBuilder().create()
            val pokemon = gsonObj.fromJson(jsonObj, Pokemon::class.java)

            if (pokemonName == pokemon.name) {
                pokeName.text = pokemon.name
                pokeName.text = pokemon.name.toUpperCase()
                Picasso.with(this@MainActivity).load(pokemon.sprites.front_default).into(imageView_pokemon)
                textView_weight.text = pokemon.weight.toString()
                textView_height.text = pokemon.height.toString()
                textView_baseExperience.text = pokemon.base_experience.toString()
                getAbility(jsonObj)
                getStats(jsonObj)
                getMoves(jsonObj)
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Pokemon found", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pokemon not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getAbility(jsonObj: String?) {
        val length = JSONObject(jsonObj).getJSONArray("abilities").length()
        val abilitylist = ArrayList<Ability>()
        var pos = 0
        for (i in 1..length) {
            val pokemonAbility = JSONObject(jsonObj).getJSONArray("abilities").getJSONObject(pos).getJSONObject("ability").getString("name")
            abilitylist.add(Ability(pokemonAbility))
            val adapter = AbilitiesAdapter(abilitylist)
            list_abilities.adapter = adapter
            pos++
        }
    }

    private fun getStats(jsonObj: String?) {
        val length = JSONObject(jsonObj).getJSONArray("stats").length()
        val statslist = ArrayList<Stats>()
        var pos = 0
        for (i in 1..length) {
            val pokemonStats = JSONObject(jsonObj).getJSONArray("stats").getJSONObject(pos).getJSONObject("stat").getString("name")
            val basemonStats = JSONObject(jsonObj).getJSONArray("stats").getJSONObject(pos).getString("base_stat").toString()
            statslist.add(Stats(pokemonStats,basemonStats))
            val adapter = StatsAdapter(statslist)
            list_stats.adapter = adapter
            pos++
        }
    }

    private fun getMoves(jsonObj: String?) {
        val length = JSONObject(jsonObj).getJSONArray("moves").length()
        val moveslist = ArrayList<Moves>()
        var pos = 0
        for (i in 1..length) {
            var pokemonMoves = JSONObject(jsonObj).getJSONArray("moves").getJSONObject(pos).getJSONObject("move").getString("name")
            moveslist.add(Moves(pokemonMoves))
            val adapter = MovesAdapter(moveslist)
            list_types.adapter = adapter
            pos++
        }
    }
}
