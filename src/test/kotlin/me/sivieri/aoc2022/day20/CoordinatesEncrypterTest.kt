package me.sivieri.aoc2022.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CoordinatesEncrypterTest {

    @Test
    fun `part 1 example`() {
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val encrypter = CoordinatesEncrypter()
        val result = encrypter.decrypt(input)
        assertThat(result, `is`(3))
    }

}