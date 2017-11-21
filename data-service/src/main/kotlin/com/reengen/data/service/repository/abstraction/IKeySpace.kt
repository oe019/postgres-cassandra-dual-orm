package com.reengen.data.service.repository.abstraction

import com.reengen.data.service.repository.enum.StrategyClassTypes

interface IKeySpace {
    var _qBuilder:StringBuilder

    fun WITH_REPLICATION(clss:StrategyClassTypes,replicationFactor:Int) :IStrategy

}