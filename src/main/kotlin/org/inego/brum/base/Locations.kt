package org.inego.brum.base


sealed class Location(
    val name: String
)


sealed class Town(
    name: String,
    override val c2: Int,
    override val c3: Int,
    override val c4: Int,
) : Location(name), CardStats


object Belper : Town("Belper", 0, 0, 2)
object Derby : Town("Derby", 0, 0, 3)

object Leek : Town("Leek", 0, 2, 2)
object StokeOnTrent : Town("Stoke-on-Trent", 0, 3, 3)
object Stone : Town("Stone", 0, 2, 2)
object Uttoxeter : Town("Uttoxeter", 0, 1, 2)

object Stafford : Town("Stafford", 2, 2, 2)
object BurtonOnTrent : Town("Burton-On-Trent", 2, 2, 2)
object Cannock : Town("Cannock", 2, 2, 2)
object Tamworth : Town("Tamworth", 1, 1, 1)
object Warsall : Town("Warsall", 1, 1, 1)

object Coalbrookdale : Town("Coalbrookdale", 3, 3, 3)
object Dudley : Town("Dudley", 2, 2, 2)
object Kidderminster : Town("Kidderminster", 2, 2, 2)
object Wolverhampton : Town("Wolverhampton", 2, 2, 2)
object Worcester : Town("Worcester", 2, 2, 2)

object Birmingham : Town("Birmingham", 3, 3, 3)
object Coventry : Town("Coventry", 3, 3, 3)
object Nuneaton : Town("Nuneaton", 1, 1, 1)
object Redditch : Town("Redditch", 1, 1, 1)


val towns = setOf(
    Belper, Derby,
    Leek, StokeOnTrent, Stone, Uttoxeter,
    Stafford, BurtonOnTrent, Cannock, Tamworth, Warsall,
    Coalbrookdale, Dudley, Kidderminster, Wolverhampton, Worcester,
    Birmingham, Coventry, Nuneaton, Redditch
)



class Merchant(name: String) : Location(name)
