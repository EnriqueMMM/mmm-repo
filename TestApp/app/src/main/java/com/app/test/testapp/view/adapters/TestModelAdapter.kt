package com.app.test.testapp.view.adapters

import android.app.Application
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test.testapp.R
import com.app.test.testapp.data.local.TestEntity
import com.app.test.testapp.extensions.inject
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by Enrique on 2/10/2018.
 */
class TestModelAdapter : RecyclerView.Adapter<TestModelAdapter.TestHolder>() {

    val ctx by inject<Application>()
    private var list: List<TestEntity> = mutableListOf()
    private var onItemSelected: ((model: TestEntity) -> Unit)? = null
    fun setOnItemSelected(onItemSelectedFun: ((model: TestEntity) -> Unit)?) {
        onItemSelected = onItemSelectedFun
    }

    fun setData(l: List<TestEntity>) {
        list = l
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TestHolder?, position: Int) {
        holder?.let { h ->
            val model = list[h.adapterPosition]
            h.itemView?.apply {
                itemName.text = model.name
                itemPoints.text = model.points.toString()
                itemBox.setOnClickListener { view ->
                    onItemSelected?.invoke(model)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TestHolder =
            TestHolder(LayoutInflater.from(ctx).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = list.size


    inner class TestHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}