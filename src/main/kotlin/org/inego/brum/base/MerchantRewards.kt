package org.inego.brum.base

sealed class MerchantReward(
    val name: String
)


object FreeDevelopReward : MerchantReward("Free develop")


class VictoryPointsReward(
    private val vp: Int
) : MerchantReward("$vp VP")


class MoneyReward(
    private val amount: Int
) : MerchantReward("£$amount")


class IncomeReward(
    private val amount: Int
) : MerchantReward("Income +$amount")
