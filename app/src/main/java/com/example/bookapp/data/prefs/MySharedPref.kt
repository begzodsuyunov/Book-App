package com.example.bookapp.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.bookapp.utils.SharedPreference
import javax.inject.Inject

class MySharedPref @Inject constructor(
    ctx: Context,
    sharedPref: SharedPreferences
) : SharedPreference(ctx, sharedPref) {

    var name: String by Strings("")

    var id: String by Strings("")

    var image: String by Strings("")

    var password: String by Strings("")
}