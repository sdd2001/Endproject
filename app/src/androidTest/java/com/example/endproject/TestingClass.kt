package com.example.endproject

import androidx.core.content.MimeTypeFilter.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestingClass {
    // Kotlin activity scenario
    @get:Rule var mActivityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule<MainActivity>() // doesn't seem to work

    @Test
    public fun clickFab_loadItems() {
        onView(withId(R.id.fabLoad)).perform(click())
        onView(withId(R.id.rvGames).())

    }

}