package org.inego.brum.base


sealed class Location(
    val name: String
)


sealed class Town(
    name: String,
    override val c2: Int,
    override val c3: Int,
    override val c4: Int,
    private vararg val slots: IndustryCount
) : Location(name), CardStats


object Belper : Town("Belper", 0, 0, 2, CottonGoods, CoalMine, Pottery)
object Derby : Town("Derby", 0, 0, 3, CottonBrewery, CottonGoods, IronWorks)

object Leek : Town("Leek", 0, 2, 2, CottonGoods, CottonCoal)
object StokeOnTrent : Town("Stoke-on-Trent", 0, 3, 3, CottonGoods, PotteryIron, Goods)
object Stone : Town("Stone", 0, 2, 2, CottonBrewery, GoodsCoal)
object Uttoxeter : Town("Uttoxeter", 0, 1, 2, GoodsBrewery, CottonBrewery)

object Stafford : Town("Stafford", 2, 2, 2, GoodsBrewery, Pottery)
object BurtonOnTrent : Town("Burton-On-Trent", 2, 2, 2, GoodsCoal, Brewery)
object Cannock : Town("Cannock", 2, 2, 2, GoodsCoal, CoalMine)
object Tamworth : Town("Tamworth", 1, 1, 1, CottonCoal * 2)
object Warsall : Town("Warsall", 1, 1, 1, GoodsIron, GoodsBrewery)

object Coalbrookdale : Town("Coalbrookdale", 3, 3, 3, IronBrewery, IronWorks, CoalMine)
object Dudley : Town("Dudley", 2, 2, 2, CoalMine, IronWorks)
object Kidderminster : Town("Kidderminster", 2, 2, 2, CottonCoal, CottonMill)
object Wolverhampton : Town("Wolverhampton", 2, 2, 2, Goods, GoodsCoal)
object Worcester : Town("Worcester", 2, 2, 2, CottonMill * 2)

object Birmingham : Town("Birmingham", 3, 3, 3, CottonGoods, Goods * 2, IronWorks)
object Coventry : Town("Coventry", 3, 3, 3, Pottery, GoodsCoal, GoodsIron)
object Nuneaton : Town("Nuneaton", 1, 1, 1, GoodsBrewery, CottonCoal)
object Redditch : Town("Redditch", 1, 1, 1, GoodsCoal, IronWorks)


val towns = setOf(
    Belper, Derby,
    Leek, StokeOnTrent, Stone, Uttoxeter,
    Stafford, BurtonOnTrent, Cannock, Tamworth, Warsall,
    Coalbrookdale, Dudley, Kidderminster, Wolverhampton, Worcester,
    Birmingham, Coventry, Nuneaton, Redditch
)



class Merchant(name: String) : Location(name)
