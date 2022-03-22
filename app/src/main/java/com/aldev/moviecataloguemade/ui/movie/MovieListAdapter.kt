package com.aldev.moviecataloguemade.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.common.databinding.ItemMovieBinding
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listMovie.clear()
        listMovie.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bindItem(data)
    }

    inner class MovieListViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: Movie) {
            binding.apply {
                tvItemTitle.text = item.title
                tvItemRating.text = item.voteAverage.toString()
                tvItemDesc.text = item.overview

                Glide.with(root)
                    .load(CommonConstant.posterUrlPath + item.posterPath)
                    .apply(RequestOptions())
                    .into(binding.imgItemPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listMovie[adapterPosition])
            }
        }
    }
}