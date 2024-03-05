package com.kogelmogel123.budgetbuddy

import kotlin.random.Random

class Repository {
    fun fetchData(): Int {
        return Random.nextInt(0,100)
    }
}