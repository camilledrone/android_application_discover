package fr.imt_atlantique.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText editTextQuestion;
    private Button sendQuestionButton;

    public static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        this.editTextQuestion = findViewById(R.id.editTextQuestion);
        this.sendQuestionButton = findViewById(R.id.button_question);

        sendQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendQuestion();
                sendQuestionIntent();
            }
        });

    }

    // When 'Answer Activity' completed, it sends back a feedback.
    // (If it have started it by startActivityForResult())
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
            String feedback = data.getStringExtra("feedback");
            Snackbar.make(findViewById(R.id.constraint_layout), feedback, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(findViewById(R.id.constraint_layout), "?!", Snackbar.LENGTH_LONG).show();
        }
    }

    private void sendQuestionIntent() {
        String question = this.editTextQuestion.getText().toString();

        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("question", question);

        this.startActivityForResult(intent, MY_REQUEST_CODE);
    }

    private void sendQuestion() {
        String question = this.editTextQuestion.getText().toString();

        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("question", question);

        this.startActivity(intent);
    }
}