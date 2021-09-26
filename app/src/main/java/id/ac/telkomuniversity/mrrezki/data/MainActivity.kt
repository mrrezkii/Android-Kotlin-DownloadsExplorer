package id.ac.telkomuniversity.mrrezki.data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.telkomuniversity.mrrezki.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.btnFiles.setOnClickListener {
            startActivity(Intent(this, FilesManagementActivity::class.java))
        }
        binding.btnFolder.setOnClickListener {
            startActivity(Intent(this, FolderManagementActivity::class.java))
        }
    }

}