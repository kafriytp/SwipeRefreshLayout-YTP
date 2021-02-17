package com.eunidev.pulltorefreshytp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        recyclerView = findViewById(R.id.recyclerView)

        val list = ArrayList<ContentModel>()
        list.add(ContentModel(Random.nextInt(), "Title1", "Description1"))
        list.add(ContentModel(Random.nextInt(), "Title2", "Description2"))
        list.add(ContentModel(Random.nextInt(), "Title3", "Description3"))
        list.add(ContentModel(Random.nextInt(), "Title4", "Description4"))
        list.add(ContentModel(Random.nextInt(), "Title5", "Description5"))
        list.add(ContentModel(Random.nextInt(), "Title6", "Description6"))
        list.add(ContentModel(Random.nextInt(), "Title7", "Description7"))
        adapter = RVAdapter(this, list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }
        })

        // Pull To Refresh
        swipeRefreshLayout.setOnRefreshListener {
            // Refreshing
            refresh()
            swipeRefreshLayout.isRefreshing = false
        }

    }

    fun refresh() {
        Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0,0)
        finish()
        overridePendingTransition(0,0)
    }
}