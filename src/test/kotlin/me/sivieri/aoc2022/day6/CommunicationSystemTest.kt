package me.sivieri.aoc2022.day6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CommunicationSystemTest {

    @Test
    fun `premarker test 1`() {
        val communicationSystem = CommunicationSystem("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
        assertThat(communicationSystem.countPremarkerData(4), `is`(7))
    }

    @Test
    fun `premarker test 2`() {
        val communicationSystem = CommunicationSystem("bvwbjplbgvbhsrlpgdmjqwftvncz")
        assertThat(communicationSystem.countPremarkerData(4), `is`(5))
    }

    @Test
    fun `premarker test 3`() {
        val communicationSystem = CommunicationSystem("nppdvjthqldpwncqszvftbrmjlhg")
        assertThat(communicationSystem.countPremarkerData(4), `is`(6))
    }

    @Test
    fun `premarker test 4`() {
        val communicationSystem = CommunicationSystem("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
        assertThat(communicationSystem.countPremarkerData(4), `is`(10))
    }

    @Test
    fun `premarker test 5`() {
        val communicationSystem = CommunicationSystem("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
        assertThat(communicationSystem.countPremarkerData(4), `is`(11))
    }

    @Test
    fun `premarker test 6`() {
        val communicationSystem = CommunicationSystem("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
        assertThat(communicationSystem.countPremarkerData(14), `is`(19))
    }

    @Test
    fun `premarker test 7`() {
        val communicationSystem = CommunicationSystem("bvwbjplbgvbhsrlpgdmjqwftvncz")
        assertThat(communicationSystem.countPremarkerData(14), `is`(23))
    }

    @Test
    fun `premarker test 8`() {
        val communicationSystem = CommunicationSystem("nppdvjthqldpwncqszvftbrmjlhg")
        assertThat(communicationSystem.countPremarkerData(14), `is`(23))
    }

    @Test
    fun `premarker test 9`() {
        val communicationSystem = CommunicationSystem("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
        assertThat(communicationSystem.countPremarkerData(14), `is`(29))
    }

    @Test
    fun `premarker test 10`() {
        val communicationSystem = CommunicationSystem("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")
        assertThat(communicationSystem.countPremarkerData(14), `is`(26))
    }

}