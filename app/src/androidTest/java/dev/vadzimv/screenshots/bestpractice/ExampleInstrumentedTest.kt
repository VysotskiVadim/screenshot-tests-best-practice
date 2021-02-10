import dev.vadzimv.screenshots.bestpractice.ScrollingActivity

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.karumi.shot.ScreenshotTest
import dev.vadzimv.screenshots.bestpractice.compareDayNightScreenshots
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : ScreenshotTest {

    @Test
    fun useAppContext() {
        val scenario = ActivityScenario.launch(ScrollingActivity::class.java)
        compareDayNightScreenshots(scenario)

    }
}