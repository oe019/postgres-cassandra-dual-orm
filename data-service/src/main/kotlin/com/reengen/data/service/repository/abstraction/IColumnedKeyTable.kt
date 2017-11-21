package com.reengen.data.service.repository.abstraction

import com.reengen.data.service.repository.enum.TableProperyTypes

interface IColumnedKeyTable {

    var _qBuilder : StringBuilder

    fun WITH(property: TableProperyTypes, tablePropertyObject: Any) :ITableWith
}