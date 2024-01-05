package com.example.authorize.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.authorize.databinding.FragmentAuthBinding
import com.example.authorize.di.App
import com.example.authorize.network.ApiErrorResponse
import com.example.authorize.network.ApiResponse
import com.example.authorize.presentation.customView.ErrorView
import com.example.authorize.presentation.customView.SuccessView

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels() {
        MainViewModelFactory((requireActivity().application as App).repository)
    }
    private lateinit var successView: SuccessView
    private lateinit var errorView: ErrorView

    private var phoneNumber: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.phoneNumberInput.addTextChangedListener(PhoneNumberFormatter)

        binding.codeGetButton.setOnClickListener {
            Log.d("AuthFragment", "button pushed")
            requestCode()
        }
        //Custom View
        successView = SuccessView(requireContext())
        errorView = ErrorView(requireContext())

        //Add to container, but view.GONE
        binding.customViewContainer.apply {
            addView(successView)
            addView(errorView)
            successView.visibility = View.GONE
            errorView.visibility = View.GONE
        }

        binding.confirmCodeButton.setOnClickListener {
            verifyCode()
        }

        successView.closeButton.setOnClickListener {
            successView.hide()
        }
        errorView.closeButton.setOnClickListener {
            errorView.hide()
        }
        errorView.requestNewCodeButton.setOnClickListener {
            requestNewCode()
        }
    }

    private fun requestCode() {
        phoneNumber = binding.phoneNumberInput.text.toString()
        phoneNumber?.let {
            if (it.isNotEmpty()) {
                viewModel.getCode(it, ::handleGetCodeSuccess, ::handleGetCodeError)
            }
        }
    }

    private fun onCodeRequested(response: ApiResponse) {
        binding.codeInputLayout.visibility = View.VISIBLE
        binding.confirmCodeButton.visibility = View.VISIBLE
        if (response.status == "new") {
            // Логика для нового пользователя, автоматическая отправка смс
        }
    }

    private fun verifyCode() {
        val code = binding.codeInputLayout.text.toString() //получаем код из поля ввода
        phoneNumber?.let { phone ->
            if (code.isNotEmpty()) {
                viewModel.getToken(phone, code, { token ->
                    onTokenReceived(phone, token)
                }, ::onCodeError)
            }
        }
    }

    private fun handleGetCodeSuccess(response: ApiResponse) {
        // Обработка успешного ответа
        onCodeRequested(response)
    }

    private fun onTokenReceived(phoneNumber: String, token: String) {
        successView.setMessage("Здравствуйте, $phoneNumber, Ваш токен $token")
        successView.visibility = View.VISIBLE
        errorView.hide()
        saveToken(token)
    }

    private fun onCodeError(error: ApiErrorResponse) {
        errorView.setMessage("Неверный код")
        errorView.visibility = View.VISIBLE
        successView.hide()
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun handleGetCodeError(error: ApiErrorResponse) {
        Toast.makeText(context, "Телефон должен совпадать с заданным форматом.", Toast.LENGTH_LONG)
            .show()
        Toast.makeText(
            context,
            "Переданного номера телефона нет в базе.",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun saveToken(token: String) {
        val sharedPrefs = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPrefs?.edit()?.apply() {
            putString("auth_token", token)
            apply()
        }
    }

    private fun requestNewCode() {
        requestCode() // Повторный запрос кода
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}