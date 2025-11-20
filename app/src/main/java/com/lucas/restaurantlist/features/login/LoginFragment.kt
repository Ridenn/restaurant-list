package com.lucas.restaurantlist.features.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.lucas.restaurantlist.R
import com.lucas.restaurantlist.databinding.FragmentLoginBinding
import com.lucas.restaurantlist.features.storefeed.StoreFeedFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelStates()
        setupListeners()
    }

    private fun setupViewModelStates() {
        lifecycleScope.launch {
            viewModel.getLoginState.collect { loginState ->
                when (loginState) {
                    is LoginViewModel.LoginState.Success -> {
                        Toast.makeText(
                            requireContext(),
                            "Welcome, Lucas.",
                            Toast.LENGTH_SHORT
                        ).show()
                        goToStoreFeed()
                    }
                    is LoginViewModel.LoginState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            loginState.errorMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is LoginViewModel.LoginState.Loading -> {

                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.requestLogin(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun goToStoreFeed() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, StoreFeedFragment())
            .commit()
    }
}