package com.example.dawid.soundmeter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var context: Context

    lateinit var simpleText: TextView
    lateinit var logoutButton: Button
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).applicationComponent.inject(this)

        firebaseAuth = FirebaseAuth.getInstance()


        simpleText = findViewById(R.id.simple_text)
        simpleText.text = firebaseAuth.currentUser?.uid

        logoutButton = findViewById(R.id.button_logout)
        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}
