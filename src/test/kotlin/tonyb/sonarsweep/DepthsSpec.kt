package tonyb.sonarsweep

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

object DepthsSpec {
    @Test
    fun `counts the number of times the depth increases over a three-reading window`() {
        val depths = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263).map(::Depth)

        assertThat(countDepthIncreases(depths), equalTo(5))
    }
}