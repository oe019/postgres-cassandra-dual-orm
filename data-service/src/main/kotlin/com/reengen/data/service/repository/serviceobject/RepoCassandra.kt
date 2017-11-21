package com.reengen.data.service.repository.serviceobject

import com.reengen.data.service.repository.abstraction.*
import com.reengen.data.service.repository.enum.CQLDataTypes
import com.reengen.data.service.repository.enum.StrategyClassTypes
import com.reengen.data.service.repository.enum.TableProperyTypes
import org.apache.cassandra.locator.NetworkTopologyStrategy
import org.apache.cassandra.locator.SimpleStrategy
import org.springframework.data.cassandra.core.CassandraTemplate
import java.io.InvalidObjectException
import java.security.spec.KeySpec

class RepoCassandra :IRepoCassandra{

    private var initialQuery: QueryBuilder = QueryBuilder()

    override fun createCQL() : QueryBuilder {
        return QueryBuilder()
    }

    override fun <T> getAll(): MutableList<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> getById(id: Int): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> deleteById(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var CassandraTemplete: CassandraTemplate = CassandraTemplate()
}





/////////
class QueryBuilder {


     var _qbuilder: StringBuilder = StringBuilder()

    fun SELECT(columns: String):ISelected{

        val _selected:Selected = Selected()
        _qbuilder.append("SELECT")
        leaveSpace(_qbuilder)
        _qbuilder.append(columns)
        leaveSpace(_qbuilder)
        _selected._qbuilder = _qbuilder

        return _selected
    }

    fun CREATE_TABLE(name : String): ITable{

        val tbl: ITable = Table()

        tbl._qBuilder = _qbuilder
        tbl._qBuilder.append("CREATE TABLE ").append(name).append("(")

        return tbl

    }

    fun CREATE_KEYSPACE(name:String):IKeySpace{
        val tbl: IKeySpace = Keyspace()
        tbl._qBuilder = _qbuilder.append("CREATE KEYSPACE ").append(name)
        return tbl
    }

    private fun leaveSpace(qbuilder: StringBuilder): StringBuilder{

       return qbuilder.append(" ")
    }

    //

    private class Selected : ISelected{

        var _qbuilder:StringBuilder = StringBuilder()

        var _selectedFrom:SelectedFrom = SelectedFrom()


        override fun FROM(column: String): ISelectedFrom {

            _qbuilder.append("FROM")
            leaveSpace(_qbuilder)
            _qbuilder.append(column)
            _selectedFrom._qbuilder = _qbuilder

            return _selectedFrom

        }

        private fun leaveSpace(qbuilder: StringBuilder): StringBuilder{

            return qbuilder.append(" ")
        }

    }

    private class SelectedFrom : ISelectedFrom{

       var _qbuilder:StringBuilder = StringBuilder()

        override fun WHERE() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        private fun leaveSpace(qbuilder: StringBuilder): StringBuilder{

            return qbuilder.append(" ")
        }
    }

    private class Table: ITable{

        override var _qBuilder: StringBuilder = StringBuilder()

        override fun Column(name:String, type: CQLDataTypes):IColumnedTable{

            var colTable: ColumnedTable = ColumnedTable()

            this._qBuilder.append(name + " " + type.toString())

            colTable._qBuilder = this._qBuilder

            return  colTable
        }

    }

    private class ColumnedTable:IColumnedTable {
        override fun SetCompositeKey(): IColumnedCompositeKeyTable {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        var _qBuilder: StringBuilder = StringBuilder()

        private val primKey: String = "PRIMARY KEY ("


        override fun Column(name: String, type: CQLDataTypes): IColumnedTable {
            val columnedTable: ColumnedTable = ColumnedTable()

            this._qBuilder.append(",")
            this._qBuilder.append(name + " " + type.toString())
            columnedTable._qBuilder = this._qBuilder

            return  columnedTable
        }

        override fun SetPrimaryKey(partitioningKeys: String, clusteringKeys:String): IColumnedKeyTable {

            val colKeyTable : ColumnedKeyTable = ColumnedKeyTable()
            val isPartKeysVoid = partitioningKeys.isNullOrEmpty().equals(true)
            val isClustKeysVoid = clusteringKeys.isNullOrEmpty().equals(true)


            _qBuilder.append(",")

            if(!_qBuilder.contains("PRIMARY KEY")){
                _qBuilder.append(primKey)
            }

            if(isClustKeysVoid){
                throw InvalidObjectException(" at least one clustering key must be defined")
            }

            if(isPartKeysVoid){

                _qBuilder.append(clusteringKeys)
                colKeyTable._qBuilder = _qBuilder.append("))")
                return colKeyTable
            }

            if(partitioningKeys.split(",").count() > 1){
                _qBuilder.append("(").append(partitioningKeys).append(")")
                _qBuilder.append(",").append(clusteringKeys)
                _qBuilder.append(")")

            }
            else{
                _qBuilder.append(partitioningKeys)
                _qBuilder.append(",").append(clusteringKeys)
                _qBuilder.append(")")
            }

            colKeyTable._qBuilder = _qBuilder.append(")")

            return colKeyTable
        }
    }

    private class ColumnedKeyTable: IColumnedKeyTable{
        val _with :String = " WITH  "

        override var _qBuilder : StringBuilder = StringBuilder()


        override fun WITH(property: TableProperyTypes, tablePropertyObject: Any): ITableWith {
            var tableWith : TableWith = TableWith()
            _qBuilder.append(_with).append(property.toString())
            _qBuilder.append(" = ")
            _qBuilder.append(tablePropertyObject.toString())
            tableWith._qBuilder = _qBuilder
            return tableWith
        }


    }

    private class Keyspace:IKeySpace, IStrategy{


        val _withRpl: StringBuilder = StringBuilder().append(" WITH REPLICATION = { ")

        override var _qBuilder: StringBuilder = StringBuilder()

        override fun WITH_REPLICATION(clss: StrategyClassTypes, replicationFactor: Int):IStrategy {

            _withRpl.append("'class' :" + "'" + clss.toString()+"'").append(",").append("'replication_factor' :" + replicationFactor.toString()).append("} ")

            _qBuilder.append(_withRpl.toString())
            val kspc: SimpleStrategy = SimpleStrategy()
            kspc._qBuilder = _qBuilder

            val nts: NetworkStrategy = NetworkStrategy()
            nts._qBuilder = _qBuilder

            when(clss){
                StrategyClassTypes.SimpleStrategy -> return kspc
                StrategyClassTypes.NetworkTopologyStrategy -> return nts

            }

        }

    }//

    private class SimpleStrategy:ISimpleStrategy{


        override var _qBuilder: StringBuilder=StringBuilder()

    }

    private class NetworkStrategy:INetworkStrategy {
        val andDurableWrites: StringBuilder = StringBuilder().append(" AND DURABLE_WRITES = ")

        override var _qBuilder: StringBuilder = StringBuilder()

        override fun AND_DURABLE_WRITES(isDurableWrites: Boolean) :StringBuilder{
           andDurableWrites.append(isDurableWrites.toString()).append(";")
            _qBuilder.append(andDurableWrites.toString())
            return _qBuilder
        }

    }

    private class TableWith:ITableWith{
        override var _qBuilder: StringBuilder = StringBuilder()

        override fun AND() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}

