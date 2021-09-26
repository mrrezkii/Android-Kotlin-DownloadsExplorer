package id.ac.telkomuniversity.mrrezki.data

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import id.ac.telkomuniversity.mrrezki.databinding.ActivityFolderManagementBinding
import id.ac.telkomuniversity.mrrezki.utils.showToast
import java.io.File

class FolderManagementActivity : AppCompatActivity() {

    companion object {
        const val FOLDER_NAME = "New Folder"
        const val SUCCESS_CREATED = "Success create $FOLDER_NAME"
        const val FOLDER_EXISTS = "Folder already exists"
        const val SUCCESS_DELETED = "Success deleted $FOLDER_NAME"
        const val FOLDER_NOT_FOUND = "Folder not found"
    }

    private val binding: ActivityFolderManagementBinding by lazy {
        ActivityFolderManagementBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        binding.btnCreateFolder.setOnClickListener {
            createFolder(downloadsDirectory)
        }
        binding.btnDeleteFolder.setOnClickListener {
            deleteFolder(downloadsDirectory)
        }
    }

    private fun createFolder(directory: File) {
        val file = File(directory, FOLDER_NAME)

        if (!file.exists()) {
            file.mkdir()
            showToast(SUCCESS_CREATED)
        } else {
            showToast(FOLDER_EXISTS)
        }
    }

    private fun deleteFolder(directory: File) {
        val file = File(directory, FOLDER_NAME)

        if (file.exists()) {
            file.delete()
            showToast(SUCCESS_DELETED)
        } else {
            showToast(FOLDER_NOT_FOUND)
        }
    }
}