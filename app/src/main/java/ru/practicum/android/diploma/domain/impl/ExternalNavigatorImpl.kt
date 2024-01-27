package ru.practicum.android.diploma.domain.impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.practicum.android.diploma.domain.ExternalNavigator

class ExternalNavigatorImpl(val context: Context): ExternalNavigator {
    override fun shareLink(url: String?) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
        }
        context.startActivity(
            Intent.createChooser(shareIntent, url)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    override fun openEmail(email: String?) {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(emailIntent)
    }

    override fun openDial(number: String?) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.apply {
            data = Uri.parse("tel:$number")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(dialIntent)
    }
}
