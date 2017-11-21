package com.reengen.abstraction.global

/**
 * Created by erguzelolgun on 07/07/2017.
 * Represents a database object of table, immutable properties
 */
interface ITable : IbeNamable {
    val Schema: String
    val ColumnNames: Collection<String>
}