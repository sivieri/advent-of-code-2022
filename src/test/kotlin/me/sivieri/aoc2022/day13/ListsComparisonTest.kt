package me.sivieri.aoc2022.day13

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class ListsComparisonTest {

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