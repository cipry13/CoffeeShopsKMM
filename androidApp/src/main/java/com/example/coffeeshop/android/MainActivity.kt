package com.example.coffeeshop.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.ApiClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coffeeShopsRv = findViewById<RecyclerView>(R.id.coffee_shops_rv)

        ApiClass().getData {
            val coffeeShopRvAdapter = CoffeeShopsAdapter(it)
            coffeeShopsRv.layoutManager = LinearLayoutManager(this)
            coffeeShopsRv.adapter = coffeeShopRvAdapter
        }
    }
}
