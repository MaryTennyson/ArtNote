package com.ebraratabay.artnote

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ebraratabay.artnote.databinding.ActivityArtBinding
import com.google.android.material.snackbar.Snackbar

class ArtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtBinding
  var selectedBitmap:Bitmap? =null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        registerLauncher()

    }

    fun onSaveClicked(view: View) {


    }

    fun onSelectImage(view: View) {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) { //rationale
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Give Permission", View.OnClickListener {
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }).show()
            } else {
   permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)

        }
    }

private fun registerLauncher(){
    activityResultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result -> if(result.resultCode== RESULT_OK){
        val intentFromResult= result.data
        if (intentFromResult!=null){
            val imageData= intentFromResult.data
          //  binding.imageView.setImageURI(imageData)
            if(imageData!=null){
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source=ImageDecoder.createSource(this@ArtActivity.contentResolver,imageData)
                  selectedBitmap= ImageDecoder.decodeBitmap(source)
                  binding.imageView.setImageBitmap(selectedBitmap)
              } else {
                 selectedBitmap= MediaStore.Images.Media.getBitmap(contentResolver, imageData)
              }
            }catch (e:Exception){
                e.printStackTrace()
            }}
        }
    }

}
 permissionLauncher  = registerForActivityResult(ActivityResultContracts.RequestPermission()){
     result-> if(result){
     val intentToGallery =
         Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
     activityResultLauncher.launch(intentToGallery)
 }else{
     Toast.makeText(this@ArtActivity, "Permission needed", Toast.LENGTH_LONG).show()
 }
 }
}}