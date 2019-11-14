package com.tioeun.a20191114_okhttp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var mContext : Context = this

    abstract fun setupEvents()

    abstract fun setValues()
}