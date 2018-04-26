package io.varol.flixbus.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.varol.flixbus.R
import io.varol.flixbus.data.models.Departures
import kotlinx.android.synthetic.main.layout_timetable_item.view.*

/**
 * Created by varol on 26.4.2018.
 */

class CommonAdapter<T> : RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    object ItemType {
        val DEPARTURE = 0
        val ARRIVAL = 1
    }


    private val items: ArrayList<T> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return if (items[position] is Departures) {
            ItemType.DEPARTURE
        } else // if (items[position] is )
        {
            ItemType.ARRIVAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        when (viewType) {

            ItemType.DEPARTURE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_timetable_item, parent,
                        false)
                return ViewHolder(view)
            }

        /**
         * DUMMY FOR NOW
         */
            ItemType.ARRIVAL -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_timetable_item, parent,
                        false)
                return ViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_timetable_item, parent,
                        false)
                return ViewHolder(view)
            }

        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.let {
            when (getItemViewType(position)) {
                ItemType.DEPARTURE -> {

                    holder.itemView.setOnClickListener {
                        if (holder.adapterPosition != RecyclerView.NO_POSITION && onDepartureItemClickListener != null) {
                            onDepartureItemClickListener!!.onItemClick(holder.adapterPosition)
                        }
                    }
                    holder.bindDepartures(items[position] as Departures)

                }
            }


        }
    }

    class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {


        fun bindDepartures(item: Departures) {

            try {

                itemView.tv_direction.text = item.direction
                itemView.tv_line_code.text = item.line_code
                itemView.tv_time.text = item.datetime.timestamp.toString()
            } catch (e: Exception) {
                return
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


//region Custom helper methods

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    fun clear() {
        while (itemCount > 0) {
            items.removeAt(itemCount - 1)
            this.notifyItemRemoved(itemCount - 1)
        }
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addAt(position: Int, item: T) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun addAll(itemList: List<T>) {
        this.items.addAll(itemList)
        notifyItemRangeChanged((items.size - itemList.size), itemList.size)
    }

    fun remove(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    interface OnDepartureItemClickListener {
        fun onItemClick(position: Int)
    }

    private var onDepartureItemClickListener: OnDepartureItemClickListener? = null

    fun setOnDepartureItemClickListener(onDepartureItemClickListener: OnDepartureItemClickListener) {
        this.onDepartureItemClickListener = onDepartureItemClickListener
    }

    //endregion

}


