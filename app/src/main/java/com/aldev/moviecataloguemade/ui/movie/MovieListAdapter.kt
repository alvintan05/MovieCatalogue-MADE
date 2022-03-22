package com.aldev.moviecataloguemade.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.common.databinding.ItemMovieBinding
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieListAdapter :
    ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(TaskDiffCallBack()) {

    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =
        MovieListViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val data = getItem(position)
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
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    class TaskDiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
    
}