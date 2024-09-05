package com.example.assigmentloginrd.ui.addUser

import com.example.assigmentloginrd.databinding.FragmentUserFormBinding



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
 import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFormFragment : Fragment() {

    private lateinit var binding: FragmentUserFormBinding
    private val userFormViewModel: UserFormViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserFormBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = userFormViewModel

        // Navigate back to the user list after saving the user
        userFormViewModel.saveUserEvent.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}
