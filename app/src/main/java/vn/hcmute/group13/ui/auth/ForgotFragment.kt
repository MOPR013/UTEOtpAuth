package vn.hcmute.group13.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import vn.hcmute.group13.R

class ForgotFragment : Fragment(R.layout.fragment_forgot) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btnSendOtp)
            .setOnClickListener {
                findNavController().navigate(R.id.action_forgot_to_otp)
            }
    }
}
