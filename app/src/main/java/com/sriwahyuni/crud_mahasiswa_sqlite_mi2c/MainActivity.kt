package com.sriwahyuni.crud_mahasiswa_sqlite_mi2c

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.adapter.mahasiswaAdapter
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.databinding.ActivityMainBinding
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.helper.DBHelper
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.screen_page.TambahDataMahasiswaActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var db : DBHelper
    private lateinit var  mahasiswaAdapter: mahasiswaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(this)
        mahasiswaAdapter = mahasiswaAdapter(db.getAllDataMahasiswa(), this)

        binding.rvDataMahasiswa.layoutManager = LinearLayoutManager(this)
        binding.rvDataMahasiswa.adapter = mahasiswaAdapter

        binding.btnPageTambah.setOnClickListener {
            val intent = Intent(this, TambahDataMahasiswaActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val newMahasiswa = db.getAllDataMahasiswa()
        mahasiswaAdapter.refreshData(newMahasiswa)
    }
}