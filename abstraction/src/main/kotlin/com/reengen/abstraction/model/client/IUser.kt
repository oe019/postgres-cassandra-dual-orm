package com.reengen.abstraction.model.client

import com.reengen.abstraction.global.IUniqueIdentifier
import com.reengen.abstraction.global.IbeNamable
import com.reengen.abstraction.global.IbePhonable
import com.reengen.abstraction.global.IeMail

/**
 * Created by erguzelolgun on 05/07/2017.
 */
interface IUser: IbeNamable, IbePhonable, IeMail, IUniqueIdentifier {
}