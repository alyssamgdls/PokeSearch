package com.magdales.pokemonsearch

/**
 * Created by Lai on 3/16/2018.
 */
data class Pokemon(val name: String,
                   val sprites: Sprite,
                   val weight: Int,
                   val height: Int,
                   val speed: Int,
                   val base_experience: Int,
                   val ability: Ability,
                   val stat: Stats,
                   val moves: Moves
)

data class Sprite(val front_default: String)

data class Stats(var stats: String, var base_stat: String)

data class Ability(val abilities: String)

data class Moves(val move: String)