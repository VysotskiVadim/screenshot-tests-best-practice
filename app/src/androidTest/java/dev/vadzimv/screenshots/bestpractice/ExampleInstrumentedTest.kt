import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.facebook.testing.screenshot.ViewHelpers
import com.karumi.shot.ScreenshotTest
import dev.vadzimv.screenshots.bestpractice.R
import dev.vadzimv.screenshots.bestpractice.ScrollingActivity
import dev.vadzimv.screenshots.bestpractice.compareDayNightScreenshots
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : ScreenshotTest {

    @Test
    fun activityScreenshotTest() {
        val scenario = ActivityScenario.launch(ScrollingActivity::class.java)
        compareDayNightScreenshots(scenario)
    }

    @Test
    fun viewScreenshotTest() = compareDayNightScreenshots(R.layout.activity_scrolling) {
        ViewHelpers.setupView(it).setExactWidthPx(800).setExactHeightPx(3000).layout()
    }
}