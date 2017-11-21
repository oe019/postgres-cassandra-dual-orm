package com.reengen.data.service.repository.abstraction

interface INetworkStrategy :IStrategy{

    fun AND_DURABLE_WRITES(isDurableWrite: Boolean):StringBuilder
}