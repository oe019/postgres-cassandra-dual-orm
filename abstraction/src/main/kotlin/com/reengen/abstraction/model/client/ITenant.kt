package com.reengen.abstraction.model.client

import com.reengen.abstraction.global.IBrand
import com.reengen.abstraction.global.IUniqueIdentifier
import com.reengen.abstraction.global.IbeNamable

/**
 * Created by erguzelolgun on 05/07/2017.
 */
interface ITenant: IbeNamable, IBrand, IUniqueIdentifier {
}