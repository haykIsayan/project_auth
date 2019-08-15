package com.example.project_auth.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import com.example.project_auth.R
import com.example.project_auth.model.User
import com.example.project_auth.presentation.LoginPresenter
import com.example.project_auth.presentation.LoginViewController

class LoginFragment : Fragment(), LoginViewController {

    private lateinit var avLoginView: AuthView
    private lateinit var mLoginPresenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLoginPresenter = LoginPresenter(this, activity!!.application)
        mLoginPresenter.init()

        avLoginView = view.findViewById(R.id.av_login_view_fragment_login)
        avLoginView.invokeLoginMode(
                savedInstanceState,
                this::onLoginClicked,
                this::navigateToRegister)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::avLoginView.isInitialized) {
            outState.putAll(avLoginView.getBundle())
        }
    }

    private fun onLoginClicked(userName: String, password: String) {
        mLoginPresenter.onLoginClick(userName, password)
    }

    private fun navigateToRegister() {
        Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                .navigate(R.id.action_destination_login_to_destination_register2)
    }


    override fun onLoginSuccess(user: User) {

        Toast.makeText(this.context, "Success!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(message: String) {
        Toast.makeText(this.context, "Pending", Toast.LENGTH_SHORT).show()

    }

    override fun onLoginFailed(throwable: Throwable?) {
        Toast.makeText(this.context, "Fuck", Toast.LENGTH_SHORT).show()
    }

}
