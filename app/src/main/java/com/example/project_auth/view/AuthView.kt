package com.example.project_auth.view

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.example.project_auth.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class AuthView(context: Context, attributes: AttributeSet): LinearLayout(context, attributes) {

    private val etUsername: EditText
    private val etFirstName: TextInputEditText
    private val etLastName: TextInputEditText

    private val etPassword: TextInputEditText
    private val etConfirmPassword: TextInputEditText

    private val mbAuthButton: MaterialButton
    private val mbAuthMessage: MaterialButton

    init {
        View.inflate(context, R.layout.layout_register_view, this)

        etUsername = this.findViewById(R.id.et_username_layout_auth_view)
        etFirstName = this.findViewById(R.id.et_first_name_layout_auth_view)
        etLastName = this.findViewById(R.id.et_last_name_layout_auth_view)

        etPassword = this.findViewById(R.id.et_password_layout_auth_view)
        etConfirmPassword = this.findViewById(R.id.et_confirm_password_layout_auth_view)

        mbAuthButton = this.findViewById(R.id.mb_button_layout_auth_view)
        mbAuthMessage = this.findViewById(R.id.mb_message_layout_auth_view)
    }

    private fun populateViews(bundle: Bundle) {
        bundle.getString(EXTRA_USERNAME)?.apply {
            etUsername.setText(this)
        }
        bundle.getString(EXTRA_FIRST_NAME)?.apply {
            etFirstName.setText(this)
        }
        bundle.getString(EXTRA_LAST_NAME)?.apply {
            etLastName.setText(this)
        }
        bundle.getString(EXTRA_PASSWORD)?.apply {
            etPassword.setText(this)
        }
        bundle.getString(EXTRA_CONFIRM_PASSWORD)?.apply {
            etConfirmPassword.setText(this)
        }

    }


    fun getBundle() = Bundle().apply {
        putString(EXTRA_USERNAME, etUsername.text.toString())
        putString(EXTRA_FIRST_NAME, etFirstName.text.toString())
        putString(EXTRA_LAST_NAME, etLastName.text.toString())
        putString(EXTRA_PASSWORD, etPassword.text.toString())
        putString(EXTRA_CONFIRM_PASSWORD, etConfirmPassword.text.toString())
    }

    fun invokeLoginMode(bundle: Bundle?, onLoginClicked: (userName: String, password: String) -> Unit,
                        onMessageClicked: () -> Unit) {

        bundle?.apply {
            populateViews(this)
        }

        etFirstName.visibility = View.GONE
        etLastName.visibility = View.GONE
        etConfirmPassword.visibility = View.GONE


        mbAuthButton.text = context.resources.getString(R.string.login_button_text)
        mbAuthButton.icon = context.resources.getDrawable(R.drawable.ic_action_enter, context.theme)
        mbAuthMessage.text = context.resources.getString(R.string.login_message_text)

        mbAuthButton.setOnClickListener {
            onLoginClicked(
                    etUsername.text.toString(),
                    etPassword.text.toString())
        }

        mbAuthMessage.setOnClickListener {
            onMessageClicked()
        }
    }

    fun invokeRegisterMode(bundle: Bundle?,
            onRegisterClicked: (userName: String,
                                firstName: String,
                                lastName: String,
                                password: String) -> Unit,
            onMessageClicked: () -> Unit) {

        bundle?.apply {
            populateViews(this)
        }

        etFirstName.visibility = View.VISIBLE
        etLastName.visibility = View.VISIBLE
        etConfirmPassword.visibility = View.VISIBLE

        mbAuthButton.text = context.resources.getString(R.string.register_button_text)
//        mbAuthButton.icon = context.resources.getDrawable(
//                R.drawable.ic_action_register, context.theme)
        mbAuthMessage.text = context.resources.getString(R.string.register_message_text)

        mbAuthButton.setOnClickListener {
            if (etPassword.text.toString() != etConfirmPassword.text.toString()) {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            onRegisterClicked(
                    etUsername.text.toString(),
                    etFirstName.text.toString(),
                    etLastName.text.toString(),
                    etPassword.text.toString()
            )
        }

        mbAuthMessage.setOnClickListener {
            onMessageClicked()
        }
    }



    companion object {
        const val EXTRA_USERNAME = "Extra.Username"
        const val EXTRA_LAST_NAME = "Extra.LastName"
        const val EXTRA_FIRST_NAME = "Extra.First.Name"
        const val EXTRA_PASSWORD = "Extra.Password"
        const val EXTRA_CONFIRM_PASSWORD = "Extra.Confirm.Password"
    }


}