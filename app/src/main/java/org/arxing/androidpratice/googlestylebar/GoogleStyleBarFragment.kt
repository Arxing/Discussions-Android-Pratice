package org.arxing.androidpratice.googlestylebar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.arxing.androidpratice.databinding.FragmentGoogleStyleBarBinding

class GoogleStyleBarFragment : Fragment() {
    private lateinit var binding: FragmentGoogleStyleBarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGoogleStyleBarBinding.inflate(inflater, container, false).let { binding ->
            this.binding = binding
            binding.root
        }
    }
}