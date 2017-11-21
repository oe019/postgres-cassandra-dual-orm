package com.reengen.data.service.repository.abstraction

  interface IService {
    fun initialize()
    fun getRepo(): Any
    fun configure(keyspace:String) :IService
    fun configure(config:IConfig)

}