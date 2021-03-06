package xoulis.xaris.com.spamfree.util

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import xoulis.xaris.com.spamfree.R

class CustomDialogHelper(
    context: Context,
    val title: String,
    val message: String,
    private val inputType: Int,
    private val filter: InputFilter?,
    private val autoDismiss: Boolean
) {

    private val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.custom_edit_text_dialog, null)
    }
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)
    private lateinit var dialog: AlertDialog

    private val titleTextView by lazy {
        dialogView.findViewById<TextView>(R.id.custom_dialog_title)
    }
    private val inputLayout by lazy {
        dialogView.findViewById<TextInputLayout>(R.id.custom_dialog_textInputLayout)
    }
    private val editText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.custom_dialog_message)
    }
    private val okButton: Button by lazy {
        dialogView.findViewById<Button>(R.id.custom_dialog_ok_button)
    }

    fun create(): AlertDialog {
        titleTextView.text = title
        editText.apply {
            inputType = this@CustomDialogHelper.inputType
            if (filter != null) {
                filters = arrayOf(this@CustomDialogHelper.filter)
            }
            append(message)
        }
        dialog = builder.create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        return dialog
    }

    fun setOkButtonClickListener(f: (userInput: String) -> (Unit)) {
        okButton.setOnClickListener {
            f(editText.text.toString())
            if (autoDismiss) {
                dialog.dismiss()
            }
        }
    }

    fun enableOkButton(enableView: Boolean) {
        okButton.enableView(enableView)
    }

    fun setTextInputLayoutError(errorString: String?) {
        inputLayout.error = errorString
    }

    fun setEditTextWatcher(f: (userInput: String) -> Unit) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                f(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}