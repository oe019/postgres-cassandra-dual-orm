package com.reengen.data.model.client

import com.reengen.abstraction.model.client.IEmployee
import com.reengen.data.model.dwelling.Office
import com.sun.corba.se.spi.oa.ObjectAdapterFactory
import org.jetbrains.annotations.NotNull
import javax.persistence.*



@Entity
@Table(name="Employees")
data class Employeee(
        @Column(name = "Name", unique = true)
        @get:NotNull
        override var Name: String,
        @Id
       // @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var Id: Int) :IEmployee{
    constructor():this("",0)
}




//
//@Column(name = "Name", unique = true)
//@get:NotNull override var Name: String,
//@Column
//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//override var Id: Int) : Initializer(), IEmployee