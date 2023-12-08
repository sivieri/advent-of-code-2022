package me.sivieri.aoc2022.day21

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class RiddleOperationTest {

    @Test
    fun sum() {
        assertThat(SumOperation.execute(2, 3), `is`(5))
    }

    @Test
    fun `reverse sum x`() {
        assertThat(SumOperation.calculateMissingValue(2, null, 5), `is`(3))
    }

    @Test
    fun `reverse sum y`() {
        assertThat(SumOperation.calculateMissingValue(null, 3, 5), `is`(2))
    }

    @Test
    fun subtract() {
        assertThat(SubtractionOperation.execute(6, 4), `is`(2))
    }

    @Test
    fun `reverse subtract x`() {
        assertThat(SubtractionOperation.calculateMissingValue(6, null, 2), `is`(4))
    }

    @Test
    fun `reverse subtract y`() {
        assertThat(SubtractionOperation.calculateMissingValue(null, 4, 2), `is`(6))
    }

    @Test
    fun multiply() {
        assertThat(ProductOperation.execute(2, 3), `is`(6))
    }

    @Test
    fun `reverse multiply x`() {
        assertThat(ProductOperation.calculateMissingValue(2, null, 6), `is`(3))
    }

    @Test
    fun `reverse multiply y`() {
        assertThat(ProductOperation.calculateMissingValue(null, 3, 6), `is`(2))
    }

    @Test
    fun divide() {
        assertThat(DivisionOperation.execute(6, 3), `is`(2))
    }

    @Test
    fun `reverse divide x`() {
        assertThat(DivisionOperation.calculateMissingValue(6, null, 2), `is`(3))
    }

    @Test
    fun `reverse divide y`() {
        assertThat(DivisionOperation.calculateMissingValue(null, 3, 2), `is`(6))
    }

    @Test(expected = NotImplementedError::class)
    fun noop() {
        NoOp.execute(2, 3)
    }

    @Test(expected = NotImplementedError::class)
    fun `reverse noop x`() {
        NoOp.calculateMissingValue(2, null, 5)
    }

    @Test(expected = NotImplementedError::class)
    fun `reverse noop y`() {
        NoOp.calculateMissingValue(null, 3, 5)
    }

}