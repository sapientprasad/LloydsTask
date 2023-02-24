package com.example.lloydstask.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lloydstask.R
import com.example.lloydstask.ui.activities.DogsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DogsActivityTest {

    @get:Rule
    val activityTestRule: ActivityScenarioRule<DogsActivity> =
        ActivityScenarioRule(DogsActivity::class.java)

    @Test
    fun testActivityUIElements() {
        verifyViewWithIdIsDisplayed(R.id.imageView)
        verifyViewWithIdIsDisplayed(R.id.btnRefresh)
        verifyViewWithIdIsClickable(R.id.btnRefresh)
    }

    private fun verifyViewWithIdIsDisplayed(resId: Int) {
        onView(withId(resId)).check(matches(isDisplayed()))
    }

    private fun verifyViewWithIdIsClickable(resId: Int) {
        onView(withId(resId)).check(matches(isClickable()))
    }
}