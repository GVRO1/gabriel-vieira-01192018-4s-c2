package br.com.coolpon.continuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ComCachorroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_com_cachorro)
        val tvCachorro1:TextView = findViewById(R.id.tv_cachorro1)
        val tvCachorro2:TextView = findViewById(R.id.tv_cachorro2)
        val tvTotal:TextView = findViewById(R.id.total)
        var c1:Cachorro? = intent.getParcelableExtra<Cachorro>("c1")
        var c2:Cachorro? = intent.getParcelableExtra<Cachorro>("c2")
        var valorC1:Int = 0
        var valorC2:Int = 0
        if (c1 ==null){
            tvCachorro1.text = getString(R.string.cachorroNaoEncontrado)
        }else{
            valorC1 = c1.precoMedio
            tvCachorro1.text = getString(R.string.cachorro1,c1.raca)
        }
        if (c2 ==null){
            tvCachorro2.text = getString(R.string.cachorroNaoEncontrado)
        }else{
            valorC2 = c2.precoMedio
            tvCachorro2.text = getString(R.string.cachorro2,c2.raca)
        }
        tvTotal.text = getString(R.string.total,valorC1+valorC2)

    }
}