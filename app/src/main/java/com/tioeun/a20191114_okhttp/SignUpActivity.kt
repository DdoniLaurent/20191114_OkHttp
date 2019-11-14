package com.tioeun.a20191114_okhttp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tioeun.a20191114_okhttp.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.idEdt
import kotlinx.android.synthetic.main.activity_login.pwEdt
import kotlinx.android.synthetic.main.activity_login.signUpBtn
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        setupEvents()
        setValues()

    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            var inputId = idEdt.text.toString()
            var inputPw = pwEdt.text.toString()
            var inputName = nameEdt.text.toString()
            var inputPhone = phoneEdt.text.toString()
            ServerUtil.putRequestSignUp(mContext, inputId, inputPw, inputName, inputPhone, object: ServerUtil.JonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    runOnUiThread {
                        Log.d("액티비티에서 보는 응답", json.toString())

                        var code = json.getInt("code")
                        if(code == 400) {
                            val message = json.getString("message")
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        } else if(code == 200) {

                            Toast.makeText(mContext, "회원가입 성공!!!!!!!!!!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }

    }

    override fun setValues() {

    }


}
