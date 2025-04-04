package com.kick.mp25_assign3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    var name: String,
    var email: String,
    var password: String,
    var information: String
) : Parcelable
