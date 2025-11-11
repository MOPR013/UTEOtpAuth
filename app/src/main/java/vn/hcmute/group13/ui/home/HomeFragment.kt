package vn.hcmute.group13.ui.home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import vn.hcmute.group13.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var btnForgot: Button
    private lateinit var btnLogout: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin   = view.findViewById(R.id.btnGoLogin)
        btnRegister= view.findViewById(R.id.btnGoRegister)
        btnForgot  = view.findViewById(R.id.btnGoForgot)
        btnLogout  = view.findViewById(R.id.btnLogout)

        // Điều hướng 4 nút
        btnLogin.setOnClickListener   { findNavController().navigate(R.id.loginFragment) }
        btnRegister.setOnClickListener{ findNavController().navigate(R.id.registerFragment) }
        btnForgot.setOnClickListener  { findNavController().navigate(R.id.forgotFragment) }

        btnLogout.setOnClickListener {
            // Đăng xuất: tắt cờ, quay về Home và làm sạch backstack
            requireContext().getSharedPreferences("auth", MODE_PRIVATE)
                .edit().putBoolean("isLoggedIn", false).apply()

            val opts = navOptions {
                popUpTo(R.id.homeFragment) { inclusive = true }
                launchSingleTop = true
            }
            findNavController().navigate(R.id.homeFragment, null, opts)
            updateUi() // cập nhật ngay
        }

        updateUi()
    }

    override fun onResume() {
        super.onResume()
        // Mỗi lần quay về Home đều refresh nút theo trạng thái
        updateUi()
    }

    private fun updateUi() {
        val loggedIn = requireContext()
            .getSharedPreferences("auth", MODE_PRIVATE)
            .getBoolean("isLoggedIn", false)

        if (loggedIn) {
            // Đã đăng nhập: chỉ hiện Quên mật khẩu & Đăng xuất
            btnLogin.visibility    = View.GONE
            btnRegister.visibility = View.GONE
            btnForgot.visibility   = View.VISIBLE
            btnLogout.visibility   = View.VISIBLE
        } else {
            // Chưa đăng nhập: hiện 3 nút, ẩn Đăng xuất
            btnLogin.visibility    = View.VISIBLE
            btnRegister.visibility = View.VISIBLE
            btnForgot.visibility   = View.VISIBLE
            btnLogout.visibility   = View.GONE
        }
    }
}
