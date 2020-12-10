/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.example.architectures.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.concurrent.thread


open class LocalDataSource(application: Application): LifecycleObserver {


  private val movieDao: MovieDao

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  fun getAllMvovies(): List<Movie> {
        var list: List<Movie>
        val job =  GlobalScope.async {
            movieDao.allMovies()
          }
        runBlocking {
          list =  job.await()
        }
    Log.d("aa","asdasdasdas")
        return list
  }

  init {
    val db = LocalDatabase.getInstance(application)
    movieDao = db.movieDao()
  }

  fun insert(movie: Movie) {
    GlobalScope.launch(Dispatchers.IO) {
      movieDao.insert(movie)
    }
  }

  fun delete(movie: Movie) {
    GlobalScope.launch(Dispatchers.IO) {
      movieDao.delete(movie.id)
    }
  }

  fun update(movie: Movie) {
    GlobalScope.launch(Dispatchers.IO) {
      movieDao.update(movie)
    }
  }

}