package tonyb.sonarsweep

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test


object PropulsionSpec {
    @Test
    fun `going nowhere`() {
        assertThat(propulsion(listOf<String>()), equalTo(Position.ZERO))
    }

    @Test
    fun `going forwards`() {
        assertThat(propulsion(listOf("forward 5")), equalTo(Position(Depth.ZERO, Horizontal(5))))
    }

    @Test
    fun `going down`() {
        assertThat(propulsion(listOf("down 2")), equalTo(Position(Depth(2), Horizontal.ZERO)))
    }

    @Test
    fun `going up again`() {
        assertThat(propulsion(listOf("down 5", "up 2")), equalTo(Position(Depth(3), Horizontal.ZERO)))
    }
}