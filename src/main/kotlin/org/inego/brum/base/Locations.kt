package org.inego.brum.base

import org.inego.brum.base.Era.CANAL
import org.inego.brum.base.Era.RAIL


sealed class Location(
    override val name: String
) : MapGraphNode {

    override fun toString() = name
}


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


val towns = listOf(
    Belper, Derby,
    Leek, StokeOnTrent, Stone, Uttoxeter,
    Stafford, BurtonOnTrent, Cannock, Tamworth, Warsall,
    Coalbrookdale, Dudley, Kidderminster, Wolverhampton, Worcester,
    Birmingham, Coventry, Nuneaton, Redditch
)


open class Merchant(
    name: String,
    val slotCount: Int,
    val reward: MerchantReward
) : Location(name)


object Oxford : Merchant("Oxford", 2, IncomeReward(2))
object Gloucester : Merchant("Gloucester", 2, FreeDevelopReward)
object Shrewsbury : Merchant("Shrewsbury", 1, VictoryPointsReward(4))
object Warrington : Merchant("Warrington", 2, MoneyReward(5))
object Nottingham : Merchant("Nottingham", 2, VictoryPointsReward(3))

val merchants = listOf(Oxford, Gloucester, Shrewsbury, Warrington, Nottingham)



open class FarmBrewery(
    name: String
) : Location(name)

object CannockFarmBrewery : FarmBrewery("Cannock Farm")
object KidderminsterWorcesterFarmBrewery : FarmBrewery("Worcester/Kidderminster Farm")

val farmBreweries = listOf(CannockFarmBrewery, KidderminsterWorcesterFarmBrewery)


enum class Era {
    CANAL,
    RAIL
}


class Link(
    override val name: String,
    val era: Era?
) : MapGraphNode {
    override fun toString(): String {
        return if (era == null) name else "$name ($era)"
    }
}


val gameGraph = MapGraph().apply {

    add(towns)
    add(merchants)
    add(farmBreweries)

    fun link(vararg locations: Location, era: Era? = null, customName: String? = null) {
        for (location in locations) {
            if (location !in nodes) {
                throw IllegalStateException("Unknown location for a link: ${location.name}")
            }
        }
        val name = customName ?: locations.joinToString(" - ") { it.name }
        val link = Link(name, era)
        add(link, *locations)
    }

    link(Oxford, Birmingham)
    link(Oxford, Redditch)
    link(Gloucester, Redditch)
    link(Gloucester, Worcester)
    link(Shrewsbury, Coalbrookdale)
    link(Warrington, StokeOnTrent)
    link(Nottingham, Derby)

    link(Redditch, Birmingham, era = RAIL)

    link(Worcester, Birmingham)
    link(Worcester, Kidderminster, KidderminsterWorcesterFarmBrewery, customName = "Worcester - Kidderminster - Farm")
    link(Kidderminster, Dudley)
    link(Kidderminster, Coalbrookdale)

    link(Dudley, Wolverhampton)
    link(Dudley, Birmingham)
    link(Coventry, Birmingham)
    link(Coventry, Nuneaton, era = RAIL)
    link(Birmingham, Nuneaton, era = RAIL)
    link(Birmingham, Tamworth)
    link(Birmingham, Warsall)

    link(Nuneaton, Tamworth)

    link(Coalbrookdale, Wolverhampton)
    link(Wolverhampton, Warsall)
    link(Wolverhampton, Cannock)
    link(Warsall, Cannock)
    link(Warsall, BurtonOnTrent, era = CANAL)
    link(Warsall, Tamworth)

    link(Tamworth, BurtonOnTrent)

    link(Cannock, CannockFarmBrewery, customName = "Cannock - Farm")
    link(Cannock, Stafford)
    link(Cannock, BurtonOnTrent, era = RAIL)

    link(BurtonOnTrent, Stone)
    link(BurtonOnTrent, Derby)

    link(Stafford, Stone)

    link(Stone, StokeOnTrent)
    link(Stone, Uttoxeter, era = RAIL)
    link(Uttoxeter, Derby, era = RAIL)
    link(Derby, Belper)

    link(StokeOnTrent, Leek)
    link(Leek, Belper)
}


fun main() {
    gameGraph.nodes.sortedBy { it.name }.forEach { n ->
        println(n.name)
        gameGraph.connections[n]!!.forEach {
            println("  ${it}")
        }
    }
}
