package com.gaffaryucel.news.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.gaffaryucel.news.R
import com.gaffaryucel.news.adapter.NewsDetailsAdapter
import com.gaffaryucel.news.databinding.FragmentCategoriesBinding
import com.gaffaryucel.news.databinding.FragmentDetailsBinding
import com.gaffaryucel.news.model.User
import com.gaffaryucel.news.viewmodel.NewsViewModel
import kotlinx.coroutines.runBlocking

class CategoriesFragment : DialogFragment() {
    private lateinit var binding : FragmentCategoriesBinding
    private lateinit var viewModel : NewsViewModel
    private var category = ""
    private val categories = arrayOf("select a category",
        "animals-farmed", "artanddesign", "australia-news", "World news", "UK news",
        "Politics", "Football", "Sports", "Technology", "Environment", "Business",
        "Culture", "Lifestyle", "Travel", "Opinion", "Education", "Health", "Society",
        "Media", "Law", "Science","other"
    )
    val forApiTagList = arrayListOf(
        "select a category",
        "technology/technology",
        "science/science",
        "business/business",
        "world/world",
        "sport/sport",
        "culture/culture",
        "lifeandstyle/lifeandstyle",
        "environment/environment",
        "politics/politics",
        "commentisfree/commentisfree",
        "media/media",
        "society/society",
        "education/education",
        "uk-news/uk-news",
        "us-news/us-news",
        "australia-news/australia-news",
        "global-development/global-development",
        "global/global",
        "cities/cities",
        "stage/stage",
        "tv-and-radio/tv-and-radio",
        "books/books",
        "music/music",
        "film/film",
        "artanddesign/artanddesign",
        "football/football",
        "cricket/cricket",
        "rugby-union/rugby-union",
        "formulaone/formulaone",
        "tennis/tennis",
        "golf/golf",
        "boxing/boxing",
        "cycling/cycling",
        "us-sports/us-sports",
        "horse-racing/horse-racing",
        "travel/travel",
        "money/money",
        "fashion/fashion",
        "food/food",
        "health-and-wellbeing/health-and-wellbeing",
        "home-and-garden/home-and-garden",
        "family/family",
        "technology-archive/technology-archive",
        "film-archive/film-archive",
        "music-archive/music-archive",
        "media-archive/media-archive",
        "society-archive/society-archive",
        "education-archive/education-archive",
        "politics-archive/politics-archive",
        "sport-archive/sport-archive",
        "books-archive/books-archive",
        "art-archive/art-archive",
        "stage-archive/stage-archive",
        "lifeandstyle-archive/lifeandstyle-archive",
        "environment-archive/environment-archive",
        "culture-archive/culture-archive"
    )
    val forUserTagList = arrayListOf(
        "select a category",
        "technology",
        "science",
        "business",
        "world",
        "sport",
        "culture",
        "lifeandstyle",
        "environment",
        "politics",
        "commentisfree",
        "media",
        "society",
        "education",
        "uk-news",
        "us-news",
        "australia-news",
        "global-development",
        "global",
        "cities",
        "stage",
        "tv-and-radio",
        "books",
        "music",
        "film",
        "artanddesign",
        "football",
        "cricket",
        "rugby-union",
        "formulaone",
        "tennis",
        "golf",
        "boxing",
        "cycling",
        "us-sports",
        "horse-racing",
        "travel",
        "money",
        "fashion",
        "food",
        "health-and-wellbeing",
        "home-and-garden",
        "family",
        "technology-archive",
        "film-archive",
        "music-archive",
        "media-archive",
        "society-archive",
        "education-archive",
        "politics-archive",
        "sport-archive",
        "books-archive",
        "art-archive",
        "stage-archive",
        "lifeandstyle-archive",
        "environment-archive",
        "culture-archive"
    )
    var tagsforuser = ""
    var tagsforapi = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        val view = binding.root
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        var tags = viewModel.newsTags.value
        spin()
        tagSpin()
        binding.selectCategoryButton.setOnClickListener{
            val myText = binding.categoryText.text.toString()
            if (category.isNotEmpty()){
                if (category == "other"){
                    viewModel.deleteCategory()
                    viewModel.changeCategory(User(myText))
                    dialog!!.dismiss()
                } else{
                    if (!binding.categoryText.isEnabled){
                        viewModel.deleteCategory()
                        viewModel.changeCategory(User(category))
                        dialog!!.dismiss()
                    }else{
                        viewModel.deleteCategory()
                        viewModel.changeCategory(User(myText))
                        dialog!!.dismiss()
                    }
                }
            }else{
                Toast.makeText(requireContext(), "you have to select any TAG and Category", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun spin(){
        // ArrayAdapter ile kategorileri Spinner'a ekleyin
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = adapter

        // Spinner'a bir öğe seçildiğinde ne yapılacağını belirleyin
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCategory = parent.getItemAtPosition(position).toString()
                category = ""
                var button = binding.selectCategoryButton
                when (selectedCategory) {
                        "select one category" -> {
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "animals-farmed" -> {
                            category = "animals-farmed"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                            button.requestFocus()
                        }
                        "artanddesign" -> {
                            category = "artanddesign"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "australia-news" -> {
                            category = "australia-news"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "World-news" -> {
                            category = "World news"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "UK news" -> {
                            category = "UK news"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Politics" -> {
                            category = "Politics"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Football" -> {
                            category = "Football"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Sports" -> {
                            category = "Sports"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Technology" -> {
                            category = "Technology"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Environment" -> {
                            category = "Environment"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Business" -> {
                            category = "Business"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Culture" -> {
                            category = "Culture"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Lifestyle" -> {
                            category = "Lifestyle"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Travel" -> {
                            category = "Travel"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Opinion" -> {
                            category = "Opinion"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Education" -> {
                            category = "Education"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Health" -> {
                            category = "Health"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Society" -> {
                            category = "Society"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Media" -> {
                            category = "Media"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Law" -> {
                            category = "animals-farmed"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }
                        "Science" -> {
                            category = "animals-farmed"
                            binding.categoryText.isEnabled = false
                            binding.categoryText.hint = "Category"
                        }"other" -> {
                            category = "other"
                            binding.categoryText.isEnabled = true
                        }
                    }
                if (selectedCategory != "select one category"){
                    binding.selectCategoryButton.isEnabled = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            // Hiçbir şey seçilmediğinde yapılacak işlemler
            }
        }
    }
    fun tagSpin(){
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, forUserTagList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.tagSpiner.adapter = adapter

        // Spinner'a bir öğe seçildiğinde ne yapılacağını belirleyin
        binding.tagSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedTag = parent.getItemAtPosition(position).toString()

                if (selectedTag == parent.getItemAtPosition(0).toString()){

                }else{
                    category = "$selectedTag ${forApiTagList.get(position).substringBefore("/")}"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
            }
        }

    }
}