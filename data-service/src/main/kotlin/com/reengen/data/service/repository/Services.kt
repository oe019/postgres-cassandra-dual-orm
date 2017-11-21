package com.reengen.data.service.repository

import com.reengen.data.service.repository.abstraction.*
import com.reengen.data.service.repository.enum.ServiceType
import com.reengen.data.service.repository.util._accessService

fun main(args: Array<String>){
    println("Hello test")

    val casserv: IServiceCassandra = _accessService(ServiceType.Cassandra).configure("test") as IServiceCassandra
    val rep:IRepoCassandra =casserv.getRepo() as IRepoCassandra
    val sb:String = rep.createCQL().SELECT("tenant").FROM("test.data").toString()

    println(sb)
}

class Services : IService{
    override fun initialize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRepo(): IRepository {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configure(schema:String) :IService {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun configure(config: IConfig) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}