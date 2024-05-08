package com.example.ch18_joyce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch18_joyce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonfragment = JsonFragment()
        val xmlfragment = XmlFragment()
        val bundle = Bundle()

        binding.btnSearch.setOnClickListener {
            bundle.putString("searchYear", binding.edtYear.text.toString())

            if (binding.rGroup.checkedRadioButtonId == R.id.rbJson) {
                jsonfragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_content, jsonfragment) // activity_content를 jsonfragment로 대체
                    .commit()
            } else if (binding.rGroup.checkedRadioButtonId == R.id.rbXml) {
                xmlfragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_content, xmlfragment) // activity_content를 xmlfragment로 대체
                    .commit()
            }
        }
    }
}