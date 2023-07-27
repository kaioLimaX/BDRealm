package com.skydevices.bdrealm.database

import android.util.Log
import com.skydevices.bdrealm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.ObjectId

class DatabaseRealm {

    val config = RealmConfiguration.create(schema = setOf(Usuario::class))
    val realm: Realm = Realm.open(config)

    fun salvar(usuario: Usuario){
        Log.i("info_realm", "salvar:d ")

        realm.writeBlocking {
            copyToRealm(usuario)
        }

    }

    fun listar() : RealmResults<Usuario>{
        //val listaItems: RealmResults<Usuario> = realm.query<Usuario>("nome = $0 ","jamilton",).find()
        return realm
            .query<Usuario>()
           // .sort("nome",Sort.ASCENDING)
            .find()


    }

    fun remover(_id :ObjectId){
        Log.i("info_realm", "salvar:d ")

        realm.writeBlocking {
            val usuarioRemover = query<Usuario>("_id == $0", _id)
                .find()
                .first()

            delete(usuarioRemover)

        }

    }

    fun atualizar(usuario: Usuario) {

        realm.writeBlocking {
            val usuarioAtualizar = query<Usuario>("_id == $0", usuario._id)
                .find()
                .first()

            usuarioAtualizar.nome = usuario.nome
            usuarioAtualizar.idade= usuario.idade

        }

    }
}