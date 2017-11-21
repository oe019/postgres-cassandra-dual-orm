package com.reengen.data.service.repository.abstraction

import org.springframework.beans.factory.support.ManagedList

interface IRepository{
    fun <T> getAll() : MutableList<T>
    fun<T> getById(id:Int):T
    fun<T> deleteById(id:Int)
}