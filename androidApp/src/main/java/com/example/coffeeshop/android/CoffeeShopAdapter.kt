package com.example.coffeeshop.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.CoffeeShop

class CoffeeShopsAdapter(private val coffeeShops: List<CoffeeShop>) :
    RecyclerView.Adapter<CoffeeShopsAdapter.CoffeeShopViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoffeeShopsAdapter.CoffeeShopViewHolder {
        return CoffeeShopViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.coffee_shop_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoffeeShopsAdapter.CoffeeShopViewHolder, position: Int) {
        holder.setCoffeeShopItemText(coffeeShops[position])
    }

    override fun getItemCount(): Int = coffeeShops.count()

    inner class CoffeeShopViewHolder(coffeeShopItemView: View) :
        RecyclerView.ViewHolder(coffeeShopItemView) {
        private val coffeeShopTextView = itemView.findViewById<TextView>(R.id.coffee_shop_text_view)

        fun setCoffeeShopItemText(coffeeShopItem: CoffeeShop) {
            coffeeShopTextView.text = coffeeShopItem.pretify()
        }
    }
}

fun CoffeeShop.pretify() = "${this.id} ${this.name} \n${this.x} ${this.y} \n"