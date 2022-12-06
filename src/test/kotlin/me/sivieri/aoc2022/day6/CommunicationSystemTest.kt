package me.sivieri.aoc2022.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CommunicationSystemTest {

    @Test
    fun `premarker test 1`() {
        val communicationSystem = CommunicationSystem("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
        assertThat(communicationSystem.countPremarkerData(), `is`(7))
    }

    @Test
    fun `premarker test 2`() {
        val communicationSystem = CommunicationSystem("bvwbjplbgvbhsrlpgdmjqwftvncz")
        assertThat(communicationSystem.countPremarkerData(), `is`(5))
    }

    @Test
    fun `premarker test 3`() {
        val communicationSystem = CommunicationSystem("nppdvjthqldpwncqszvftbrmjlhg")
        assertThat(communicationSystem.countPremarkerData(), `is`(6))
    }

    @Test
    fun `premarker test 4`() {
        val communicationSystem = CommunicationSystem("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
        assertThat(communicationSystem.countPremarkerData(), `is`(10))
    }

    @Test
    fun `premarker test 5`() {
        val communicationSystem = CommunicationSystem("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
        assertThat(communicationSystem.countPremarkerData(), `is`(11))
    }

}