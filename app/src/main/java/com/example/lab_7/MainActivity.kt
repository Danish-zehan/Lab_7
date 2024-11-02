package com.example.lab_7

import android.os.Bundle
import android.os.AsyncTask
import android.widget.ListView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val charactersList = mutableListOf<JSONObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        FetchStarWarsDataTask().execute()

        listView.setOnItemClickListener { _, _, position, _ ->
            val character = charactersList[position]
            val isTablet = findViewById<android.widget.FrameLayout>(R.id.detailContainer) != null

            if (isTablet) {
                // Show details in fragment for tablet
                val fragment = DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putString("name", character.getString("name"))
                        putString("height", character.getString("height"))
                        putString("mass", character.getString("mass"))
                        putString("hair_color", character.getString("hair_color"))
                        putString("skin_color", character.getString("skin_color"))
                        putString("eye_color", character.getString("eye_color"))
                        putString("birth_year", character.getString("birth_year"))
                        putString("gender", character.getString("gender"))
                    }
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, fragment)
                    .commit()
            } else {
                // Start EmptyActivity for phone
                val intent = android.content.Intent(this, EmptyActivity::class.java).apply {
                    putExtra("name", character.getString("name"))
                    putExtra("height", character.getString("height"))
                    putExtra("mass", character.getString("mass"))
                    putExtra("hair_color", character.getString("hair_color"))
                    putExtra("skin_color", character.getString("skin_color"))
                    putExtra("eye_color", character.getString("eye_color"))
                    putExtra("birth_year", character.getString("birth_year"))
                    putExtra("gender", character.getString("gender"))
                }
                startActivity(intent)
            }
        }
    }

    private inner class FetchStarWarsDataTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void): String {
            return URL("https://swapi.dev/api/people/?format=json").readText()
        }

        override fun onPostExecute(result: String) {
            val jsonObject = JSONObject(result)
            val characters = jsonObject.getJSONArray("results")
            for (i in 0 until characters.length()) {
                val character = characters.getJSONObject(i)
                charactersList.add(character)
                adapter.add(character.getString("name"))
            }
            adapter.notifyDataSetChanged()
        }
    }
}