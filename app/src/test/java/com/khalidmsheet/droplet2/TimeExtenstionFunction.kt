package com.khalidmsheet.droplet2

import com.khalidmsheet.droplet2.presentation.timeline.toTimeAgo
import org.junit.Test
import java.util.Calendar

class TimeExtentionFunctionUnitTest {

    @Test
    fun it_should_be_just_now() {
        val calendar = Calendar.getInstance()
        val now = calendar.timeInMillis
        val isNow =  now.toTimeAgo() == "Just now"
        assert(isNow)
    }

    @Test
    fun it_should_not_be_just_now() {
        val calendar = Calendar.getInstance()
        val now = calendar.timeInMillis + 10
        val isNow =  now.toTimeAgo() == "Just now"
        assert(isNow)
    }
}