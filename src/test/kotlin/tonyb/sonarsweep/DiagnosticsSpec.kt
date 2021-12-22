package tonyb.sonarsweep

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test


object DiagnosticsSpec {
    @Test
    fun `calculating gamma from three simple numbers`() {
        assertThat(numbers(listOf("10000", "10000", "00000")).gamma(), equalTo(16))
        assertThat(numbers(listOf("01000", "01000", "00000")).gamma(), equalTo(8))
        assertThat(numbers(listOf("00100", "00100", "00000")).gamma(), equalTo(4))
        assertThat(numbers(listOf("00010", "00010", "00000")).gamma(), equalTo(2))
        assertThat(numbers(listOf("00001", "00001", "00000")).gamma(), equalTo(1))
        assertThat(numbers(listOf("00000", "00000", "00000")).gamma(), equalTo(0))
    }

    @Test
    fun `calculating epsilon from three simple numbers`() {
        assertThat(numbers(listOf("00000", "10000", "00000")).epsilon(), equalTo(16))
        assertThat(numbers(listOf("00000", "01000", "00000")).epsilon(), equalTo(8))
        assertThat(numbers(listOf("00000", "00100", "00000")).epsilon(), equalTo(4))
        assertThat(numbers(listOf("00000", "00010", "00000")).epsilon(), equalTo(2))
        assertThat(numbers(listOf("00000", "00001", "00000")).epsilon(), equalTo(1))
        assertThat(numbers(listOf("00000", "00000", "00000")).epsilon(), equalTo(0))
    }

    @Test
    fun `the spec input`() {
        val readings = numbers(listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        ))

        val gamma = readings.gamma()
        val epsilon = readings.epsilon()

        assertThat(gamma, equalTo(22))
        assertThat(epsilon, equalTo(9))
    }
}
