package dev.vadzimv.screenshots.bestpractice

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ContextThemeWrapper
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.facebook.testing.screenshot.Screenshot
import com.facebook.testing.screenshot.internal.TestNameDetector
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest

fun compareDayNightScreenshots(
    @LayoutRes viewId: Int,
    @StyleRes theme: Int = R.style.Theme_Screenshottestsbestpractice,
    setupView: (View) -> Unit
) {
    val context = ContextThemeWrapper(
        InstrumentationRegistry.getInstrumentation().targetContext,
        theme
    )
    val dayView = LayoutInflater.from(context).inflate(viewId, null, false)
    runOnMainSync { setupView(dayView) }
    Screenshot.snap(dayView).setName(screenshotName("day")).record()

    val nightConfiguration = Configuration(context.resources.configuration)
    nightConfiguration.uiMode =
        Configuration.UI_MODE_NIGHT_YES or (nightConfiguration.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv())
    val nightContext = context.createConfigurationContext(nightConfiguration)
    val nightModeWrapper = ContextThemeWrapper(
        nightContext,
        theme
    )

    val nightView = LayoutInflater.from(nightModeWrapper).inflate(viewId, null, false)
    runOnMainSync { setupView(nightView) }
    Screenshot.snap(nightView).setName(screenshotName("night")).record()
}

fun <T : AppCompatActivity> ScreenshotTest.compareDayNightScreenshots(
    activityScenario: ActivityScenario<T>
) {
    val dayActivity = activityScenario.waitForActivity()
    compareScreenshot(dayActivity, name = screenshotName("day"))
    dayActivity.runOnUiThread {
        dayActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
    }
    val nightActivity = activityScenario.waitForActivity()
    compareScreenshot(nightActivity, name = screenshotName("night"))
}

private fun runOnMainSync(block: () -> Unit) =
    InstrumentationRegistry.getInstrumentation().runOnMainSync(block)

private fun screenshotName(postfix: String = ""): String {
    return "${TestNameDetector.getTestClass()}_${TestNameDetector.getTestName()}_$postfix"
}