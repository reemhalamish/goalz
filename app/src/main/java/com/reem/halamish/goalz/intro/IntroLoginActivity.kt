package com.reem.halamish.goalz.intro

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.facebook.login.LoginManager
import com.reem.halamish.goalz.R
import com.reem.halamish.goalz.auth.FirebaseAuthManager
import com.reem.halamish.goalz.models.UserDetails
import kotlinx.android.synthetic.main.activity_intro_login.*


class IntroLoginActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_login)

        val viewModel = IntroViewModel.get(this)
        viewModel.userDetails.observe(this, Observer { it?.let {
            textView.text = it.name
        } })

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
                string?.toString()?.let { viewModel.setDetails(UserDetails(it)) }
            }
        })

        viewModel.connectedUser.observe(this, Observer { it?.let {
            tvLoginStatus.text = it.displayName + " " + it.providers
        } ?: run{
            tvLoginStatus.text = "no connected user!"
        }})

        viewModel.connectedUser.observe(this, Observer {
            val isConnected = it != null

            if (isConnected) {
                fbButton.text = "DISCONNECT"
                fbButton.setOnClickListener { logout() }
            } else {
                fbButton.text = "CONNECT"
                fbButton.setOnClickListener { facebookLogin() }
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        IntroViewModel.get(this).onActivityResult(requestCode, resultCode, data)
    }

    fun facebookLogin() {
        LoginManager.getInstance()?.logInWithReadPermissions(this, arrayListOf("email", "public_profile"))
    }

    fun logout() {
        FirebaseAuthManager.instance.logout()
    }
}
