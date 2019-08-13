package com.example.project_auth.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project_auth.R;
import com.example.project_auth.model.User;
import com.example.project_auth.presentation.LoginPresenter;
import com.example.project_auth.presentation.LoginViewController;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements LoginViewController {

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginPresenter = new LoginPresenter(this, this.getApplication());
        mLoginPresenter.init();
    }

    @Override
    public void onLoginSuccess(@NotNull User user) {

    }

    @Override
    public void showLoading(@NotNull String message) {

    }

    @Override
    public void onLoginFailed(@Nullable Throwable throwable) {

    }
}
