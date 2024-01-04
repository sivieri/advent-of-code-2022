package me.sivieri.aoc2022.day25

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class SnafuTest {

    @Test
    fun `decimal to snafu`() {
        assertThat(1.toSnafu(), `is`(Snafu("1")))
        assertThat(2.toSnafu(), `is`(Snafu("2")))
        assertThat(3.toSnafu(), `is`(Snafu("1=")))
        assertThat(4.toSnafu(), `is`(Snafu("1-")))
        assertThat(5.toSnafu(), `is`(Snafu("10")))
        assertThat(6.toSnafu(), `is`(Snafu("11")))
        assertThat(7.toSnafu(), `is`(Snafu("12")))
        assertThat(8.toSnafu(), `is`(Snafu("2=")))
        assertThat(9.toSnafu(), `is`(Snafu("2-")))
        assertThat(10.toSnafu(), `is`(Snafu("20")))
        assertThat(15.toSnafu(), `is`(Snafu("1=0")))
        assertThat(20.toSnafu(), `is`(Snafu("1-0")))
        assertThat(2022.toSnafu(), `is`(Snafu("1=11-2")))
        assertThat(12345.toSnafu(), `is`(Snafu("1-0---0")))
        assertThat(314159265L.toSnafu(), `is`(Snafu("1121-1110-1=0")))
    }

    @Test
    fun `snafu to decimal`() {
        assertThat(Snafu("1=-0-2").toInt(), `is`(1747))
        assertThat(Snafu("12111").toLong(), `is`(906L))
        assertThat(Snafu("2=0=").toInt(), `is`(198))
        assertThat(Snafu("21").toInt(), `is`(11))
        assertThat(Snafu("2=01").toInt(), `is`(201))
        assertThat(Snafu("111").toInt(), `is`(31))
        assertThat(Snafu("20012").toLong(), `is`(1257L))
        assertThat(Snafu("112").toInt(), `is`(32))
        assertThat(Snafu("1=-1=").toInt(), `is`(353))
        assertThat(Snafu("1-12").toInt(), `is`(107))
        assertThat(Snafu("12").toInt(), `is`(7))
        assertThat(Snafu("1=").toInt(), `is`(3))
        assertThat(Snafu("122").toInt(), `is`(37))
    }

}