package com.example.conversor_moedas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class CurrencySpinnerAdapter(
    private val context: Context,
    private val currencies: List<Currency>
) : BaseAdapter(){

override fun getCount(): Int {
    return currency.size
}

override fun getItem(position: Int): Currency {
    return currency[position]
}

override fun getItemId(position: Int): Long {

    return position.toLong()

}

override fun getView(position: Int,convertView: View?,parent:ViewGroup?): View{
    val view = convertView ?: LayoutInflater.from(context ).inflate(R.layout.item_spinner,parent,false)

    bindView(view, currency[position])

    return view
}

private fun bindView(view: View,currency: Currency){
    val ivCurrency = view.findViewById<ImageView>(R.id.iv_currency_image)
    val tvCurrencyIso = view.findViewById<TextView>(R.id.tv_currency_iso)

    ivCurrency.setImageResource(currency.icon)
    tvCurrencyIso.text = currency.currency

}






}