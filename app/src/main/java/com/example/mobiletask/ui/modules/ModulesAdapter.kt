package com.example.mobiletask.ui.modules

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletask.R
import com.example.mobiletask.data.response.ModuleData
import java.util.Random

class ModulesAdapter(var modulesList: ArrayList<ModuleData>, private val context: Context) :
    RecyclerView.Adapter<ModulesAdapter.MyViewHolder>() {

    private val previousColors = arrayListOf<Int>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTextView: TextView
        var statusTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.text_view_name)
            statusTextView = itemView.findViewById(R.id.text_view_status)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_modules_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return modulesList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTextView.text = modulesList[position].name
        val statusResId = if (modulesList[position].status == "1") {
            R.string.active
        } else {
            R.string.in_active
        }
        holder.statusTextView.text = context.resources.getString(statusResId)
        val textColor = generateRandomColor()
        holder.nameTextView.setTextColor(textColor)
        holder.statusTextView.setTextColor(textColor)

    }

    private fun generateRandomColor(): Int {
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        if (previousColors.contains(color)) {
            generateRandomColor()
        }
        previousColors.add(color)
        return color
    }

}