package org.inego.brum.base

sealed class Industry(
    val name: String,
    override val c2: Int,
    override val c3: Int,
    override val c4: Int,
) : CardStats


object IronWorks : Industry("Iron works", 4, 4, 4)
object CoalMine : Industry("Coal mine", 2, 2, 3)
object GoodsCotton : Industry("Coal mine", 0, 6, 8)
object Pottery : Industry("Pottery", 2, 2, 3)
object Brewery : Industry("Brewery", 5, 5, 5)


val industries: List<Industry> = listOf(
    IronWorks,
    CoalMine,
    GoodsCotton,
    Pottery,
    Brewery
)