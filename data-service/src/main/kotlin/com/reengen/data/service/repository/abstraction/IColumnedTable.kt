package com.reengen.data.service.repository.abstraction

import com.reengen.data.service.repository.enum.CQLDataTypes

interface IColumnedTable {

    fun SetPrimaryKey(partitioningKeys: String = "", clusteringKeys:String):IColumnedKeyTable
    fun SetCompositeKey():IColumnedCompositeKeyTable
    fun Column(name:String, type: CQLDataTypes): IColumnedTable
}