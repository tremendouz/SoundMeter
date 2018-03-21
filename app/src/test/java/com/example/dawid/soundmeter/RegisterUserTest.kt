package com.example.dawid.soundmeter

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config


/**
 * Created by daza on 17.03.18.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class RegisterUserTest {
    @Test
    fun shouldRegisterUser() {
        val activity = Robolectric.setupActivity(RegistrationActivity::class.java)

        val emailText = activity.findViewById<EditText>(R.id.text_email)
                .setText("example@example.com")

        val passwordText = activity.findViewById<EditText>(R.id.text_password)
                .setText("password123")
        val confirmPasswordText = activity.findViewById<EditText>(R.id.text_confirm_password)
                .setText("password123")


        val signupButton = activity.findViewById<Button>(R.id.button_register)

        assertNotNull(signupButton)
        assert(signupButton.isEnabled)


        signupButton.performClick()


        assertNotNull(activity.firebaseAuth.currentUser)

        val actualIntent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        val expectedIntent = Intent(activity, MainActivity::class.java)
        assertEquals(expectedIntent.component, actualIntent.component)

    }
}