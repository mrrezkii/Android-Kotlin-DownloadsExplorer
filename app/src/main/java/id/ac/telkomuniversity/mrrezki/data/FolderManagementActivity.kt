package id.ac.telkomuniversity.mrrezki.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.telkomuniversity.mrrezki.databinding.ActivityFolderManagementBinding

class FolderManagementActivity : AppCompatActivity() {

    private val binding: ActivityFolderManagementBinding by lazy {
        ActivityFolderManagementBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}