package com.reengen.data.service.repository.serviceobject

import com.reengen.data.service.repository.abstraction.IConfig
import com.reengen.data.service.repository.abstraction.IRepository
import com.reengen.data.service.repository.abstraction.IService
import com.reengen.data.service.repository.abstraction.IServicePostgres

class PostgreService: IServicePostgres {
    override fun configure(keyspace: String):IService {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initialize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRepo(): IRepository {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configure(config: IConfig) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}