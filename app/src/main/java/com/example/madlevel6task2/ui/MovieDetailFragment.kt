package com.example.madlevel6task2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.FragmentMovieDetailBinding
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.viewmodel.MovieViewModel

/**
 * This class is used to display a detailed view of a movie that is received from the API.
 */
class MovieDetailFragment : Fragment() {

    private lateinit var movie: MovieItem
    private var _binding: FragmentMovieDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    // When the View is created, retrieve the list of movies.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRetrieveMovieResult()
    }

    // Used to safely switch between fragments.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Make use of bundles to retrieve the desired movie from the list of the MovieListFragment.
    private fun observeRetrieveMovieResult() {
        setFragmentResultListener(REQ_MOVIE_KEY) { _, bundle ->
            bundle.getParcelable<MovieItem>(BUNDLE_MOVIE_KEY)?.let {
                movie = it
                populateMovieDetailFragment()
            } ?: Log.e("MovieDetailFragment", "Request triggered, but empty movie!")
        }
    }

    // Put the components of the movie item into the correct places in the layout file.
    private fun populateMovieDetailFragment() {

        Glide.with(requireContext()).load(movie.getBackdropPathUrl()).into(binding.ivMovieBackdrop)
        Glide.with(requireContext()).load(movie.getPosterPathUrl()).into(binding.ivMoviePoster)
        binding.tvMovieTitle.text = movie.title
        binding.tvMovieReleaseDate.text = movie.releaseDate
        binding.tvMovieRating.text = getString(R.string.rating, movie.rating.toString())
        binding.tvMovieDescription.text = movie.overview

    }
}