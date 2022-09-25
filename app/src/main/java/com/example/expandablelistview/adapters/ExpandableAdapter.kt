package com.example.expandablelistview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.expandablelistview.databinding.ChildItemBinding
import com.example.expandablelistview.databinding.GroupItemBinding

class ExpandableAdapter(
    context: Context,
    var titleList: List<String>,
    var groupChild: HashMap<String, List<String>>
) :
    BaseExpandableListAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getGroupCount(): Int {
        return titleList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return groupChild[titleList[groupPosition]]?.size!!
    }

    override fun getGroup(groupPosition: Int): Any {
        return titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return groupChild[titleList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {

        val binding = GroupItemBinding.inflate(inflater, parent, false)

        binding.title.text = titleList[groupPosition]

        return binding.root

    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding = ChildItemBinding.inflate(inflater, parent, false)

        val list = groupChild[titleList[groupPosition]]
        val child = list?.get(childPosition)

        binding.list.text = child

        return binding.root
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}