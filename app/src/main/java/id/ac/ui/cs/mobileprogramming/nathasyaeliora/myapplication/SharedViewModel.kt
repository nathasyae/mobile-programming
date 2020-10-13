package id.ac.ui.cs.mobileprogramming.nathasyaeliora.myapplication

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.nathasyaeliora.myapplication.model.Contact
import java.util.*


class SharedViewModel : ViewModel() {
    private var mutableContactList = MutableLiveData<MutableList<Contact>?>()
    private val mutableContact = MutableLiveData<Contact>()
    val contactList: LiveData<MutableList<Contact>?>
        get() {
            if (mutableContactList.value == null) {
                LoadContacts().execute()
            }
            return mutableContactList
        }
    val contact: LiveData<Contact>
        get() = mutableContact

    fun setContact(position: Int) {
        val contact = mutableContactList.value!![position]
        mutableContact.value = contact
    }

    fun addContact(contact: Contact) {
        if (mutableContactList.value != null) {
            val contactList = mutableContactList.value
            contactList!!.add(contact)
            mutableContactList.value = contactList
            return
        }
        val contactList: MutableList<Contact> = ArrayList()
        contactList.add(contact)
        mutableContactList.value = contactList
    }

    inner class LoadContacts : AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            val contactList: MutableList<Contact> = ArrayList()
            contactList.add(Contact("Polisi", "110"))
            contactList.add(Contact("Ambulans", "118"))
            contactList.add(Contact("Pemadam Kebakaran", "113"))
            contactList.add(Contact("Pencegahan Bunuh Diri", "500454"))
            mutableContactList.postValue(contactList)
        }
    }

    companion object {
        private const val TAG = "SharedViewModel"
    }
}