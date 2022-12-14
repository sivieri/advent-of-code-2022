package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.common.Left
import me.sivieri.aoc2022.common.Right
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class ListsParserTest {

    @Test
    fun `parse line 1`() {
        val line = "[1,1,3,1,1]"
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf(0 to Left(1)))),
            1 to Left(4)
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 5`() {
        val line = "[9]"
        val parsed = ListsParser.parse(line)
        val expected = ListsElement(mutableMapOf(0 to Left(9)))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 6`() {
        val line = "[[8,7,6]]"
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
        val expected = ListsElement(mutableMapOf())
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 12`() {
        val line = "[3]"
        val parsed = ListsParser.parse(line)
        val expected = ListsElement(mutableMapOf(0 to Left(3)))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 13`() {
        val line = "[[[]]]"
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
        val expected = ListsElement(mutableMapOf(
            0 to Right(ListsElement(mutableMapOf()))
        ))
        assertThat(parsed, `is`(expected))
    }

    @Test
    fun `parse line 15`() {
        val line = "[1,[2,[3,[4,[5,6,7]]]],8,9]"
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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
        val parsed = ListsParser.parse(line)
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

}