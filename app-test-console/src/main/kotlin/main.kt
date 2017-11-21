import com.datastax.driver.core.*
import org.springframework.data.cassandra.core.CassandraTemplate
import com.datastax.driver.core.querybuilder.Insert
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.schemabuilder.TableOptions
import com.datastax.driver.core.schemabuilder.TableOptions.CompactionOptions.SizeTieredCompactionStrategyOptions
import com.reengen.data.service.repository.abstraction.*
import com.reengen.data.service.repository.abstraction.IServiceCassandra
import com.reengen.data.service.repository.enum.*
import com.reengen.data.service.repository.serviceobject.CassandraConfig
import com.reengen.data.service.repository.serviceobject.tableproperty.TableProperties
import com.reengen.data.service.repository.serviceobject.tableproperty.TableSubProperties.*
import com.reengen.data.service.repository.util._accessService
import kotlin.*
import org.hibernate.tool.hbm2ddl.SchemaExport.execute
import org.springframework.cassandra.core.PreparedStatementCreator
import java.util.*
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


// test methods startsgit
fun testInserter(){


    val casserv: IServiceCassandra = _accessService(ServiceType.Cassandra).configure("test") as IServiceCassandra
    val rep: IRepoCassandra =casserv.getRepo() as IRepoCassandra
    val sb:String = rep.createCQL().SELECT("tenant").FROM("test.data").toString()
    val tb: String =  (rep.createCQL().CREATE_TABLE("testTable1"))
                                    .Column("BigIntcol", CQLDataTypes.bigint)
                                     .Column("StringColumn",CQLDataTypes.text)
                                        .Column("inetColumn",CQLDataTypes.inet)
            .SetPrimaryKey("BigIntcol","asd").toString()

    rep.CassandraTemplete.execute(tb.toString())

}

fun test2(){
    val casServ: IServiceCassandra = _accessService(ServiceType.Cassandra).configure("test") as IServiceCassandra
    val casRep:IRepoCassandra = casServ.getRepo() as IRepoCassandra
    val query = casRep.createCQL().CREATE_TABLE("test.denn4")
            .Column("col1",CQLDataTypes.text)
            .Column("col2",CQLDataTypes.float)
            .Column("col3",CQLDataTypes.decimal)
            .Column("col4",CQLDataTypes.inet)
            .SetPrimaryKey("col1,col2","col3,col4")
            ._qBuilder.toString()

    casRep.CassandraTemplete.execute(query)
}

fun test3(){
   var clstr:Cluster= Cluster.builder().addContactPoint("localhost")
            .withTimestampGenerator(TimestampGenerator { System.currentTimeMillis()/1000L })
            .withPort(9042)
            .build()
    val sess :Session =  clstr.connect("test")
    val temp:CassandraTemplate = CassandraTemplate(sess)




}

fun test5(){
    val _keyValues:StringBuilder = StringBuilder()

    val strategy: SizeTieredCompactionStrategy = SizeTieredCompactionStrategy()

    val clsName :String = SizeTieredCompactionStrategy::class.simpleName.toString()
    
}


// test methods ends

fun main(args: Array<String>) {
    //var strategy: TableSubProperties.SizeTieredCompactionStrategy = TableSubProperties.SizeTieredCompactionStrategy()
    //val props = TableSubProperties.SizeTieredCompactionStrategy::class.memberProperties

    //for(prop in props){
      //  prop.isAccessible = true
        //prop.get(strategy)

   // }

    //testInserter()
 //   test5()
    test2()
}

fun test4(){



    val casserv: IServiceCassandra = _accessService(ServiceType.Cassandra).configure("test") as IServiceCassandra
    val casrepo:IRepoCassandra = casserv.getRepo() as IRepoCassandra
    val query: String = casrepo.createCQL()
           .CREATE_TABLE("test.fluentTable")
           .Column("col1",CQLDataTypes.bigint)
           .Column("col2",CQLDataTypes.decimal)
           .Column("col3",CQLDataTypes.text)
           .Column("col4",CQLDataTypes.float)
           .SetPrimaryKey("col1,col2","col3,col4")
           .WITH(
                   TableProperyTypes.compaction,
                   TableProperties.Compaction(SizeTieredCompactionStrategy())
           )
           ._qBuilder
           .toString()
    casrepo.CassandraTemplete.execute(query)
}
