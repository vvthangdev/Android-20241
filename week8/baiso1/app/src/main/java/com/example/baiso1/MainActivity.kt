package com.example.baiso1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var addressHelper: AddressHelper
    private lateinit var spinnerTinhThanh: Spinner
    private lateinit var spinnerQuanHuyen: Spinner
    private lateinit var spinnerPhuongXa: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addressHelper = AddressHelper(resources)

        spinnerTinhThanh = findViewById(R.id.spinnerTinhThanh)
        spinnerQuanHuyen = findViewById(R.id.spinnerQuanHuyen)
        spinnerPhuongXa = findViewById(R.id.spinnerPhuongXa)

        setupProvinceSpinner()
    }

    private fun setupProvinceSpinner() {
        val provinces = addressHelper.getProvinces()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provinces)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTinhThanh.adapter = adapter

        spinnerTinhThanh.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedProvince = provinces[position]
                setupDistrictSpinner(selectedProvince)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupDistrictSpinner(province: String) {
        val districts = addressHelper.getDistricts(province)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, districts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerQuanHuyen.adapter = adapter

        spinnerQuanHuyen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedDistrict = districts[position]
                setupWardSpinner(province, selectedDistrict)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupWardSpinner(province: String, district: String) {
        val wards = addressHelper.getWards(province, district)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, wards)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPhuongXa.adapter = adapter
    }
}
