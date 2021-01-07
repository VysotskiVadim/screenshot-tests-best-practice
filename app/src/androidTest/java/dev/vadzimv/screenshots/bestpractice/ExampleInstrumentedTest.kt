package dev.vadzimv.screenshots.bestpractice

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karumi.shot.ScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : ScreenshotTest {
    @Test
    fun useAppContext() {
        val activity = ActivityScenario.launch(ScrollingActivity::class.java)

    }
}