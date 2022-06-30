package com.bitcode.webservices1

import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WebUtil {

    companion object {

        fun simpleHTTPRequest(targetUrl: String) {

            var url = URL(targetUrl)
            var httpsUrlConnection = url.openConnection() as HttpsURLConnection

            httpsUrlConnection.connect()

            mt("Response code: ${httpsUrlConnection.responseCode}")
            mt("Response message: ${httpsUrlConnection.responseMessage}")
            mt("Content Length: ${httpsUrlConnection.contentLength}")
            mt("Encoding: ${httpsUrlConnection.contentEncoding}")


            var inStream = httpsUrlConnection.inputStream

            var data = ByteArray(1024 * 1)
            var count = 0
            var buffer = StringBuffer()

            count = inStream.read(data)
            while (count != -1) {
                buffer.append(String(data, 0, count))
                count = inStream.read(data)
            }
            inStream.close()

            Log.e("tag", buffer.toString())

        }

        private fun mt(text: String) {
            Log.e("tag", text)
        }

        fun getUsers(): ArrayList<User>? {
            var url = URL("https://reqres.in/api/users?page=1")
            var httpURLConnection = url.openConnection() as HttpURLConnection

            httpURLConnection.connect()

            Log.e("tag", "Res code ${httpURLConnection.responseCode}")

            if (httpURLConnection.responseCode != 200) {
                return null
            }

            var responseBuffer = StringBuffer()

            var count = 0
            var data = ByteArray(1024 * 1)
            var inStream = httpURLConnection.inputStream

            count = inStream.read(data)
            while (count != -1) {
                responseBuffer.append(String(data, 0, count))
                count = inStream.read(data)
            }
            inStream.close()

            mt(responseBuffer.toString())

            var jsonRoot = JSONObject(responseBuffer.toString())

            if (jsonRoot.has("page")) {
                mt("page: ${jsonRoot.getInt("page")}")
            }
            mt("total pages: ${jsonRoot.getInt("total_pages")}")

            var jUsersArray = jsonRoot.getJSONArray("data")
            var users = ArrayList<User>()

            for (i in 0..jUsersArray.length() - 1) {
                var jUser = jUsersArray.getJSONObject(i)
                users.add(
                    User(
                        jUser.getInt("id"),
                        jUser.getString("email"),
                        jUser.getString("first_name"),
                        jUser.getString("last_name"),
                        jUser.getString("avatar")
                    )
                )
            }

            return users
        }

    }


}