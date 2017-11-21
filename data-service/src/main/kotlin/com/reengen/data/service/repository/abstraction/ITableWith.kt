package com.reengen.data.service.repository.abstraction

interface ITableWith {

    var _qBuilder:StringBuilder

    fun AND()
}