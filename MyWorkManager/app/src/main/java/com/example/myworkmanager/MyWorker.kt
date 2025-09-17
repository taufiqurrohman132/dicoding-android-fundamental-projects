package com.example.myworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    private var resultStatus: Result? = null

    companion object {
        private val TAG = MyWorker::class.java.simpleName
        const val APP_ID = "2cad5d7823a59992726224d3ac0aed72"
        const val EXTRA_CITY = "city"
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME = "dicoding channel"
    }

    // berjalan di bg tred otomatis
    override fun doWork(): Result {
        Log.d(TAG, "doWork: jalan")
        val dataCity = inputData.getString(EXTRA_CITY)
        return getCurrentWeather(dataCity)

    }

    private fun showNotification(title: String, message: String?) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val chanel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notification.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(chanel)
        }

        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }

    private fun getCurrentWeather(city: String?): Result {
        Log.d(TAG, "getCurrentWeather: Mulai.....")

        Looper.prepare()
        val client = SyncHttpClient()
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=${BuildConfig.APP_ID}"
        Log.d(TAG, "getCurrentWeather: $url")

        client.post(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    val currentWeather: String = responseObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val description: String = responseObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val tempInKelvin = responseObject.getJSONObject("main").getDouble("temp")
                    val tempInCelsius = tempInKelvin - 273
                    val temperature: String = DecimalFormat("##.##").format(tempInCelsius)
                    val title = "Current Weather in $city"
                    val message = "$currentWeather, $description with $temperature celsius"
                    showNotification(title, message)
                    Log.d(TAG, "onSuccess: Selesai.....")
                    resultStatus = Result.success()
                }catch (e: Exception){
                    showNotification("Get Current Weather Not Success", e.message)
                    Log.d(TAG, "onSuccess: Gagal.....")
                    resultStatus = Result.failure()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.d(TAG, "onFailure: Gagal.....")
                // ketika proses gagal, maka jobFinished diset dengan parameter true. Yang artinya job perlu di reschedule
                showNotification("Get Current Weather Failed", error?.message)
                resultStatus = Result.failure()
            }

        })
        return resultStatus as Result

    }

}