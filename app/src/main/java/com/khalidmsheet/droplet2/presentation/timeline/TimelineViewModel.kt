package com.khalidmsheet.droplet2.presentation.timeline

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

class ThoughtsViewModel : ViewModel() {
    private val _state = MutableLiveData<List<ThoughtData>>()
    val state: LiveData<List<ThoughtData>> = _state

    init {
        getThoughtsState()
    }

    private fun getThoughtsState() {
        val db = FirebaseFirestore.getInstance()
        val temp = mutableListOf<ThoughtData>()
        try {
            db.collection("thoughts").orderBy("timestamp", Query.Direction.DESCENDING).get()
                .addOnSuccessListener { document ->
                    document?.documents?.map {
                        val obj = it.toObject<ThoughtData>()
                        var newThough: ThoughtData

                        obj?.run {
                            newThough = ThoughtData(
                                totalLikes = obj.totalLikes,
                                content = obj.content,
                                timestamp = obj.timestamp,
                                id = it.id,
                                username = obj.username,
                                photo = obj.photo,
                                uid = obj.uid
                            )
                            temp.add(newThough)
                        }

                    }
                    _state.value = temp
                }
        } catch (e: FirebaseFirestoreException) {
            Log.d("ERROR ->", e.toString())
        }
    }

}
