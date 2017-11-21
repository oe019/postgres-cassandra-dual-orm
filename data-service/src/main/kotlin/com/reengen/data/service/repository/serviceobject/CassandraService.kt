package com.reengen.data.service.repository.serviceobject

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session
import com.reengen.data.model.client.Employeee
import com.reengen.data.service.repository.abstraction.*
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.repository.CassandraRepository

class CassandraService : IServiceCassandra {


    private var casTemp : CassandraTemplate = CassandraTemplate()


    override fun initialize() {
    }

    override fun getRepo(): IRepoCassandra {
        val rep: RepoCassandra = RepoCassandra()
        rep.CassandraTemplete = this.casTemp
        return rep
    }

    override fun configure(keyspace:String) :IService{

        val cluster = Cluster.builder()
                .withPort(9042)
                .addContactPoint("127.0.0.1")
                .build()
        val session = cluster.connect(keyspace)

        this.casTemp = CassandraTemplate(session)

        return this

    }

    override fun configure(config: IConfig) {
        val cluster = Cluster.builder()
                .withPort(config.port)
                .addContactPoint(config.server)
                .build()
        val session = cluster.connect(config.schema)
         this.casTemp = CassandraTemplate(session)
    }
}