/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty

// Create adapter class to bind data to our recyclerview
// We are extending RecyclerView ListAdapter
// So we pass in list item type (MarsProperty), the viewholder(MarsPropertyViewHolder) and a DiffUtil item callback impl (DiffCallback)
class PhotoGridAdapter : ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback){

    // This is our viewholder that extends RecyclerView.ViewHolder
    // Pass in binding object from gridview layout to bind our mars property to xml layout
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // Binds grid view list item layout xml var to the mars property
        fun bind(marsProperty: MarsProperty){
            binding.property = marsProperty
            binding.executePendingBindings() // property update executes immediately
        }

    }

    // this is like a static method
    // Remember that diff callback is bascially for the recyclerview to know how to easily compare items so it knows when to update
    // This object extends DiffUtil.ItemCallback and we give it the type of object we want to compare
    // This object also needs to implement abstract methods of DiffUtil
    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {

        // How to compare new item to old item
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem === newItem // true if object references are the same
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

    }

    // This is an abstract method of ListAdapter that must be implemented
    // Returns a Viewholder object, inflates correct view which is our grid item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // This is an abstract method of ListAdapter that must be implemented
    // This method actually binds the current mars property to the viewholder
    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}