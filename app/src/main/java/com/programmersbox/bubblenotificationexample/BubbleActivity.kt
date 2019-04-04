package com.programmersbox.bubblenotificationexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bubble.*
import org.jetbrains.anko.defaultSharedPreferences

class BubbleActivity : AppCompatActivity() {
    private var count = 0
        get() {
            return defaultSharedPreferences.getInt("counter", 0)
        }
        set(value) {
            field = value
            defaultSharedPreferences.edit().putInt("counter", field).apply()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble)
        button2.setOnClickListener {
            textView.text = "${count++}"
        }
        textView.text = "$count"
    }
}
