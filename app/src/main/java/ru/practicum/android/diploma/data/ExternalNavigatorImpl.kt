package ru.practicum.android.diploma.data

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.api.ExternalNavigator

class ExternalNavigatorImpl(val context: Context) : ExternalNavigator {
    var listException: ArrayList<in Throwable> = arrayListOf()
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
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        listException.clear()
        try {
            context.startActivity(emailIntent)
        } catch (activityNotFound: ActivityNotFoundException) {
            listException.add(activityNotFound)
        }
    }

    override fun openDial(number: String?) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.apply {
            data = Uri.parse("tel:$number")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(dialIntent)
    }

    override fun getExceptionsList(): Int = listException.size
}
