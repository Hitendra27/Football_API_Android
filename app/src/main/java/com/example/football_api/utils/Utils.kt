package com.example.football_api.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImagefromUrl(imageUrl: String) {
    Picasso.with(context).load(imageUrl).into(this);
}

