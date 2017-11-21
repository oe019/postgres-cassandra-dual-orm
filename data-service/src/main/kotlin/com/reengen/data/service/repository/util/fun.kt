package com.reengen.data.service.repository.util
import com.reengen.data.service.repository.enum.ServiceType

import com.reengen.data.service.repository.abstraction.IConfig
import com.reengen.data.service.repository.abstraction.IService
import com.reengen.data.service.repository.serviceobject.CassandraConfig
import com.reengen.data.service.repository.serviceobject.CassandraService
import com.reengen.data.service.repository.serviceobject.PostgreService
import com.reengen.data.service.repository.serviceobject.PostgresConfig
import java.io.InvalidObjectException

fun _accessService(serviceType: ServiceType): IService{
    when(serviceType){

        ServiceType.Cassandra -> return CassandraService()
        ServiceType.Postgres -> return PostgreService()
        else -> throw InvalidObjectException("no such service")
    }
}

/*
fun _createConfig(serviceType: ServiceType): IConfig{
    when(serviceType){
        ServiceType.Cassandra -> return CassandraConfig()
        ServiceType.Postgres -> return PostgresConfig()
        else -> throw InvalidObjectException("no such service")
    }

}
 */