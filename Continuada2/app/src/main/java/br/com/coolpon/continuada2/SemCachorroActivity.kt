package br.com.coolpon.continuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SemCachorroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sem_cachorro)
        val tvDeuRuim:TextView = findViewById(R.id.textView)
        var tipo1 = intent.getStringExtra("c1")
        var tipo2 = intent.getStringExtra("c2")

        tvDeuRuim.text = getString(R.string.deu_ruim,tipo1,tipo2)
    }
}