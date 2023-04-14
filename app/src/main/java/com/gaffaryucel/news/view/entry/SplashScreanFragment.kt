package com.gaffaryucel.news.view.entry

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.gaffaryucel.news.R
import com.gaffaryucel.news.databinding.FragmentSignInBinding
import com.gaffaryucel.news.databinding.FragmentSplashScreanBinding
import com.gaffaryucel.news.view.NewsNavigationActivity
import com.google.firebase.auth.FirebaseAuth


class SplashScreanFragment : Fragment() {
    private lateinit var binding : FragmentSplashScreanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreanBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_animation)
        binding.imageViewLogo.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }
            override fun onAnimationEnd(animation: Animation) {
                if (FirebaseAuth.getInstance().currentUser != null){
                    startActivity(Intent(requireContext(), NewsNavigationActivity::class.java))
                    requireActivity().finish()
                }else{
                    val action = SplashScreanFragmentDirections.actionSplashScreanFragmentToSignInFragment()
                    Navigation.findNavController(view).navigate(action)
                }
            }
            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}