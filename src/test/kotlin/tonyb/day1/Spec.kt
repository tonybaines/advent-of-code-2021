package tonyb.day1

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

object Spec {
    @Test
    fun `counts the number of times the depth increases`() {
        val depths = sequenceOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

        assertThat(countDepthIncreases(depths), equalTo(7))
    }
}