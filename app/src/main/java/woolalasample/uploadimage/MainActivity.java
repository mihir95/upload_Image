package woolalasample.uploadimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE =1;

    ImageView imageToUpload; /*,downloadImage;*/
    Button bUploadImage; /*,bDownloadImage;*/
    EditText uploadImageNmae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageToUpload = (ImageView) findViewById(R.id.imageToUpload); //same for download
        bUploadImage = (Button) findViewById(R.id.bUploadImage); //same for dwn
        uploadImageNmae = (EditText) findViewById(R.id.etUploadName); //caption, same for dwn

        imageToUpload.setOnClickListener(this); //perform some action when click
        bUploadImage.setOnClickListener(this);
        uploadImageNmae.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageToUpload:
                break;

            case R.id.bUploadImage: //same for dwn
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, /*to select*/ MediaStore.Images.Media.EXTERNAL_CONTENT_URI); //gallery to open
                //galleryIntent.setType("image/*");
                galleryIntent.putExtra("crop", "true");
                galleryIntent.putExtra("outputX", 512);
                galleryIntent.putExtra("outputY", 512);
                galleryIntent.putExtra("aspectX", 1);
                galleryIntent.putExtra("aspectY", 1);
                galleryIntent.putExtra("scale", true);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE); //start intern that has created, "result" to get a result when select an image
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //method called when user selects pic from gallery
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();//address of image
            imageToUpload.setImageURI(selectedImage);

            Bundle extras = data.getExtras();
            Bitmap image = extras.getParcelable("data");
            imageToUpload.setImageBitmap(image);
        }
    }

}
