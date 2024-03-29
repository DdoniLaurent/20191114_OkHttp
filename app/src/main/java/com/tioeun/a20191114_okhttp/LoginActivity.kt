package com.tioeun.a20191114_okhttp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tioeun.a20191114_okhttp.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvents()
        setValues()

//        ServerUtil.postRequestLogin(mContext, "cho881020", "qlalfqjsgh!", null)

    }

    override fun setupEvents() {

        signUpBtn.setOnClickListener {
            var intent = Intent(mContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            var inputId = idEdt.text.toString()
            var inputPw = pwEdt.text.toString()
            ServerUtil.postRequestLogin(mContext, inputId, inputPw, object: ServerUtil.JonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    runOnUiThread {
                        Log.d("액티비티에서 보는 응답", json.toString())

                        var code = json.getInt("code")
                        if(code == 400) {
                            val message = json.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        } else if(code == 200) {
//                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                            var dataObject = json.getJSONObject("data")

                            val userObject = dataObject.getJSONObject("user")

                            val userName = userObject.getString("name")

                            Toast.makeText(mContext, "${userName}님 환영합니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }

    override fun setValues() {

    }


}
