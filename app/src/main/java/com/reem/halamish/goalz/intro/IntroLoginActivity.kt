package com.reem.halamish.goalz.intro

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.reem.halamish.goalz.R
import com.reem.halamish.goalz.models.UserDetails
import kotlinx.android.synthetic.main.activity_intro_login.*

class IntroLoginActivity : AppCompatActivity() {

//    val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//    lateinit var googleClient: GoogleSignInClient
//
//    fun signIn() {
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_login)

        val viewModel = ViewModelProviders.of(this).get(IntroViewModel::class.java)

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
    }
}
