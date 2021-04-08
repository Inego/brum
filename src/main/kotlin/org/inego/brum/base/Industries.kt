package org.inego.brum.base


sealed class IndustrySlot : IndustryCount {
    override val slot: IndustrySlot
        get() = this
    override val count: Int
        get() = 1
}


abstract class Industry(
    val name: String
) : IndustrySlot()


object IronWorks : Industry("Iron works")
object CoalMine : Industry("Coal mine")
object Brewery : Industry("Brewery")
object CottonMill : Industry("Cotton mill")
object Goods : Industry("Mfg. goods")
object Pottery : Industry("Pottery")


sealed class IndustryCard(
    override val c2: Int,
    override val c3: Int,
    override val c4: Int,
) : CardStats {
    abstract val name: String
    abstract fun supports(industry: Industry): Boolean
}

open class SingleIndustryCard(
    private val industry: Industry,
    c2: Int,
    c3: Int,
    c4: Int
) : IndustryCard(c2, c3, c4) {
    override val name: String
        get() = industry.name

    override fun supports(industry: Industry) = this.industry == industry
}


object IronWorksCard : SingleIndustryCard(IronWorks,  4, 4, 4)
object CoalMineCard : SingleIndustryCard(CoalMine, 2, 2, 3)
object GoodsCottonCard : IndustryCard( 0, 6, 8) {
    override val name: String
        get() = "Goods/Cotton"
    override fun supports(industry: Industry): Boolean {
        return industry == Goods || industry == CottonMill
    }
}
object PotteryCard : SingleIndustryCard(Pottery, 2, 2, 3)
object BreweryCard : SingleIndustryCard(Brewery, 5, 5, 5)


interface IndustryCount {
    val slot: IndustrySlot
    val count: Int
}


val industryCards: List<IndustryCard> = listOf(
    IronWorksCard,
    CoalMineCard,
    GoodsCottonCard,
    PotteryCard,
    BreweryCard
)



open class DoubleSlot(
    val industry1: Industry,
    val industry2: Industry,
) : IndustrySlot()


operator fun IndustrySlot.times(count: Int) = object : IndustryCount {
    override val slot: IndustrySlot
        get() = this@times
    override val count: Int
        get() = count
}


object CottonGoods : DoubleSlot(CottonMill, Goods)
object CottonBrewery : DoubleSlot(CottonMill, Brewery)
object CottonCoal : DoubleSlot(CottonMill, CoalMine)
object PotteryIron : DoubleSlot(Pottery, IronWorks)
object GoodsCoal : DoubleSlot(Goods, CoalMine)
object GoodsIron : DoubleSlot(Goods, IronWorks)
object GoodsBrewery : DoubleSlot(Goods, Brewery)
object IronBrewery : DoubleSlot(IronWorks, Brewery)