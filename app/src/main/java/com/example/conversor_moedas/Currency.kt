package com.example.conversor_moedas

import androidx.annotation.DrawableRes

data class Currency(
    val currency: String,
    val value: String,
    @DrawableRes val icon: Int

)
