package com.reengen.data.service.repository.abstraction

interface ISelected  {

    fun FROM(column : String) : ISelectedFrom
}