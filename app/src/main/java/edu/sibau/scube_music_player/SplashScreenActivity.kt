package edu.sibau.scube_music_player

// SplashScreenActivity.kt
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 10000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app's main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
