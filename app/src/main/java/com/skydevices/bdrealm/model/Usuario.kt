package com.skydevices.bdrealm.model

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Usuario : RealmObject{

    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var nome = ""
    var idade = 0
}