package com.example.coffeeshop.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.ApiClass
import com.example.coffeeshop.Platform
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Coffee Shops"

        val coffeeShopRv: RecyclerView = findViewById(R.id.coffee_shops_rv)
        coffeeShopRv.layoutManager = LinearLayoutManager(this)

        mainScope.launch {
            kotlin.runCatching {
                ApiClass().getData()
            }.onSuccess {
                coffeeShopRv.adapter = CoffeeShopsAdapter(it)
            }.onFailure {
                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
