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
        const val FOLDER_NAME_2 = "New Folder 2"
        const val SUCCESS_CREATED = "Success create $FOLDER_NAME"
        const val FOLDER_EXISTS = "Folder already exists"
        const val SUCCESS_RENAME_1_TO_2 = "Success rename folder $FOLDER_NAME to $FOLDER_NAME_2"
        const val SUCCESS_RENAME_2_TO_1 = "Success rename folder $FOLDER_NAME_2 to $FOLDER_NAME"
        const val FAILED_RENAME = "Failed to rename folder"
        const val SUCCESS_DELETED = "Success deleted $FOLDER_NAME"
        const val SUCCESS_DELETED_2 = "Success deleted $FOLDER_NAME_2"
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
        binding.btnUpdateFolder.setOnClickListener {
            updateFolder(downloadsDirectory)
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

    private fun updateFolder(directory: File) {
        val file = File(directory, FOLDER_NAME)
        val file2 = File(directory, FOLDER_NAME_2)

        if (file.exists() && file.name == FOLDER_NAME) {
            val success: Boolean = file.renameTo(file2)
            if (success) {
                showToast(SUCCESS_RENAME_1_TO_2)
            } else {
                showToast(FAILED_RENAME)
            }
        } else if (file2.exists() && file2.name == FOLDER_NAME_2) {
            val success: Boolean = file2.renameTo(file)
            if (success) {
                showToast(SUCCESS_RENAME_2_TO_1)
            } else {
                showToast(FAILED_RENAME)
            }
        } else {
            showToast(FOLDER_NOT_FOUND)
        }

    }

    private fun deleteFolder(directory: File) {
        val file = File(directory, FOLDER_NAME)
        val file2 = File(directory, FOLDER_NAME_2)

        if (file.exists() && file.name == FOLDER_NAME) {
            file.delete()
            showToast(SUCCESS_DELETED)
        } else if (file2.exists() && file2.name == FOLDER_NAME_2) {
            file2.delete()
            showToast(SUCCESS_DELETED_2)
        } else {
            showToast(FOLDER_NOT_FOUND)
        }
    }
}