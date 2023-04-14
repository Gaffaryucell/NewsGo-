package com.gaffaryucel.news.view.entry

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gaffaryucel.news.R
import com.gaffaryucel.news.databinding.FragmentSignInBinding
import com.gaffaryucel.news.databinding.FragmentSignUpBinding
import com.gaffaryucel.news.view.NewsNavigationActivity


class SignUpFragment : Fragment() {

    lateinit var viewmodel : EntryViewModel
    lateinit var binding : FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(EntryViewModel::class.java)
        binding.gotosignInText.setOnClickListener {
            findNavController().popBackStack()
        }
        observeLiveData()
        binding.signInButton.setOnClickListener {
            var email = binding.emailText.text.toString()
            var pass1 = binding.passwordText.text.toString()
            var pass2 = binding.passwordText2.text.toString()
            viewmodel.signUp(email,pass1,pass2)
        }
    }
    fun observeLiveData(){
        viewmodel.enter.observe(viewLifecycleOwner, Observer {
            if(it){
                startActivity(Intent(requireContext(), NewsNavigationActivity::class.java))
                requireActivity().finish()
            }
        })
        viewmodel.loading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.INVISIBLE
            }
        })
        viewmodel.error.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.errorText.visibility = View.VISIBLE
            }else{
                binding.errorText.visibility = View.INVISIBLE
            }
        })
        viewmodel.errormessage.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                binding.errorText.text = it
            }
        })
    }

}