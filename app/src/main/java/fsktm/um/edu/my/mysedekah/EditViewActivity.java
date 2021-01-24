package fsktm.um.edu.my.mysedekah;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import fsktm.um.edu.my.mysedekah.campaigndb.campaignhelper;
import fsktm.um.edu.my.mysedekah.campaigndb.campaignitems;
import fsktm.um.edu.my.mysedekah.campaigndb.campaigncontent;

public class EditViewActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int PICK_IMAGE = 1;
    public static String user_id = "40";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editview);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView pageImage = (ImageView) findViewById(R.id.iv_pageimage);
        pageImage.setOnClickListener(this);
        Button save = findViewById(R.id.btn_save);
        save.setOnClickListener(this);

        campaignhelper helper = new campaignhelper(this);
        if (helper.checkID(user_id)) {
            campaigncontent cc = helper.getCampaign(user_id);
            setLayout(cc);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_pageimage:
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;
            case R.id.btn_save:
                campaignhelper helper = new campaignhelper(this);
                EditText c_title = findViewById(R.id.ctitle);
                EditText c_desc = findViewById(R.id.tv_description);
                ContentValues values = new ContentValues();
                values.put(campaignitems.campaign.COLUMN_ID, user_id);
                values.put(campaignitems.campaign.COLUMN_TITLE,c_title.getText().toString());
                values.put(campaignitems.campaign.COLUMN_DESC, c_desc.getText().toString());
                values.put(campaignitems.campaign.COLUMN_IMG, imgToBlob());
                if (helper.checkID(user_id)) {
                    helper.update(values, user_id);
                }
                else{
                    helper.insertcampaign(values);
                }
                Toast.makeText(getApplicationContext(), "Saved Changes.", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                break;
        }

    }


    public void setLayout(campaigncontent cc){
        EditText c_title = findViewById(R.id.ctitle);
        EditText c_desc = findViewById(R.id.tv_description);
        ImageView Imgv = findViewById(R.id.iv_pageimage);

        c_title.setText(cc.getTitle());
        c_desc.setText(cc.getDesc());
        Imgv.setImageDrawable(blobToDrawable(cc.getImg()));
    }

    public byte[] imgToBlob(){
        ImageView Imgv = findViewById(R.id.iv_pageimage);
        Drawable dw = Imgv.getDrawable();
        Bitmap bitmap = vectToBitmap(dw);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bitmapbyte = stream.toByteArray();
        return  bitmapbyte;
    }

    public Bitmap vectToBitmap(Drawable drawable){
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }

    }

    public Drawable blobToDrawable(byte[] image){
        Drawable img = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(image, 0, image.length));
        return img;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {

            ImageView pageImage = (ImageView) findViewById(R.id.iv_pageimage);

            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                pageImage.setImageBitmap(selectedImage);

            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();}
            catch (NullPointerException e){}
        }
    }
}
