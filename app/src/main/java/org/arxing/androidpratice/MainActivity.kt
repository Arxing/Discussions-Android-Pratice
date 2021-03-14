package org.arxing.androidpratice

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import org.arxing.androidpratice.databinding.ActivityMainBinding
import org.arxing.androidpratice.googlestylebar.GoogleStyleBarFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(LayoutInflater.from(this), null, false).let { binding ->
            this.binding = binding
            setContentView(binding.root)
        }

        val initFragment = GoogleStyleBarFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, initFragment).commit()
    }
}