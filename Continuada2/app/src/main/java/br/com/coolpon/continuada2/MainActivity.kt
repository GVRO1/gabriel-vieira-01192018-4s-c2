package br.com.coolpon.continuada2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        A função recuperar serve para caso o aplicativo feche sozinho o shared file guarda sempre a ultima pesquisa do usuario
        recuperar()
    }

    private fun recuperar() {
        val settings = getSharedPreferences("SharedFile", 0)
        val c1 = settings.getString("c1", "")
        val c2 = settings.getString("c2", "")
        val switchShared = settings.getBoolean("switch", false)
        val tipo1: EditText = findViewById(R.id.et_tipo1)
        val tipo2: EditText = findViewById(R.id.et_tipo2)
        val switch: Switch = findViewById(R.id.switch1)

        if (c1 != "") {
            tipo1.text.insert(tipo1.selectionStart,"c1")
        }
        if (c2 != "") {
            tipo2.text.insert(tipo2.selectionStart,"c2")
        }
        if(switchShared){
            switch.isChecked = true
        }
    }

    fun comprar(view: View) {
        val tipoEd2: EditText = findViewById(R.id.et_tipo2)
        val tipoEd1: EditText = findViewById(R.id.et_tipo1)
        val switch1: Switch = findViewById(R.id.switch1)
        val settings = getSharedPreferences("SharedFile", 0)
        val editor = settings.edit()
        editor.putString("c1", tipoEd1.text.toString())
        editor.putString("c2", tipoEd2.text.toString())
        editor.putBoolean("switch", switch1.isChecked)
        // Commit as edições
        editor.commit()
        val tipo1: EditText = findViewById(R.id.et_tipo1)
        val tipo2: EditText = findViewById(R.id.et_tipo2)
        var cachorro1: Cachorro? = null
        var cachorro2: Cachorro? = null
        var switch: Switch = findViewById(R.id.switch1)
        HttpRequest.criar().cachorroId(tipo1.text.toString()).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 200) {
                    if (switch.isChecked) {
                        if (response.body()!!.indicadoCriancas) {
                            cachorro1 = response.body()!!
                        } else {
                            cachorro1 = null
                        }
                    }else{
                        cachorro1 = response.body()!!
                    }
                } else {
                    cachorro1 = null
                }

                HttpRequest.criar().cachorroId(tipo2.text.toString())
                    .enqueue(object : Callback<Cachorro> {
                        override fun onResponse(
                            call: Call<Cachorro>,
                            response: Response<Cachorro>
                        ) {

                            if (response.code() == 200) {
                                if (switch.isChecked) {
                                    if (response.body()!!.indicadoCriancas) {
                                        cachorro2 = response.body()!!
                                    } else {
                                        cachorro2 = null
                                    }
                                }else{
                                    cachorro2 = response.body()!!
                                }
                            } else {
                                cachorro2 = null
                            }
                            println(cachorro1)
                            println(cachorro2)
                            if (cachorro1 == null && cachorro2 == null) {
                                val intent: Intent =
                                    Intent(baseContext, SemCachorroActivity::class.java)
                                intent.putExtra("c1", tipo1.text.toString())
                                intent.putExtra("c2", tipo2.text.toString())
                                startActivity(intent)
                            } else {
                                val intent: Intent =
                                    Intent(baseContext, ComCachorroActivity::class.java)
                                intent.putExtra("c1", cachorro1)
                                intent.putExtra("c2", cachorro2)
                                startActivity(intent)
                            }
                        }

                        override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                            Toast.makeText(
                                baseContext,
                                t.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    })
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(
                    baseContext,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }


}