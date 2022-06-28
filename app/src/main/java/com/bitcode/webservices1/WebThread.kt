package com.bitcode.webservices1

import android.os.AsyncTask

class WebThread : AsyncTask<String, Any?, Any?>() {

    override fun doInBackground(vararg args : String?): Any? {
        //WebUtil.simpleHTTPRequest(args[0]!!)
        WebUtil.getUsers()
        return null
    }
}