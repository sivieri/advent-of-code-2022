package me.sivieri.aoc2022.day18

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class DropletTest {

    @Test
    fun `simple droplet`() {
        val droplet = Droplet(listOf("1,1,1", "2,1,1"))
        assertThat(droplet.calculateSurface(), `is`(10))
    }

    @Test
    fun `part 1 example`() {
        val droplet = Droplet(listOf(
            "2,2,2",
            "1,2,2",
            "3,2,2",
            "2,1,2",
            "2,3,2",
            "2,2,1",
            "2,2,3",
            "2,2,4",
            "2,2,6",
            "1,2,5",
            "3,2,5",
            "2,1,5",
            "2,3,5"
        ))
        assertThat(droplet.calculateSurface(), `is`(64))
    }

}