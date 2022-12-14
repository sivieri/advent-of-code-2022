package me.sivieri.aoc2022.day13

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class ListsComparisonTest {

    @Test
    fun `pair 1`() {
        val a = ListsParser.parse("[1,1,3,1,1]")
        val b = ListsParser.parse("[1,1,5,1,1]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 2`() {
        val a = ListsParser.parse("[[1],[2,3,4]]")
        val b = ListsParser.parse("[[1],4]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 3`() {
        val a = ListsParser.parse("[9]")
        val b = ListsParser.parse("[[8,7,6]]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `pair 4`() {
        val a = ListsParser.parse("[[4,4],4,4]")
        val b = ListsParser.parse("[[4,4],4,4,4]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 5`() {
        val a = ListsParser.parse("[7,7,7,7]")
        val b = ListsParser.parse("[7,7,7]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `pair 6`() {
        val a = ListsParser.parse("[]")
        val b = ListsParser.parse("[3]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 7`() {
        val a = ListsParser.parse("[[[]]]")
        val b = ListsParser.parse("[[]]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `pair 8`() {
        val a = ListsParser.parse("[1,[2,[3,[4,[5,6,7]]]],8,9]")
        val b = ListsParser.parse("[1,[2,[3,[4,[5,6,0]]]],8,9]")
        val result = ListsComparison.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `part 1 example`() {
        val input = """
[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]
        """.trimIndent().split("\n\n")
        val listsComparison = ListsComparison(input)
        assertThat(listsComparison.sumRightPairs(), `is`(13))
    }

}