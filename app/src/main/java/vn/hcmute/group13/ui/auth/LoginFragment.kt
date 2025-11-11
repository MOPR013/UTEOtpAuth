package vn.hcmute.group13.ui.auth

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import vn.hcmute.group13.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        btnLogin = view.findViewById(R.id.btnLogin)

        view.findViewById<Button>(R.id.btnToRegister)
            .setOnClickListener { findNavController().navigate(R.id.action_login_to_register) }

        view.findViewById<Button>(R.id.btnForgot)
            .setOnClickListener { findNavController().navigate(R.id.action_login_to_forgot) }

        btnLogin.isEnabled = false
        etEmail.addTextChangedListener { updateUiState() }
        etPassword.addTextChangedListener { updateUiState() }

        etPassword.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (isValid()) doLogin(v) else showErrors()
                true
            } else false
        }

        btnLogin.setOnClickListener {
            if (isValid()) doLogin(it) else showErrors()
        }
    }

    private fun doLogin(v: View) {
        v.hideKeyboard()
        // TODO: call API thực tế
        requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
            .edit().putBoolean("isLoggedIn", true).apply()

        val opts = navOptions {
            popUpTo(R.id.homeFragment) { inclusive = true }
            launchSingleTop = true
        }
        findNavController().navigate(R.id.homeFragment, null, opts)
    }

    private fun updateUiState() {
        val ok = isValid()
        btnLogin.isEnabled = ok
        etEmail.error = if (!isEmailValid(etEmail.text.toString())) "Email không hợp lệ" else null
        etPassword.error = if (!isPasswordValid(etPassword.text.toString())) "Mật khẩu tối thiểu 6 ký tự" else null
    }

    private fun isValid() =
        isEmailValid(etEmail.text.toString()) && isPasswordValid(etPassword.text.toString())

    private fun isEmailValid(s: String) =
        s.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(s).matches()

    private fun isPasswordValid(s: String) = s.length >= 6

    private fun showErrors() {
        if (!isEmailValid(etEmail.text.toString())) etEmail.error = "Email không hợp lệ"
        if (!isPasswordValid(etPassword.text.toString())) etPassword.error = "Mật khẩu tối thiểu 6 ký tự"
    }
}

private fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
