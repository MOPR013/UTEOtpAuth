package vn.hcmute.group13.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import vn.hcmute.group13.R

class RegisterFragment : Fragment(R.layout.fragment_register) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnRegister).setOnClickListener {
            // TODO: validate + gọi API đăng ký
            // Theo yêu cầu: đăng ký thành công => coi như đã đăng nhập
            requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
                .edit().putBoolean("isLoggedIn", true).apply()

            val opts = navOptions {
                popUpTo(R.id.homeFragment) { inclusive = true }
                launchSingleTop = true
            }
            findNavController().navigate(R.id.homeFragment, null, opts)
        }

        view.findViewById<Button>(R.id.btnGoHome)?.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
