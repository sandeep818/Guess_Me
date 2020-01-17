package com.jungle.guess_me;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
            ONE,TWO,No
    }

    Player CurrentPlayer=Player.ONE;
    Player[] playerChoices= new Player[9];
    private boolean gameOver=false;
    private Button restartGame;
    private GridLayout gridView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0]=Player.No;
        playerChoices[1]=Player.No;
        playerChoices[2]=Player.No;
        playerChoices[3]=Player.No;
        playerChoices[4]=Player.No;
        playerChoices[5]=Player.No;
        playerChoices[6]=Player.No;
        playerChoices[7]=Player.No;
        playerChoices[8]=Player.No;
        restartGame=findViewById(R.id.reset);
        gridView =findViewById(R.id.grid_layout);



    }
    public void  clickMe(View imgView) {

        ImageView clickedImage = (ImageView) imgView;

        int clickedImageTag = Integer.parseInt(clickedImage.getTag().toString());
        if (playerChoices[clickedImageTag] == Player.No && !gameOver) {
            playerChoices[clickedImageTag] = CurrentPlayer;
            int[][] winnerRowsCollums = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

            clickedImage.animate().rotation(3600).setDuration(3000);
            if (CurrentPlayer == Player.ONE ) {
                clickedImage.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                clickedImage.setAlpha(1f);
                clickedImage.setImageResource(R.drawable.tiger);

                CurrentPlayer = Player.TWO;
            } else if (CurrentPlayer == Player.TWO) {
                clickedImage.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                clickedImage.setAlpha(1f);
                clickedImage.setImageResource(R.drawable.lion);
                CurrentPlayer = Player.ONE;

            }

            for (int[] winnerCollums : winnerRowsCollums) {
                if (playerChoices[winnerCollums[0]] == playerChoices[winnerCollums[1]] && playerChoices[winnerCollums[1]] == playerChoices[winnerCollums[2]] && playerChoices[winnerCollums[0]] != Player.No) {
                    restartGame.setVisibility(View.VISIBLE);
                    gameOver=true;
                    String gameWinner = "";
                    if (CurrentPlayer == Player.ONE) {
                        gameWinner = "Player Two";

                    } else if (CurrentPlayer == Player.TWO) {
                        gameWinner = "Player One";
                    }

                    Toast.makeText(this, gameWinner + " Is The Winner ", Toast.LENGTH_SHORT).show();
                }


            }

            // Toast.makeText(this, ""+clickedImage.getTag(), Toast.LENGTH_SHORT).show();

        }
    }


    public void reset_game(View view){
        for (int index=0; index<gridView.getChildCount();index++){

            ImageView imageView= (ImageView) gridView.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
            imageView.setBackgroundColor(getResources().getColor(R.color.newone));

        }

        for (int i=0;i<9;i++){
            playerChoices[i]=Player.No;
        }
        CurrentPlayer=Player.ONE;
        gameOver=false;
        restartGame.setVisibility(View.GONE);

    }
}
