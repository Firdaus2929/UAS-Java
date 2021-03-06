package com.informatika.firdaushermawan_19100018_kalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.informatika.firdaushermawan_19100018_kalkulator.model.ResponseAdmin
import com.informatika.firdaushermawan_19100018_kalkulator.service.SessionPreferences
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionPreferences: SessionPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_login)
        btn_submit.setOnClickListener {
            val userName = et_username.text.toString()
            val password = et_password.text.toString()

            if (userName.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Form tidak boleh kosong!", Toast.LENGTH_LONG).show()
            }else{
                actionData(userName, password)
            }
        }
        btn_clean.setOnClickListener {
            formClear()
        }
        tv_disini.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
    fun actionData(Username : String, Password : String){
        koneksi.service.logIn(Username, Password).enqueue(object : Callback<ResponseAdmin> {
            override fun onFailure(call: Call<ResponseAdmin>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(call: Call<ResponseAdmin>, response: Response<ResponseAdmin>) {
                if (response.isSuccessful){
                    val resbody = response.body()
                    val resStatus = resbody?.status
                    val resUserName = resbody?.data?.get(0)?.username
                    Log.d("pesan", resUserName.toString())
                    if (resStatus == true){
                        sessionPreferences = SessionPreferences(this@LoginActivity)
                        sessionPreferences.actionLogin(resUserName.toString())
                        val i = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(i)
                        finish()
                    }else if (resStatus == false){
                        Toast.makeText(this@LoginActivity, "Username atau Password Anda Salah!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
    fun formClear(){
        et_username.text.clear()
        et_password.text.clear()
    }
}