package com.example.dawid.soundmeter

import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingPolicies
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.*
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


/**
 * Created by daza on 16.03.18.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class RegistrationTest {

    val invalidEmailString = "example@example..com"
    val validEmailString = "example@example.com"
    val validPasswordString = "password123"



    @Rule
    @JvmField
    val activityRule = ActivityTestRule<RegistrationActivity>(RegistrationActivity::class.java)



    @Before
    fun clearAll() {
        onView(withId(R.id.text_email)).perform(clearText())
        onView(withId(R.id.text_password)).perform(clearText())
        onView(withId(R.id.text_confirm_password)).perform(clearText())
    }


    @Test
    fun shouldShowTextInputError_afterInvalidEmail() {
        val resources = InstrumentationRegistry.getTargetContext().resources
        val emailError = resources.getString(R.string.error_input_email)

        onView(withId(R.id.text_email)).perform(typeText(invalidEmailString))
        onView(withId(R.id.input_layout_reg_email))
                .check(matches(hasTextInputLayoutErrorText(emailError)))
        // alternative way
        //onView(withText(R.string.error_input_email)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowNoTextInputError_afterValidEmail() {
        onView(withId(R.id.text_email)).perform(clearText())
        onView(withId(R.id.text_email)).perform(typeText(validEmailString))
        onView(withText(R.string.error_input_email)).check(doesNotExist())
    }

    @Test
    fun shouldEnableButton_afterSuccessfulValidation() {
        onView(withId(R.id.text_email))
                .perform(typeText(validEmailString))
                .perform(closeSoftKeyboard())

        onView(withId(R.id.text_password))
                .perform(typeText(validPasswordString))
                .perform(closeSoftKeyboard())

        onView(withId(R.id.text_confirm_password))
                .perform(typeText(validPasswordString))
                .perform(closeSoftKeyboard())

        onView((withId(R.id.button_register))).check(matches(isEnabled()))
    }

    @Test
    fun shouldDisableButton_afterSuccessfulValidation() {
        onView(withId(R.id.text_email))
                .perform(typeText(invalidEmailString))
                .perform(closeSoftKeyboard())

        onView(withId(R.id.text_password))
                .perform(typeText(validPasswordString))
                .perform(closeSoftKeyboard())

        onView(withId(R.id.text_confirm_password))
                .perform(typeText(validPasswordString))
                .perform(closeSoftKeyboard())

        onView((withId(R.id.button_register))).check(matches(not(isEnabled())))
    }



    fun hasTextInputLayoutErrorText(errorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {
        override fun matchesSafely(view: View?): Boolean {
            val error = (view as TextInputLayout).error
            val errorString = error.toString()
            return errorText == errorString
        }

        override fun describeTo(description: Description?) {
        }
    }

}