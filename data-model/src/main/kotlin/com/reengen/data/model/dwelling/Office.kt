package com.reengen.data.model.dwelling

import com.reengen.abstraction.model.dwelling.IOffice
import com.reengen.data.model.client.Employeee
import org.intellij.lang.annotations.Identifier
import org.jetbrains.annotations.NotNull
import javax.persistence.*


@Entity
@Table(name = "Offices")
data class Office(
                  @Column(nullable = true)
                  override var Adress: String,
                  @Column(name = "Name", unique = true)
                  @get:NotNull
                  override var Name: String,
                  @Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  override var Id: Int) : IOffice{
    constructor():this("","",0)
}