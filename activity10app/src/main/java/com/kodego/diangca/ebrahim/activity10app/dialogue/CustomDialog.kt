package ph.kodego.aragon.janreign.module_2.dialogue

import android.app.Dialog
import android.content.Context

class CustomDialog (context: Context) :Dialog(context) {

    init {
        dialog = Dialog(context)
    }

    companion object{

    var dialog: Dialog? = null

 }
}


