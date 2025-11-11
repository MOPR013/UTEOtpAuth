package vn.hcmute.group13

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()   // Ẩn action bar mặc định (gọn UI)

        // Lấy NavController từ FragmentContainerView trong activity_main.xml
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val nav = navHost.navController

        // Nạp graph và đặt startDestination = homeFragment
        val graph = nav.navInflater.inflate(R.navigation.nav_auth)
        graph.setStartDestination(R.id.loginFragment)
        nav.graph = graph

        // (Tùy chọn) Nếu app được mở lại từ trạng thái cũ mà đang ở Login,
        // em có thể tự động điều hướng về Home khi đã đăng nhập (demo):
        val loggedIn = getSharedPreferences("auth", MODE_PRIVATE)
            .getBoolean("isLoggedIn", false)
        if (loggedIn && nav.currentDestination?.id == R.id.loginFragment) {
            val opts = navOptions {
                popUpTo(R.id.loginFragment) { inclusive = true }
                launchSingleTop = true
            }
            nav.navigate(R.id.homeFragment, null, opts)
        }
    }
}
