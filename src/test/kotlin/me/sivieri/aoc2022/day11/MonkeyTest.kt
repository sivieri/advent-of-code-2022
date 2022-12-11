package me.sivieri.aoc2022.day11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class MonkeyTest {

    @Test
    fun `parsing test`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
        """.trimIndent()
        val monkey = Monkey.parse(input)
        assertThat(monkey.id, `is`(0))
        assertThat(monkey.items, containsInAnyOrder(79, 98))
        assertThat(monkey.operation, `is`("old * 19"))
        assertThat(monkey.test, `is`(23))
        assertThat(monkey.trueResponse, `is`(2))
        assertThat(monkey.falseResponse, `is`(3))
    }

    @Test
    fun `add item`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
        """.trimIndent()
        val monkey = Monkey.parse(input)
        monkey.addItem(23)
        assertThat(monkey.items, containsInAnyOrder(79, 98, 23))
        assertThat(monkey.items.peekLast(), `is`(23))
    }

    @Test
    fun `evaluate first item`() {
        val input = """
            Monkey 0:
              Starting items: 79, 98
              Operation: new = old * 19
              Test: divisible by 23
                If true: throw to monkey 2
                If false: throw to monkey 3
        """.trimIndent()
        val monkey = Monkey.parse(input)
        val result = monkey.evaluateItem(3, 19)
        assertThat(result, `is`(Pair(500L, 3)))
    }

}