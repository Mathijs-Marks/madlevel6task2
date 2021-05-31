package com.example.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.FragmentMovieListBinding
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.viewmodel.MovieViewModel
import java.lang.Math.floor
import androidx.fragment.app.setFragmentResult

/**
 * This class makes use of the RecyclerView to display a list of movies.
 * To retrieve the list, input a year into the editTextField and press the submit button.
 */

const val REQ_MOVIE_KEY = "req_movie"
const val BUNDLE_MOVIE_KEY = "bundle_movie"

class MovieListFragment : Fragment() {

    private val movies = arrayListOf<MovieItem>()
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: MovieViewModel by activityViewModels()
    private var _binding: FragmentMovieListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root

    }

    // When the view is created, set up the RecyclerView in a Grid layout.
    // On clicking the submit button, retrieve the list of movies from the API and populate them in the RecyclerView.
    // When switching between vertical and horizontal layout, the Grid layout is adjusted accordingly.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)

        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        binding.rvMovies.layoutManager = gridLayoutManager
        binding.rvMovies.viewTreeObserver.addOnGlobalLayoutListener(object :
        ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.rvMovies.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })
        binding.rvMovies.adapter = movieAdapter

        binding.btnSubmit.setOnClickListener {

            observeMovies()
        }
    }

    // Used to safely switch between fragments.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Upon clicking a movie item, navigate towards the MovieDetailFragment.
    private fun onMovieClick(movieItem: MovieItem) {
        onRetrieveMovie(movieItem)
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
    }

    // Calculate the size of the Grid layout for the vertical and horizontal views.
    private fun calculateSpanCount(): Int {
        val viewWidth = binding.rvMovies.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.poster_width)
        val cardViewMargin = resources.getDimension(R.dimen.margin_medium)
        val spanCount = floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 1) spanCount else 1
    }

    // Tell the ViewModel to retrieve the list of movies from the API, and populate them in the RecyclerView.
    private fun observeMovies() {

        viewModel.getMovieList(binding.etMovieYear.text.toString().toInt())

        viewModel.movieList.observe(viewLifecycleOwner, {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()

        })
    }

    // When clicking a movie item, create a bundle of the clicked item, and send it to the other fragment.
    private fun onRetrieveMovie(movieItem: MovieItem) {
        val movie = movieItem
        setFragmentResult(REQ_MOVIE_KEY, bundleOf(Pair(BUNDLE_MOVIE_KEY, movie)))
    }
}