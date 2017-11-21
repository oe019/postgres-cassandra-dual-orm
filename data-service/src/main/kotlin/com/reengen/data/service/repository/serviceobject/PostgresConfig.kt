package com.reengen.data.service.repository.serviceobject

import com.reengen.data.service.repository.abstraction.IConfigPostgres

class PostgresConfig : IConfigPostgres{
    override var server: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var port: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
    override var schema: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
}