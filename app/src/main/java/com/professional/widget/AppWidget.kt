package com.professional.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.professional.R
import com.professional.di.koin.RetrofitModule
import com.test_app.repository.cloud.CloudImpl
import com.test_app.repository.cloud.CloudSource
import com.test_app.repository.cloud.api.ServiceApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class AppWidget : AppWidgetProvider() {
    private val api: ServiceApi = RetrofitModule.provideTranslatorApi()
    private val scope = CoroutineScope(Dispatchers.IO)
    private val cloudSource: CloudSource by lazy {
        CloudImpl(api)
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val intent = Intent(context, AppWidget::class.java).let {
            it.action = WIDGET_ACTION
            PendingIntent.getBroadcast(context, REQUEST_CODE, it, PendingIntent.FLAG_IMMUTABLE)
        }
        appWidgetIds?.forEach {
            setRemoteView(context, intent, appWidgetManager, it)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == WIDGET_ACTION) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val appWidgetComponentName = ComponentName(context!!, AppWidget::class.java)
            setRemoteView(context, appWidgetManager, appWidgetComponentName)
        }
    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            Log.e("WidgetError", e.stackTraceToString())
            null
        }
    }

    private fun setRemoteView(
        context: Context?,
        intent: PendingIntent,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int
    ): RemoteViews {
        return RemoteViews(
            context?.packageName,
            R.layout.app_widget
        ).apply {
            setOnClickPendingIntent(R.id.image_widget, intent)
            scope.launch {
                val data = cloudSource.getData("Dog")
                setTextViewText(R.id.title_widget, data.first().text)
                val drawable = getBitmapFromURL(
                    "https:${data.first().meanings.random().imageUrl}"
                )
                setImageViewBitmap(
                    R.id.image_widget,
                    drawable
                )
                appWidgetManager?.updateAppWidget(appWidgetId, this@apply)
            }
        }
    }

    private fun setRemoteView(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetComponentName: ComponentName?
    ): RemoteViews {
        return RemoteViews(
            context?.packageName,
            R.layout.app_widget
        ).apply {
            scope.launch {
                val data = cloudSource.getData("Dog")
                setTextViewText(R.id.title_widget, data.first().text)
                val drawable = getBitmapFromURL(
                    "https:${data.first().meanings.random().imageUrl}"
                )
                Log.e("data", data.toString())
                setImageViewBitmap(
                    R.id.image_widget,
                    drawable
                )
                appWidgetManager?.updateAppWidget(appWidgetComponentName, this@apply)
            }
        }
    }

    companion object {
        private const val WIDGET_ACTION = "Translation_Widget_Action"
        private const val REQUEST_CODE = 100
    }
}