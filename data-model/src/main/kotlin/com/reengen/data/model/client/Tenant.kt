package com.reengen.data.model.client

import com.reengen.abstraction.model.client.ITenant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.GenerationType.AUTO
import javax.persistence.Id

/**
 * Created by erguzelolgun on 07/07/2017.
 */
@Entity
class Tenant (name_:String, brand_:String, Id_:Int, override var Name: String, override var Brand: String, override var Id: Int):  ITenant {

}