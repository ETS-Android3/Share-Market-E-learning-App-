package com.example.sharemarket;

import static com.example.sharemarket.R.id.nav_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class setting extends AppCompatActivity{

    private TextView textViewProfileName, nameTxtView, workTxtView;
    private TextView emailTxtView, phoneTxtView, videoTxtView, facebookTxtView, twitterTxtView;
    private CircleImageView userImageView;
    private ImageView emailImageView, phoneImageView, videoImageView;
    private ImageView facebookImageView, twitterImageView ,back;
    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String email;
    private String userId;
    private static final String USERS = "users";
    StorageReference storageReference;





    BottomNavigationView bottomNavigationView;
    //    Button signOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Profile");


        back = findViewById(R.id.back);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(nav_profile);
        storageReference = FirebaseStorage.getInstance().getReference();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),course.class));
                finish();
            }
        });



        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), course.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ebook:
                        startActivity(new Intent(getApplicationContext(), Ebook.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case nav_profile:
                        return true;


                    case R.id.shortsss:
                        startActivity(new Intent(getApplicationContext(),shorts.class));
                        overridePendingTransition(0,0);
                        return true;


                }

                return false;
            }
        });


        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        storageReference = FirebaseStorage.getInstance().getReference();
        textViewProfileName = findViewById(R.id.textViewProfileName);
//        workTxtView = findViewById(R.id.workplace_textview);
        emailTxtView = findViewById(R.id.email_textview);
        phoneTxtView = findViewById(R.id.phone_textview);
//        videoTxtView = findViewById(R.id.video_textview);
//        facebookTxtView = findViewById(R.id.facebook_textview);
//        twitterTxtView = findViewById(R.id.twitter_textview);

        userImageView = findViewById(R.id.user_imageview);
        emailImageView = findViewById(R.id.email_imageview);
        phoneImageView = findViewById(R.id.phone_imageview);
//        videoImageView = findViewById(R.id.phone_imageview);
//        facebookImageView = findViewById(R.id.facebook_imageview);
//        twitterImageView = findViewById(R.id.twitter_imageview);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        StorageReference fileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(userImageView);
            }
        });

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(setting.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (fAuth.getCurrentUser() != null) {
                    textViewProfileName.setText(documentSnapshot.getString("fName"));
                    emailTxtView.setText(documentSnapshot.getString("email"));
                    phoneTxtView.setText(documentSnapshot.getString("mobile"));
                }
            }
        });

        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);


            }
        });


    }


        @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data)
        {
            Log.d(" enter requestcode", "lsjf;lkasjflk");
            super.onActivityResult(requestCode, resultCode, data);
            Log.d(TAG, "onActivityResult: ");
            if (requestCode == 1000) {
                Log.d("requestcode", "lsjf;lkasjflk");
                if (resultCode == Activity.RESULT_OK) {
//                    Toast.makeText(setting.this, "sushilwa", Toast.LENGTH_SHORT).show();
//                    Log.d("resultcode","inside result code");
                    Uri imageUri = data.getData();

                    userImageView.setImageURI(imageUri);
                    uploadImageToFirebase(imageUri);
                }
//

            }
//

        // Read from the database
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {
//                       Log.i(TAG, "onSuccess: what is uri");
                       Picasso.get().load(uri).into(userImageView);
                   }
               });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(setting.this, "Failed to upload.", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(), course.class);
        startActivity(intent);
    }

    public void signOut(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(), "user logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),login.class));
                    finish();
    }
}


