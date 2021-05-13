package com.example.madlevel6task2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository = MovieRepository()

    /**
     * This property points directly to the LiveData in the repository, that value
     * is updated when the user clicks on the FAB. This happens through the refreshNumber() in this class :)
     */
    val movie = movieRepository.movie

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val errorText: LiveData<String>
        get() = _errorText

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library.
     */
    fun getMovieList() {
        viewModelScope.launch {
            try {
                // The movieRepository sets its own liveData property.
                // Our own movie property points to this one.
                movieRepository.getMovieList()
            } catch (error: MovieRepository.MovieRefreshError) {
                Log.e("Movie error", error.cause.toString())
            }
        }
    }
}