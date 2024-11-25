package com.sriwahyuni.crud_mahasiswa_sqlite_mi2c

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityDetail : AppCompatActivity() {
    private lateinit var txtMahasiswa: TextView
    private lateinit var imgMahasiswa: ImageView
    private lateinit var txtNamaMhs: TextView
    private lateinit var txtProdiMhs: TextView
    private lateinit var txtNimMhs: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        txtMahasiswa = findViewById(R.id.txtMahasiswa)
        imgMahasiswa = findViewById(R.id.imgMahasiswa)
        txtNamaMhs = findViewById(R.id.txtNamaMhs)
        txtProdiMhs = findViewById(R.id.txtProdiMhs)
        txtNimMhs = findViewById(R.id.txtNimMhs)

        val imageMahasiswa = intent.getIntExtra("Image Mahasiswa", 0)
        val namaMahasiswa = intent.getStringExtra("nama")
        val nimMahasiswa = intent.getIntExtra("nim", 0).toString()
        val prodiMahasiswa = intent.getStringExtra("jurusan")

        imgMahasiswa.setImageResource(imageMahasiswa)
        txtNamaMhs.setText(namaMahasiswa)
        txtNimMhs.setText(nimMahasiswa)
        txtProdiMhs.setText(prodiMahasiswa)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
