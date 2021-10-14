package com.test_app.utils

import android.content.Context
import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.progressindicator.CircularProgressIndicator

object ImageLoader {
    private const val BEFORE_RENDER_ARG = 16f
    private const val AFTER_RENDER_ARG = 1f

    @RequiresApi(Build.VERSION_CODES.S)
    private fun blur(x: Float, y: Float): RenderEffect =
        RenderEffect.createBlurEffect(
            x,
            y,
            Shader.TileMode.MIRROR
        )


    fun load(
        url: String,
        imageView: ImageView,
        root: View?,
        progressBar: CircularProgressIndicator
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            root?.setRenderEffect(blur(BEFORE_RENDER_ARG, BEFORE_RENDER_ARG))
        }
        Glide.with(imageView.context)
            .load(url)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean = false

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            root?.setRenderEffect(blur(AFTER_RENDER_ARG, AFTER_RENDER_ARG))
                        }
                        return false
                    }

                }
            )
            .into(imageView)
    }

    fun getDrawable(context: Context?, uri : String) =
        context?.let { Glide.with(it).load(uri).placeholderDrawable }

}