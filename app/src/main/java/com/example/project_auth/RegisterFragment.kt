package com.example.project_auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.project_auth.view.AuthView

class RegisterFragment : Fragment() {

    private lateinit var avRegisterView: AuthView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avRegisterView = view.findViewById(R.id.av_register_view_fragment_register)
        avRegisterView.invokeRegisterMode(
                savedInstanceState,
                this::onRegisterClick,
                this::onMessageClick)

    }

    private fun onRegisterClick(userName: String, firstName: String,
                                lastName: String, password: String) {

    }

    private fun onMessageClick() {
        Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                .navigate(R.id.action_destination_register_to_destination_login)
    }

}
