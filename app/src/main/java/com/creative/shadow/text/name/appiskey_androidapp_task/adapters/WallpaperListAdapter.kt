package com.creative.shadow.text.name.appiskey_androidapp_task.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.creative.shadow.text.name.appiskey_androidapp_task.databinding.ItemWalpaperListBinding
import com.creative.shadow.text.name.appiskey_androidapp_task.koin.moduleList
import com.creative.shadow.text.name.appiskey_androidapp_task.model.WallpaperModel2


class WallpaperListAdapter(
    private val context: Context,
    var goTONext: (url:String) -> Unit
) :
    RecyclerView.Adapter<WallpaperListAdapter.HolderClass>() {
    private var listHomeFeatures = ArrayList<WallpaperModel2>()


    fun setConversationList(conversation:  ArrayList<WallpaperModel2>) {
        this.listHomeFeatures.clear()
        this.listHomeFeatures.addAll(conversation)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HolderClass {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemWalpaperListBinding =
            ItemWalpaperListBinding.inflate(layoutInflater, parent, false)
        return HolderClass(binding)
    }

    inner class HolderClass(private val binding: ItemWalpaperListBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(currentItemObj: WallpaperModel2, position: Int) {
            with(binding) {
              //   Glide.with(context).load(currentItemObj.url).into(wallpaperImg)
               tvUserName.text=currentItemObj.user
               tvLikes.text=currentItemObj.like.toString()

                val tags = listOf(currentItemObj.tags)
                val tagsText = tags.joinToString(", ")
               tvTags.text=tagsText


                try {

                    Glide.with(root.context).load(currentItemObj.url)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                progress.visibility = View.VISIBLE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                progress.visibility = View.GONE
                                return false
                            }

                        }).into(wallpaperImg)

                } catch (_: Exception) {
                }
            }
            itemView.setOnClickListener {
                goTONext(currentItemObj.url)
            }
        }
    }

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
        val currentItem = listHomeFeatures[position]
        holder.bind(currentItem, position)
    }

    override fun getItemCount(): Int {
        return listHomeFeatures.size
    }
}


