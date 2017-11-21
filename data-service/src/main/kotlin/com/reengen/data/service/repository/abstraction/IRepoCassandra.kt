package com.reengen.data.service.repository.abstraction

import com.reengen.data.service.repository.serviceobject.QueryBuilder
import org.springframework.data.cassandra.core.CassandraTemplate

interface IRepoCassandra : IRepository{

    fun createCQL():QueryBuilder

    var CassandraTemplete: CassandraTemplate

}