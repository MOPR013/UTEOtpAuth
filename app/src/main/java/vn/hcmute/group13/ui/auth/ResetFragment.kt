package vn.hcmute.group13.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import vn.hcmute.group13.R

class ResetFragment : Fragment(R.layout.fragment_reset) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            // TODO: gọi API đổi mật khẩu
            // Sau khi đổi mật khẩu: KHÔNG đăng nhập, quay về Login (trang ban đầu của flow)
            requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit().putBoolean("isLoggedIn", false).apply()

            val opts = navOptions {
                // dọn sạch stack đến Home để tránh quay lại OTP/Reset
                popUpTo(R.id.homeFragment) { inclusive = true }
                launchSingleTop = true
            }
            findNavController().navigate(R.id.loginFragment, null, opts)
        }

        view.findViewById<Button>(R.id.btnGoHome)?.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}




