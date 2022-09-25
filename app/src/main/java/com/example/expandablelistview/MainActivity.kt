package com.example.expandablelistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import com.example.expandablelistview.adapters.ExpandableAdapter
import com.example.expandablelistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var groupTitle: ArrayList<String>
    private lateinit var group: HashMap<String, List<String>>
    private lateinit var expandableAdapter: ExpandableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groupTitle = ArrayList()
        groupTitle.add("Ferghana")
        groupTitle.add("Namangan")
        groupTitle.add("Andijon")

        group = HashMap()
        val ferghanaList = arrayListOf("Toshloq", "Marg'ilon", "Qo'qon")
        group["Ferghana"] = ferghanaList
        val namanganList = arrayListOf("Chest", "Pop", "Chortoq")
        group["Namangan"] = namanganList
        val andijonList = arrayListOf("Shahrixon", "Buloqboshi", "Asaka")
        group["Andijon"] = andijonList

        expandableAdapter = ExpandableAdapter(this, groupTitle, group)
        binding.expandableListView.setAdapter(expandableAdapter)

        //This is close operation
        /**binding.expandableListView.setOnGroupCollapseListener {
         *    Toast.makeText(this, "Close function", Toast.LENGTH_SHORT).show()
        }*/

        //This is open operation
        /**binding.expandableListView.setOnGroupExpandListener {
         *   Toast.makeText(this, "Open function", Toast.LENGTH_SHORT).show()
        }*/

        val prevExpandPosition = intArrayOf(-1)
        binding.expandableListView.setOnGroupExpandListener { groupPosition ->
            if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                binding.expandableListView.collapseGroup(prevExpandPosition[0])
            }
            prevExpandPosition[0] = groupPosition
        }
    }
}