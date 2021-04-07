package org.inego.brum.base

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


fun count(field: CardStats.() -> Int) = (towns + industries).sumBy { it.field() }


class CardCountTest : FunSpec({

    test("Count 2") {
        count { c2 } shouldBe 40
    }

    test("Count 3") {
        count { c3 } shouldBe 54
    }

    test("Count 4") {
        count { c4 } shouldBe 64
    }
})
