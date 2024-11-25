package com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.ActivityDetail
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.R
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.helper.DBHelper
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.model.ModelMahasiswa
import com.sriwahyuni.crud_mahasiswa_sqlite_mi2c.screen_page.UpdateMahasiswaActivity

class mahasiswaAdapter(

    private var listMahasiswa: List<ModelMahasiswa>,
    val context: Context
) : RecyclerView.Adapter<mahasiswaAdapter.MahasiswaViewHolder>() {

    private val db : DBHelper = DBHelper(context)

    class MahasiswaViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNama : TextView = itemView.findViewById(R.id.txtItemNama)
        val txtJurusan : TextView = itemView.findViewById(R.id.txtItemJurusan)
        val txtNim : TextView = itemView.findViewById(R.id.txtItemNim)
        val cardMahasiswa : CardView = itemView.findViewById(R.id.cardMahasiswa)

        val btnEdit : ImageView = itemView.findViewById(R.id.btnEditItem)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDeleteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_mahasiswa,
            parent, false
        )
        return MahasiswaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val nMahasiswa = listMahasiswa[position]
        holder.txtNim.text = nMahasiswa.nim.toString()
        holder.txtNama.text = nMahasiswa.nama
        holder.txtJurusan.text = nMahasiswa.jurusan

        //bagi yg sudah bisa delete data kirim ke WA

        holder.cardMahasiswa.setOnClickListener(){
            val intent = Intent(context, ActivityDetail::class.java)

            intent.putExtra("nama", listMahasiswa[position].nama)
            intent.putExtra("nim", listMahasiswa[position].nim)
            intent.putExtra("jurusan", listMahasiswa[position].jurusan)
            context.startActivity(intent)

            Toast.makeText(context, listMahasiswa[position].nama, Toast.LENGTH_SHORT).show()
        }

        holder.btnDelete.setOnClickListener{
            db.deleteMahasiswa(nMahasiswa.id)
            refreshData(db.getAllDataMahasiswa())
            Toast.makeText(holder.itemView.context,
                "Berhasil delete data ${nMahasiswa.nama}", Toast.LENGTH_LONG).show()
        }

        holder.btnEdit.setOnClickListener(){
            val intent = Intent(holder.itemView.context,UpdateMahasiswaActivity::class.java).apply {
                putExtra("id_mhs", nMahasiswa.id)
            }
            holder.itemView.context.startActivity(intent)
        }


    }

    //ini untuk refresh data
    fun refreshData(newMahasiswa: List<ModelMahasiswa>){
        listMahasiswa = newMahasiswa
        notifyDataSetChanged()
    }
}
