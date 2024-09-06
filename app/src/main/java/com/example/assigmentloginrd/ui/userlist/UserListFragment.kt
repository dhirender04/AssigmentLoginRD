package com.example.assigmentloginrd.ui.userlist

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.assigmentloginrd.ui.userlist.adapter.UserListAdapter
import javax.inject.Inject


@AndroidEntryPoint
class UserListFragment : Fragment() {

    private lateinit var binding: FragmentUserListBinding
    private val userListViewModel: UserListViewModel by viewModels()
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = userListViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListViewModel.loadUsers()
        val adapter = UserListAdapter { user ->
            userListViewModel.deleteUser(user)
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView( binding.recyclerView)
        binding.recyclerView.adapter = adapter

        userListViewModel.userList.observe(viewLifecycleOwner) { users ->
             adapter.setList(users)
        }
        binding.apply {
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_userListFragment_to_userFormFragment)
            }
        }
    }

    val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                 (binding.recyclerView.adapter as UserListAdapter).deleteUserAt(position)
            }
        }
    }
}
