package com.bitcode.webservices1

import android.os.AsyncTask
import android.os.Handler
import android.os.Message

class WebThread(
    var handler: Handler?
) : AsyncTask<String, Any?, ArrayList<User>?>() {

    override fun doInBackground(vararg args : String?): ArrayList<User>? {
        //WebUtil.simpleHTTPRequest(args[0]!!)
        return WebUtil.getUsers()
    }

    override fun onPostExecute(result: ArrayList<User>?) {
        super.onPostExecute(result)

        var message =  Message()
        message.obj = result

        handler?.sendMessage(message)
    }
}