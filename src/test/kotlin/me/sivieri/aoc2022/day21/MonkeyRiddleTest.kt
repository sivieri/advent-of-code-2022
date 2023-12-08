package me.sivieri.aoc2022.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class MonkeyRiddleTest {

    @Test
    fun `part 1 example`() {
        val input = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
        """.trimIndent()
        val riddle = MonkeyRiddle(input)
        val result = riddle.findRootNumber()
        assertThat(result, `is`(152))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
        """.trimIndent()
        val riddle = MonkeyRiddle(input)
        val result = riddle.findYelledNumber()
        assertThat(result, `is`(301))
    }

}