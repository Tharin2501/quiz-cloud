package no.kristiania.quizcloud_exam.game;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import no.kristiania.quizcloud_exam.MainActivity;
import no.kristiania.quizcloud_exam.R;


public class GameActivity extends AppCompatActivity {

    private static final String TAG = "GameActivity";

    private PlayerView playerView;
    private SimpleExoPlayer player;

    Button answer1, answer2, answer3, answer4;
    TextView score, question;
    private Questions questions = new Questions();
    private String answer;
    private int myScore;
    private int questionLength = questions.questions.length;
    Random r;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoplayer_layout);


        playerView = findViewById(R.id.playerView);

        player = ExoPlayerFactory.newSimpleInstance(this);

        // Bind the player to the view.
        playerView.setPlayer(player);

        // DataSource instances which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "QuizCloud"));

        // This is the MediaSource object representing the media to be played.
        MediaSource firstSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.pop_out));
        MediaSource secondSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.praise_the_lord));
        MediaSource thirdSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.old_town_road));
        MediaSource forthSourch = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.your_fav_dress));

        //Creates a playlist by concatenating mediasources
        ConcatenatingMediaSource playList = new ConcatenatingMediaSource(firstSource, secondSource,
                thirdSource, forthSourch);

        // Autoplay video
        player.setPlayWhenReady(true);
        player.prepare(playList);

        // Quiz

        r = new Random();

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        question = (TextView) findViewById(R.id.question);
        score = (TextView) findViewById(R.id.score);

        score.setText("Score: " + myScore);

        //updateQuestion(r.nextInt(questionLength));
        updateQuestion1(questionLength); // pop out


        // praise the lord
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getText() == answer) {
                    myScore++;
                    score.setText("Score: " + myScore);
                    // updateQuestion(r.nextInt(questionLength));
                    // player.seekToDefaultPosition(2);
                    updateQuestion3(3);
                    player.seekToDefaultPosition(2); // old town road

                }

            }
        });

       // Pop out
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getText() == answer) {
                    myScore++;
                    score.setText("Score: " + myScore);

                    updateQuestion2(2); // Answer 2 - Pop Out
                    player.seekToDefaultPosition(1); // -> Praise the Lord

                }

            }
        });
        // Old town road
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getText() == answer) {
                    myScore++;
                    score.setText("Score: " + myScore);

                    updateQuestion4(3); // 3
                    player.seekToDefaultPosition(3); // Your Favorite Dress
                    // legg til sang

                }

            }
        });

        // Your Favorite Dress
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer4.getText() == answer) {
                    myScore++;
                    score.setText("Score: " + myScore);

                    // go to result screen
                    Intent intent = new Intent(GameActivity.this, Result.class);
                    startActivity(intent);
                }
            }
        });
    }

    // POP OUT
    private void updateQuestion1(int num) {
        question.setText(questions.getQuestion(0));
        answer1.setText(questions.getChoice1(0));
        answer2.setText(questions.getChoice2(0));
        answer3.setText(questions.getChoice3(0));
        answer4.setText(questions.getChoice4(0));

        answer = questions.getCorrectAnswer(0);
    }

    // Praise the lord
    private void updateQuestion2(int num) {
        question.setText(questions.getQuestion(1));
        answer1.setText(questions.getChoice1(1));
        answer2.setText(questions.getChoice2(1));
        answer3.setText(questions.getChoice3(1));
        answer4.setText(questions.getChoice4(1));

        answer = questions.getCorrectAnswer(1);
    }

    // Old Town Road
    private void updateQuestion3(int num) {
        question.setText(questions.getQuestion(2));
        answer1.setText(questions.getChoice1(2));
        answer2.setText(questions.getChoice2(2));
        answer3.setText(questions.getChoice3(2));
        answer4.setText(questions.getChoice4(2));

        answer = questions.getCorrectAnswer(2);
    }

    // You'r favorite dress
    private void updateQuestion4(int num) {
        question.setText(questions.getQuestion(3));
        answer1.setText(questions.getChoice1(3));
        answer2.setText(questions.getChoice2(3));
        answer3.setText(questions.getChoice3(3));
        answer4.setText(questions.getChoice4(3));

        answer = questions.getCorrectAnswer(3);
    }




    @Override
    public void onBackPressed() {
        /* I disabled the back button on the emulator
           use created back buttons instead */
    }
}