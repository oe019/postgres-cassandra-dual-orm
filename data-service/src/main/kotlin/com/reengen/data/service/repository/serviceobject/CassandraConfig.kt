package com.reengen.data.service.repository.serviceobject

import com.reengen.data.service.repository.abstraction.IConfigCassandra

class CassandraConfig(override var server: String, override var port: Int, override var schema: String) :IConfigCassandra {

}