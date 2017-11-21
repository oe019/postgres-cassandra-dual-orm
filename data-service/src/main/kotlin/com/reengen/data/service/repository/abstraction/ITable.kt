package com.reengen.data.service.repository.abstraction

import com.reengen.data.service.repository.enum.CQLDataTypes

interface ITable  {

    fun Column(name:String, type: CQLDataTypes): IColumnedTable

    var _qBuilder:StringBuilder
}