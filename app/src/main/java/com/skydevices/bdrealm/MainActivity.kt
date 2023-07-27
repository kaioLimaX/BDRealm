package com.skydevices.bdrealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.skydevices.bdrealm.database.DatabaseRealm
import com.skydevices.bdrealm.databinding.ActivityMainBinding
import com.skydevices.bdrealm.model.Usuario
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.ObjectId

/*
  antes de iniciar , inserir
  no buildGradle do project a linha:
  id ("io.realm.kotlin") version "1.10.0" apply false
  configurar o viewbind
  */

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val realm by lazy {
        DatabaseRealm()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }
            btnAtualizar.setOnClickListener {
                atualizar()
            }
            btnRemover.setOnClickListener {
                remover()
            }
        }
    }

    private fun remover() {
        val _id : ObjectId = ObjectId.Companion.from("64c1e23b99c8be4763274935")
        realm.remover(_id)
    }

    private fun atualizar() {
        val nomeRecuperado = binding.edtNome.text.toString()
        val _idSelecionado : ObjectId = ObjectId.Companion.from("64c1e22e99c8be4763274931")

        val usuario = Usuario().apply {
            _id = _idSelecionado
            nome = nomeRecuperado
            idade = 40
        }
        realm.atualizar(usuario)
    }

    private fun listar() {
        //Log.i("info_real", "botao clicado ")
        val lista = realm.listar()
        var textoLista = ""
        lista.forEach {usuario ->

            textoLista += "${usuario.nome} - idade ${usuario.idade} \n"
            Log.i("info_realm", " id : ${usuario._id} - ${usuario.nome} - idade ${usuario.idade}")

        }
        binding.txtResultado.text = textoLista

    }

    private fun salvar() {
        val nomeRecuperado = binding.edtNome.text.toString()

        val usuario = Usuario().apply {
          nome = nomeRecuperado
          idade = 10
        }
        realm.salvar(usuario)
    }
}