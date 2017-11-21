package com.reengen.data.service.repository.abstraction

import com.reengen.data.service.repository.enum.CQLDataTypes
import com.reengen.data.service.repository.enum.TableProperyTypes

interface ICreatable {

    fun WITH(property:TableProperyTypes, tablePropertyObject: Any) :ITableWith
}