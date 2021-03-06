package com.kwang0.androidanimation.presentation.ui.recyclers.main

import android.app.Activity
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kwang0.androidanimation.R
import com.kwang0.androidanimation.data.models.Content
import com.kwang0.androidanimation.helper.IntentHelper
import com.kwang0.androidanimation.presentation.ui.activities.HostsAnimActivity
import com.kwang0.androidanimation.presentation.ui.activities.NeonButtonSwitchActivity

class MainRecyclerAdapter(val mContext: Context, var mData: List<Content>) : RecyclerView.Adapter<MainRecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerHolder {
        val rootView: View = LayoutInflater.from(mContext).inflate(R.layout.activity_main_rv, parent, false)
        return MainRecyclerHolder(
            rootView
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MainRecyclerHolder, position: Int) {
        try {
            val item: Content = mData.get(position)

            setContentFrame(holder, item.id)
            setContentText(holder, item.text)
            setClickListener(holder, item.id)
        } catch (e: IndexOutOfBoundsException) {
            Toast.makeText(mContext, mContext.getString(R.string.exception_out_of_bounds), Toast.LENGTH_LONG).show()
        }
    }

    private fun setContentFrame(holder: MainRecyclerHolder, id: String) {
        when(id) {
            "anim-01" -> {
                val view = LayoutInflater.from(mContext).inflate(R.layout.view_neon_btn, holder.fl, false)
                val button = view.findViewById<Button>(R.id.view_neon_btn)
                val frameAnimation = button.getBackground() as AnimationDrawable
                frameAnimation.start()
                holder.fl.addView(view)
            }
            "anim-02" -> {
                val view = LayoutInflater.from(mContext).inflate(R.layout.view_hosts_anim, holder.fl, false)
                holder.fl.addView(view)
            }
        }
    }

    private fun setContentText(holder: MainRecyclerHolder, text: String) {
        holder.tv.text = text
    }

    private fun setClickListener(holder: MainRecyclerHolder, id: String) {
        when(id) {
            "anim-01" -> holder.layout.setOnClickListener{
                holder.fl.transitionName = "frameTransition"
                IntentHelper.activityTransitionIntent(mContext as Activity, NeonButtonSwitchActivity::class.java, holder.fl, "frameTransition")
            }
            "anim-02" -> holder.layout.setOnClickListener{
                holder.fl.transitionName = "frameTransition"
                IntentHelper.activityTransitionIntent(mContext as Activity, HostsAnimActivity::class.java, holder.fl, "frameTransition")
            }
        }
    }
}
