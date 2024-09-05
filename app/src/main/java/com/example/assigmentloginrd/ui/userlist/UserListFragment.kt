package com.example.assigmentloginrd.ui.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.assigmentloginrd.R
import com.example.assigmentloginrd.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.assigmentloginrd.ui.userlist.adapter.UserListAdapter


@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = userListViewModel

        val adapter = UserListAdapter { user -> userListViewModel.deleteUser(user) }
        binding.recyclerView.adapter = adapter

        userListViewModel.userList.observe(viewLifecycleOwner) { users ->
            adapter.submitList(users)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fab.setOnClickListener {
//                findNavController().navigate(R.id.action_userListFragment_to_userFormFragment)
            }
        }
    }
}
