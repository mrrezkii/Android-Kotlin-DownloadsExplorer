package id.ac.telkomuniversity.mrrezki.data

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import id.ac.telkomuniversity.mrrezki.databinding.ActivityFilesManagementBinding
import id.ac.telkomuniversity.mrrezki.utils.showToast
import timber.log.Timber
import java.io.*

class FilesManagementActivity : AppCompatActivity() {

    private val binding: ActivityFilesManagementBinding by lazy {
        ActivityFilesManagementBinding.inflate(
            layoutInflater
        )
    }

    companion object {
        const val FILE_NAME = "filename.txt"
        const val SUCCESS_CREATED = "Success create $FILE_NAME"
        const val SUCCESS_READ = "Success read $FILE_NAME"
        const val SUCCESS_EDIT = "Success edit $FILE_NAME"
        const val SUCCESS_DELETE = "Success delete $FILE_NAME"
        const val FILE_NOT_FOUND = "File not found"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initPermissions()
        setupView()
    }

    private fun setupView() {
        val downloadsDirectory =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        binding.btnCreateFile.setOnClickListener {
            createFile(downloadsDirectory)
        }
        binding.btnReadFile.setOnClickListener {
            readFile(downloadsDirectory)
        }
        binding.btnEditFile.setOnClickListener {
            editFile(downloadsDirectory)
        }
        binding.btnDeleteFile.setOnClickListener {
            deleteFile(downloadsDirectory)
        }
    }


    private fun initPermissions() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    if (p0!!.areAllPermissionsGranted()) {
                        Timber.d("All permissions are granted")
                    } else {
                        val intent: Intent = intent
                        finish()
                        startActivity(intent)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    p1!!.continuePermissionRequest()
                }

            }).withErrorListener {
                Timber.d("$it")
            }.check()
    }

    private fun createFile(directory: File) {
        val fillFile = "Try to input a data text"
        val file = File(directory, FILE_NAME)

        val outputStream: FileOutputStream?
        try {
            file.createNewFile()
            outputStream = FileOutputStream(file, true)
            with(outputStream) {
                write(fillFile.toByteArray())
                flush()
                close()
            }
            showToast(SUCCESS_CREATED)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun editFile(directory: File) {
        val updateFile = "Update data text"
        val file = File(directory, FILE_NAME)

        if (file.exists()) {
            val outputStream: FileOutputStream?
            try {
                file.createNewFile()
                outputStream = FileOutputStream(file, false)
                with(outputStream) {
                    write(updateFile.toByteArray())
                    flush()
                    close()
                }
                showToast(SUCCESS_EDIT)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            showToast(FILE_NOT_FOUND)
        }

    }

    private fun readFile(directory: File) {
        val file = File(directory, FILE_NAME)

        if (file.exists()) {
            val text = StringBuilder()

            try {
                val br = BufferedReader(FileReader(file))

                var line = br.readLine()

                while (line != null) {
                    text.append(line)
                    line = br.readLine()
                }
                br.close()
                showToast(SUCCESS_READ)
            } catch (e: IOException) {
                Timber.e("Error : $e")
            }
            binding.tvRead.text = text.toString()
        } else {
            showToast(FILE_NOT_FOUND)
        }

    }

    private fun deleteFile(directory: File) {
        val file = File(directory, FILE_NAME)
        if (file.exists()) {
            file.delete()
            showToast(SUCCESS_DELETE)
        } else {
            FILE_NOT_FOUND
        }
    }
}