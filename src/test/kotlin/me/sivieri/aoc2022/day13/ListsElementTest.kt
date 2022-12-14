package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.common.Left
import me.sivieri.aoc2022.common.Right
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class ListsElementTest {

    @Test
    fun `parse line 1`() {
        val line = "[1,1,3,1,1]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(1),
            1 to Left(1),
            2 to Left(3),
            3 to Left(1),
            4 to Left(1))
        )
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 2`() {
        val line = "[1,1,5,1,1]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(1),
            1 to Left(1),
            2 to Left(5),
            3 to Left(1),
            4 to Left(1)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 3`() {
        val line = "[[1],[2,3,4]]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(0 to Left(1)))),
            1 to Right(ListsElement(mutableMapOf(
                0 to Left(2),
                1 to Left(3),
                2 to Left(4)
            )))
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 4`() {
        val line = "[[1],4]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(0 to Left(1)))),
            1 to Left(4)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 5`() {
        val line = "[9]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(0 to Left(9)))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 6`() {
        val line = "[[8,7,6]]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(
                0 to Left(8),
                1 to Left(7),
                2 to Left(6)
            )))
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 7`() {
        val line = "[[4,4],4,4]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(
                0 to Left(4),
                1 to Left(4)
            ))),
            1 to Left(4),
            2 to Left(4)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 8`() {
        val line = "[[4,4],4,4,4]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(
                0 to Left(4),
                1 to Left(4)
            ))),
            1 to Left(4),
            2 to Left(4),
            3 to Left(4)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 9`() {
        val line = "[7,7,7,7]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(7),
            1 to Left(7),
            2 to Left(7),
            3 to Left(7)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 10`() {
        val line = "[7,7,7]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(7),
            1 to Left(7),
            2 to Left(7)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 11`() {
        val line = "[]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf())
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 12`() {
        val line = "[3]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(0 to Left(3)))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 13`() {
        val line = "[[[]]]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(
                0 to Right(ListsElement(mutableMapOf()))
            )))
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 14`() {
        val line = "[[]]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf()))
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 15`() {
        val line = "[1,[2,[3,[4,[5,6,7]]]],8,9]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(1),
            1 to Right(ListsElement(mutableMapOf(
                0 to Left(2),
                1 to Right(ListsElement(mutableMapOf(
                    0 to Left(3),
                    1 to Right(ListsElement(mutableMapOf(
                        0 to Left(4),
                        1 to Right(ListsElement(mutableMapOf(
                            0 to Left(5),
                            1 to Left(6),
                            2 to Left(7)
                        )))
                    )))
                )))
            ))),
            2 to Left(8),
            3 to Left(9)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 16`() {
        val line = "[1,[2,[3,[4,[5,6,0]]]],8,9]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(1),
            1 to Right(ListsElement(mutableMapOf(
                0 to Left(2),
                1 to Right(ListsElement(mutableMapOf(
                    0 to Left(3),
                    1 to Right(ListsElement(mutableMapOf(
                        0 to Left(4),
                        1 to Right(ListsElement(mutableMapOf(
                            0 to Left(5),
                            1 to Left(6),
                            2 to Left(0)
                        )))
                    )))
                )))
            ))),
            2 to Left(8),
            3 to Left(9)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse large numbers`() {
        val line = "[1,[23,[3,[4,[5,6,0]]]],8,9]"
        val parsed = ListsElement.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Left(1),
            1 to Right(ListsElement(mutableMapOf(
                0 to Left(23),
                1 to Right(ListsElement(mutableMapOf(
                    0 to Left(3),
                    1 to Right(ListsElement(mutableMapOf(
                        0 to Left(4),
                        1 to Right(ListsElement(mutableMapOf(
                            0 to Left(5),
                            1 to Left(6),
                            2 to Left(0)
                        )))
                    )))
                )))
            ))),
            2 to Left(8),
            3 to Left(9)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `pair 1`() {
        val a = ListsElement.parse("[1,1,3,1,1]")
        val b = ListsElement.parse("[1,1,5,1,1]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 2`() {
        val a = ListsElement.parse("[[1],[2,3,4]]")
        val b = ListsElement.parse("[[1],4]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 3`() {
        val a = ListsElement.parse("[9]")
        val b = ListsElement.parse("[[8,7,6]]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `pair 4`() {
        val a = ListsElement.parse("[[4,4],4,4]")
        val b = ListsElement.parse("[[4,4],4,4,4]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 5`() {
        val a = ListsElement.parse("[7,7,7,7]")
        val b = ListsElement.parse("[7,7,7]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `pair 6`() {
        val a = ListsElement.parse("[]")
        val b = ListsElement.parse("[3]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(true))
    }

    @Test
    fun `pair 7`() {
        val a = ListsElement.parse("[[[]]]")
        val b = ListsElement.parse("[[]]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

    @Test
    fun `pair 8`() {
        val a = ListsElement.parse("[1,[2,[3,[4,[5,6,7]]]],8,9]")
        val b = ListsElement.parse("[1,[2,[3,[4,[5,6,0]]]],8,9]")
        val result = ListsElement.checkOrder(a, b)
        assertThat(result, `is`(false))
    }

}