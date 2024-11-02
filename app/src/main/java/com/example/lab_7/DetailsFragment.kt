package com.example.lab_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
    
        arguments?.let { args ->
            view.findViewById<TextView>(R.id.nameTextView).text = args.getString("name")
            view.findViewById<TextView>(R.id.heightTextView).text = args.getString("height")
            view.findViewById<TextView>(R.id.massTextView)?.text = "Mass: ${args.getString("mass") ?: "Unknown"}"
            view.findViewById<TextView>(R.id.hairColorTextView)?.text = "Hair Color: ${args.getString("hair_color") ?: "Unknown"}"
            view.findViewById<TextView>(R.id.skinColorTextView)?.text = "Skin Color: ${args.getString("skin_color") ?: "Unknown"}"
            view.findViewById<TextView>(R.id.eyeColorTextView)?.text = "Eye Color: ${args.getString("eye_color") ?: "Unknown"}"

            view.findViewById<TextView>(R.id.genderTextView)?.text = "Gender: ${args.getString("gender") ?: "Unknown"}"
        }
    
        return view
    }
}