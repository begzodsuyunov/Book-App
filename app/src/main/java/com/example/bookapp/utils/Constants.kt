package com.example.bookapp.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.bookapp.MainActivity

object Constants {

    fun goToPlayMarket(activity: MainActivity) {
        try {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://payme.uz/home/main")
                )
            )
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://payme.uz/home/main")
                )
            )
        }
    }

    fun share(context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Baby Puzzle")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "link: https://payme.uz/home/main"
        )
        context.startActivity(Intent.createChooser(intent, "UpTodo"))
    }
}