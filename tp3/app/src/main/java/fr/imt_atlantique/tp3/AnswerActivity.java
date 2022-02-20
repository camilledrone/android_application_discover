package fr.imt_atlantique.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView question;
    private EditText answer;
    private Button answer_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        this.question = this.findViewById(R.id.text_question_asked);
        this.answer = this.findViewById(R.id.editTextAnswer);
        this.answer_button = this.findViewById(R.id.button_answer);

        this.answer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAnswer();
            }
        });

        Intent intent = this.getIntent();
        String question_string = intent.getStringExtra("question");
        this.question.setText(question_string);

    }

    // When completed this Activity, send feedback to the caller.
    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        String feedback = answer.getText().toString();
        data.putExtra("feedback", feedback);

        // Activity finished ok, return the data
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }

    private void sendAnswer() {
        // Calling onBackPressed() method to back to the previous Activity.
        this.onBackPressed();
    }
}