package xoulis.xaris.com.spamfree

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.constraint.Group
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.custom_edit_text_dialog.*
import xoulis.xaris.com.spamfree.util.CustomDialogHelper

/* Views */
fun View.enableView(enable: Boolean) {
    isEnabled = enable
    isClickable = enable
    alpha = if (enable) 1.0f else 0.5f
}

fun View.showSnackBar(messageResId: Int, shortDuration: Boolean = true) {
    val duration = if (shortDuration) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    Snackbar.make(this, messageResId, duration).show()
}

fun Group.setAllOnClickListeners(listener: View.OnClickListener) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

/* CustomDialogHelper */
inline fun Context.getDialog(
    title: String,
    text: String = "",
    inputType: Int = InputType.TYPE_CLASS_TEXT,
    filter: InputFilter? = null,
    autoDismiss: Boolean = true,
    f: CustomDialogHelper.() -> Unit
): AlertDialog =
    CustomDialogHelper(this, title, text, inputType, filter, autoDismiss).apply {
        f()
    }.create()


/* Network */
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

/* Request's response */
fun String.decodeRequestResponseMessage(): Int {
    return when (this) {
        "0" -> R.string.inexistent_code
        "2" -> R.string.existing_req
        "3" -> R.string.existing_chat
        else -> R.string.req_code_unusable
    }
}