package pt.apt.minhaprova.acoes

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.content.DialogInterface
import android.widget.Toast

class Dialog: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Gostaria de uma xícara de café?")
                .setPositiveButton("Sim",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(it.baseContext, "Ótimo", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("Não",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(it.baseContext, "Fica para a próxima", Toast.LENGTH_SHORT).show()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}