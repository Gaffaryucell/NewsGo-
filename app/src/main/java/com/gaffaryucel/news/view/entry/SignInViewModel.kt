package com.gaffaryucel.news.view.entry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class EntryViewModel : ViewModel(){
    private val auth = FirebaseAuth.getInstance()
    var enter = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    var errormessage = MutableLiveData<String>()
    fun signIn(email : String,password : String){
        loading.value = true
        if (email.isNotEmpty()&&password.isNotEmpty()){
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                if (it.isSuccessful){
                    enter.value = true
                    loading.value = false
                }else{
                    errormessage.value = "error: try again later"
                    loading.value = false
                    error.value = true
                }
            }
        }else{
            errormessage.value = "E-mail or password cannot be empty"
            loading.value = false
        }
    }
    fun signUp(email : String,password : String,password2 : String){
        loading.value = true
        if (email.isNotEmpty()&&password.isNotEmpty()){
            if (password == password2){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        enter.value = true
                        loading.value = false
                    }else{
                        errormessage.value = "error: try again later"
                        loading.value = false
                        error.value = true
                    }
                }
            }else{
                errormessage.value = "passwords mismatch"
            }
        }else{
            errormessage.value = "E-mail or password cannot be empty"
        }
    }
}