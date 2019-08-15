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
import com.example.project_auth.presentation.controllers.RegisterViewController
import com.example.project_auth.presentation.presenters.RegisterPresenter

class RegisterFragment : Fragment(), RegisterViewController {

    private lateinit var avRegisterView: AuthView
    private lateinit var mRegisterPresenter: RegisterPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mRegisterPresenter = RegisterPresenter(this, activity!!.application)
        mRegisterPresenter.init()

        avRegisterView = view.findViewById(R.id.av_register_view_fragment_register)
        avRegisterView.invokeRegisterMode(
                savedInstanceState,
                this::onRegisterClick,
                this::onMessageClick)

    }

    private fun onRegisterClick(userName: String, firstName: String,
                                lastName: String, password: String) {
        mRegisterPresenter.onRegisterClick(userName, firstName, lastName, password)
    }

    private fun onMessageClick() {
        Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                .navigate(R.id.action_destination_register_to_destination_login)
    }

    override fun onRegisterSuccess(user: User) {
        Toast.makeText(this.context, "Success!", Toast.LENGTH_SHORT).show()
    }

    override fun onRegisterPending() {
        Toast.makeText(this.context, "Loading", Toast.LENGTH_SHORT).show()
    }

    override fun onAlreadyRegistered() {
        Toast.makeText(this.context, "Already Registered", Toast.LENGTH_SHORT).show()
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this.context, throwable.message, Toast.LENGTH_SHORT).show()
    }
}
