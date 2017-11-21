package com.reengen.data.model.client

import com.reengen.abstraction.model.client.IUser

/**
 * Created by erguzelolgun on 07/07/2017.
 */
class User(override var eMail: String, override var Name: String, override var PhoneNo: Long, override var Id: Int) : IUser {

}