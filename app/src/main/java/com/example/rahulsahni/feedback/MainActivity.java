package com.example.rahulsahni.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int count = 0;
    private TextView textView;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private ConstraintLayout layout;

    enum Answer{
        ONE, TWO, THREE, FOUR, NONE
    }

    private Answer option;

    private ArrayList<Question> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.question_layout);
        textView = findViewById(R.id.textView);
        checkBox1 = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        String choices [][] = {
                {"sada","sada","gdiuksdff","dfsd"},
                {"sadsdfsda","sajkhsdfgda","gdf","dfsfghd"},
                { "sadsfda","saddfga","sdfgdf","dfsd"},
                { "sasdfsdda","sada","gdf","dfsgfghd"},
                {"sajghjghda","sakiida","gdf","dfssad"}
        };
        arrayList.add(new Question("Question 1", choices[0]));
        arrayList.add(new Question("Question 2", choices[1]));
        arrayList.add(new Question("Question 13", choices[2]));
        arrayList.add(new Question("Question 4", choices[3]));
        arrayList.add(new Question("Question 5", choices[4]));

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    option = Answer.ONE;
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    option = Answer.TWO;
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    option = Answer.THREE;
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                    checkBox4.setChecked(false);
                }
            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    option = Answer.FOUR;
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox1.setChecked(false);
                }
            }
        });

        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Option "+option);
                if(option.equals(Answer.NONE)){
                    Toast.makeText(MainActivity.this, "Select an option", Toast.LENGTH_SHORT).show();
                } else {
                    count++;
                    if (count < arrayList.size()) {
                        loadQuestion(count);
                    } else {
                        //send data to server
                        button.setText("Submit");
                        button.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                    }
                }
            }
        });

        loadQuestion(0);
    }

    private void loadQuestion(int count) {
        option = Answer.NONE;
        Question question = arrayList.get(count);
        textView.setText(question.getQuestion());
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        checkBox1.setText(question.getOption1());
        checkBox2.setText(question.getOption2());
        checkBox3.setText(question.getOption3());
        checkBox4.setText(question.getOption4());

        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        layout.setAnimation(animation);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.log_out){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(MainActivity.this, SplashActivity.class));
                            finish();
                        }
                    });
        }
            startActivity(new Intent(this, SplashActivity.class));
        return true;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
