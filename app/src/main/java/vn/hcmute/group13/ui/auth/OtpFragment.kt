package vn.hcmute.group13.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import vn.hcmute.group13.R

class OtpFragment : Fragment(R.layout.fragment_otp) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnVerify).setOnClickListener {
            // TODO: verify OTP thật
            findNavController().navigate(R.id.action_otp_to_reset)
        }

        view.findViewById<Button>(R.id.btnGoHome)?.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}
