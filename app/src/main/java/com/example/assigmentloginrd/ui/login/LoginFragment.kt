package com.example.assigmentloginrd.ui.login

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.assigmentloginrd.R
import com.example.assigmentloginrd.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels<LoginViewModel>()
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    // Default credentials for first-time login
    private val defaultEmail = "test@gmail.com"
    private val defaultPassword = "123456"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        sharedPreferences = requireContext().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)

        // Set up the login button click listener
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val isFirstTime = sharedPreferences.getBoolean(getString(R.string.isfirsttime), true)

            Log.e(TAG, "onCreateView:------ "+isFirstTime )
            // Trigger login action in ViewModel
            loginViewModel.login(email, password, isFirstTime, defaultEmail, defaultPassword)
        }

        // Observe login success and handle navigation or show error
        loginViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                if (sharedPreferences.getBoolean(getString(R.string.isfirsttime), true)) {
                    // Mark first-time login complete
                    sharedPreferences.edit().putBoolean(getString(R.string.isfirsttime), false).apply()
                }
                findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
            } else {
                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}

