package com.aldev.moviecataloguemade.favorite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldev.moviecataloguemade.common.constant.CommonConstant
import com.aldev.moviecataloguemade.common.databinding.ItemMovieBinding
import com.aldev.moviecataloguemade.core.domain.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteListAdapter : RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {

    private var listFavorite = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listFavorite.clear()
        listFavorite.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listFavorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val data = listFavorite[position]
        holder.bindItem(data)
    }

    inner class FavoriteViewHolder(private val binding: ItemMovieBinding) :
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
                onItemClick?.invoke(listFavorite[absoluteAdapterPosition])
            }
        }
    }
}