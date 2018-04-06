package com.thebaileybrew.nerdrabbithole;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.text.NumberFormat;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

// TODO: Look into replacing TOAST messages with SNACKBAR
// TODO: Potentially the Snackbar could allow for an action to search the internet? or send a text to a friend asking for help?

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AccountHeader headerResult;
    private Drawer result;

    //Declare the variables for each nerd sheet
    //Sci Fi Television Correct, Incorrect and Booleans for isAnswered
    int SciFiTelevisionCorrect;int SciFiTelevisionIncorrect;int SciFiTelevisionTotal;
    boolean scifiQ1answered; boolean scifiQ2answered; boolean scifiQ3answered; boolean scifiQ4answered; boolean scifiQ5answered;
    boolean scifiQ1Correct; boolean scifiQ2Correct; boolean scifiQ3Correct; boolean scifiQ4Correct; boolean scifiQ5Correct;
    boolean scifiQ1scored; boolean scifiQ2scored; boolean scifiQ3scored; boolean scifiQ4scored; boolean scifiQ5scored;

    //Sci Fi Trek Wars Correct, Incorrect and Booleans for isAnswered
    int SciFiTrekWarsCorrect;int SciFiTrekWarsIncorrect;int SciFiTrekWarsTotal;
    boolean trekwarsQ1answered; boolean trekwarsQ2answered; boolean trekwarsQ3answered; boolean trekwarsQ4answered; boolean trekwarsQ5answered;
    boolean trekwarsQ1Correct; boolean trekwarsQ2Correct; boolean trekwarsQ3Correct; boolean trekwarsQ4Correct; boolean trekwarsQ5Correct;
    boolean trekwarsQ1scored; boolean trekwarsQ2scored; boolean trekwarsQ3scored; boolean trekwarsQ4scored; boolean trekwarsQ5scored;

    //Sci Fi Other Verses Correct, Incorrect and Booleans for isAnswered
    int SciFiOtherCorrect;int SciFiOtherIncorrect;int SciFiOtherTotal;
    boolean scifiOtherQ1answered; boolean scifiOtherQ2answered; boolean scifiOtherQ3answered;


    //Fantasy LotR Correct, Incorrect and Booleans for isAnswered
    int FantasyLotRCorrect;int FantasyLotRIncorrect;int FantasyLotRTotal;
    boolean lotrQ1answered; boolean lotrQ2answered; boolean lotrQ3answered;


    //Fantasy Harry Potter Correct, Incorrect and Booleans for isAnswered
    int FantasyPotterCorrect;int FantasyPotterIncorrect;int FantasyPotterTotal;
    boolean potterQ1answered; boolean potterQ2answered; boolean potterQ3answered;


    //Comics Television Correct, Incorrect and Booleans for isAnswered
    int ComicsTelevisionCorrect;int ComicsTelevisionIncorrect;int ComicsTelevisionTotal;
    boolean comicstvQ1answered; boolean comicstvQ2answered; boolean comicstvQ3answered;


    //Comics Movies Correct, Incorrect and Booleans for isAnswered
    int ComicsFilmCorrect;int ComicsFilmIncorrect;int ComicsFilmTotal;
    boolean comicsfilmQ1answered; boolean comicsfilmQ2answered; boolean comicsfilmQ3answered;


    //Streaming Television Correct, Incorrect and Booleans for isAnswered
    int StreamingCorrect;int StreamingIncorrect;int StreamingTotal;
    boolean streamQ1answered; boolean streamQ2answered; boolean streamQ3answered;


    //Anime and Animation Correct, Incorrect and Booleans for isAnswered
    int AnimationCorrect;int AnimationIncorrect;int AnimationTotal;
    boolean animeQ1answered; boolean animeQ2answered; boolean animeQ3answered;


    //Overall Final Scores for each category
    int sciFiTelevisionScore; int sciFiTrekWarsScore; int sciFiOtherScore; int fantasyLotRScore; int fantasyPotterScore;
    int comicsTelevisionScore; int comicsFilmScore; int streamingScore; int animationScore; int totalScore;

    NestedScrollView mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.nested_scrollview_data_to_replace);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Ultimate Nerd Quiz");

        final LayoutInflater layoutInflater;
        Button startGame = findViewById(R.id.start_quiz_button);
        startGame.setOnClickListener(MainActivity.this);

        // TODO: Update the headerResult field to include the EditText Name input from activity_main
        // This should then display user name when taking quiz.
        // TODO: Make sure to remove User Name on allScoresReset onClick Method
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeightDp(75)
                .withSavedInstance(savedInstanceState)
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Sci-Fi - Television").withIdentifier(1),
                        new PrimaryDrawerItem().withName("Sci-Fi - Star Wars").withIdentifier(2),
                        new PrimaryDrawerItem().withName("Sci-Fi - Other Universes").withIdentifier(3),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Fantasy - Lord of the Rings").withIdentifier(4),
                        new PrimaryDrawerItem().withName("Fantasy - Harry Potter").withIdentifier(5),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Comics - Television").withIdentifier(6),
                        new PrimaryDrawerItem().withName("Comics - Feature Films").withIdentifier(7),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Streaming - Netflix/Hulu/Amazon").withIdentifier(8),
                        new PrimaryDrawerItem().withName("Animation & Anime").withIdentifier(9),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Scores & Sharing").withIdentifier(10)
                )
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                       /**
                        * @param view
                        * @param position
                        * @param drawerItem
                        * @return true if the event was consumed
                        */
                       @Override
                       public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                           if (mainLayout != null) {
                               mainLayout.removeAllViewsInLayout();
                           }
                           switch (position) {
                               case 0: // Main Activity
                                   break;
                               case 1: // Sci-Fi Television
                                   ScifiTelevisionSelection();
                                   collapsingToolbarLayout.setTitle("Sci-Fi - Television");
                                   Toast.makeText(MainActivity.this, "Opening SciFi - Television", Toast.LENGTH_SHORT).show();
                                   break;
                               case 2: // Sci-Fi Star Trek / Star Wars
                                   ScifiTrekWarsSelection();
                                   collapsingToolbarLayout.setTitle("Sci-Fi - Trek Wars");
                                   Toast.makeText(MainActivity.this, "Opening SciFi - Star Wars / Star Trek", Toast.LENGTH_SHORT).show();
                                   break;
                               case 3: // Sci-Fi Other Universes
                                   View view3 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view3);
                                   collapsingToolbarLayout.setTitle("Sci-Fi - Other");
                                   Toast.makeText(MainActivity.this, "Opening SciFi - Other Universes", Toast.LENGTH_SHORT).show();
                                   break;
                               case 4: //Defines the divider lines within the Navigation Menu
                               case 7:
                               case 10:
                               case 13:
                                   break;
                               case 5: // Fantasy Lord of the Rings
                                   View view5 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view5);
                                   collapsingToolbarLayout.setTitle("Fantasy - LotR");
                                   Toast.makeText(MainActivity.this, "Opening Fantasy - Lord of the Rings", Toast.LENGTH_SHORT).show();
                                   break;
                               case 6: //Fantasy Harry Potter
                                   View view6 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view6);
                                   collapsingToolbarLayout.setTitle("Fantasy - Harry Potter");
                                   Toast.makeText(MainActivity.this, "Opening Fantasy - Harry Potter", Toast.LENGTH_SHORT).show();
                                   break;
                               case 8: //Comics on Television
                                   View view8 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view8);
                                   collapsingToolbarLayout.setTitle("Comics - Television");
                                   Toast.makeText(MainActivity.this, "Opening Comics - Television", Toast.LENGTH_SHORT).show();
                                   break;
                               case 9: //Comics in Film
                                   View view9 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view9);
                                   collapsingToolbarLayout.setTitle("Comics - Feature Films");
                                   Toast.makeText(MainActivity.this, "Opening Comics - Feature Films", Toast.LENGTH_SHORT).show();
                                   break;
                               case 11: //Netflix / Hulu / Amazon Original Content
                                   View view11 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view11);
                                   collapsingToolbarLayout.setTitle("Netflix/Hulu/Amazon");
                                   Toast.makeText(MainActivity.this, "Opening Streaming - Netflix/Hulu/Amazon", Toast.LENGTH_SHORT).show();
                                   break;
                               case 12: // Anime and Animation
                                   View view12 = getLayoutInflater().inflate(R.layout.coming_soon,mainLayout,false);
                                   mainLayout.addView(view12);
                                   collapsingToolbarLayout.setTitle("Anime & Animation");
                                   Toast.makeText(MainActivity.this, "Opening Animation & Anime", Toast.LENGTH_SHORT).show();
                                   break;
                               case 14: // Scoring and Share
                                   View view14 = getLayoutInflater().inflate(R.layout.scores_and_sharing,mainLayout,false);
                                   mainLayout.addView(view14);
                                   Button resetAll = findViewById(R.id.reset_scores);
                                   resetAll.setOnClickListener(MainActivity.this);
                                   updateScoringAllCategories();
                                   //Implement Activity Swap
                                   collapsingToolbarLayout.setTitle("Share Your Scores");
                                   Toast.makeText(MainActivity.this, "Opening Scores & Sharing", Toast.LENGTH_SHORT).show();
                                   break;
                           }

                           return false;
                       }
                   }
                )
                .build();
        fillFab();

    }

    /*
    Process the details for SciFi - Television
    */
    private void ScifiTelevisionSelection() {
        View view1 = getLayoutInflater().inflate(R.layout.scifi_television,mainLayout,false);
        mainLayout.addView(view1);
        //Check to see if question has been answered
        if (scifiQ1answered) {
            String q1String = String.valueOf(scifiQ1Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q1String) {
                case "true":
                    setSciFiQuestionOneCorrect();
                    break;
                case "false":
                    setSciFiQuestionOneIncorrect();
                    break;
            }
        } else {
            Button b1 = findViewById(R.id.scifi_tv_question_one_answer_one);
            Button b2 = findViewById(R.id.scifi_tv_question_one_answer_two);
            Button b3 = findViewById(R.id.scifi_tv_question_one_answer_three);
            Button b4 = findViewById(R.id.scifi_tv_question_one_answer_four);
            b1.setOnClickListener(MainActivity.this);
            b2.setOnClickListener(MainActivity.this);
            b3.setOnClickListener(MainActivity.this);
            b4.setOnClickListener(MainActivity.this);
        }
        if (scifiQ2answered) {
            String q2String = String.valueOf(scifiQ2Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q2String) {
                case "true":
                    setSciFiQuestionTwoCorrect();
                    break;
                case "false":
                    setSciFiQuestionTwoIncorrect();
                    break;
            }
        } else {
            Button b5 = findViewById(R.id.scifi_tv_question_two_answer_one);
            Button b6 = findViewById(R.id.scifi_tv_question_two_answer_two);
            Button b7 = findViewById(R.id.scifi_tv_question_two_answer_three);
            Button b8 = findViewById(R.id.scifi_tv_question_two_answer_four);
            b5.setOnClickListener(MainActivity.this);
            b6.setOnClickListener(MainActivity.this);
            b7.setOnClickListener(MainActivity.this);
            b8.setOnClickListener(MainActivity.this);
        }
        if (scifiQ3answered) {
            String q3String = String.valueOf(scifiQ3Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q3String) {
                case "true":
                    setSciFiQuestionThreeCorrect();
                    break;
                case "false":
                    setSciFiQuestionThreeIncorrect();
                    break;
            }
        } else {
            Button b9 = findViewById(R.id.scifi_tv_question_three_answer_one);
            Button b10 = findViewById(R.id.scifi_tv_question_three_answer_two);
            Button b11 = findViewById(R.id.scifi_tv_question_three_answer_three);
            Button b12 = findViewById(R.id.scifi_tv_question_three_answer_four);
            b9.setOnClickListener(MainActivity.this);
            b10.setOnClickListener(MainActivity.this);
            b11.setOnClickListener(MainActivity.this);
            b12.setOnClickListener(MainActivity.this);
        }
        if (scifiQ4answered) {
            String q4String = String.valueOf(scifiQ4Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q4String) {
                case "true":
                    setSciFiQuestionFourCorrect();
                    break;
                case "false":
                    setSciFiQuestionFourIncorrect();
                    break;
            }
        } else {
            Button b13 = findViewById(R.id.scifi_tv_question_four_answer_one);
            Button b14 = findViewById(R.id.scifi_tv_question_four_answer_two);
            Button b15 = findViewById(R.id.scifi_tv_question_four_answer_three);
            Button b16 = findViewById(R.id.scifi_tv_question_four_answer_four);
            b13.setOnClickListener(MainActivity.this);
            b14.setOnClickListener(MainActivity.this);
            b15.setOnClickListener(MainActivity.this);
            b16.setOnClickListener(MainActivity.this);
        }
        if (scifiQ5answered) {
            String q5String = String.valueOf(scifiQ5Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q5String) {
                case "true":
                    setSciFiQuestionFiveCorrect();
                    break;
                case "false":
                    setSciFiQuestionFiveIncorrect();
                    break;
            }
        } else {
            Button b17 = findViewById(R.id.scifi_tv_question_five_answer_one);
            Button b18 = findViewById(R.id.scifi_tv_question_five_answer_two);
            Button b19 = findViewById(R.id.scifi_tv_question_five_answer_three);
            Button b20 = findViewById(R.id.scifi_tv_question_five_answer_four);
            b17.setOnClickListener(MainActivity.this);
            b18.setOnClickListener(MainActivity.this);
            b19.setOnClickListener(MainActivity.this);
            b20.setOnClickListener(MainActivity.this);
        }
    }
    /*
    Process the details for SciFi - Trek Wars
    */
    private void ScifiTrekWarsSelection() {
        View view2 = getLayoutInflater().inflate(R.layout.scifi_trekwars,mainLayout,false);
        mainLayout.addView(view2);
        //Check to see if question has been answered
        if (trekwarsQ1answered) {
            String q1String = String.valueOf(trekwarsQ1Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q1String) {
                case "true":
                    setTrekWarsQuestionOneCorrect();
                    break;
                case "false":
                    setTrekWarsQuestionOneIncorrect();
                    break;
            }
        } else {
            Button b1 = findViewById(R.id.trek_question_one_answer_one);
            Button b2 = findViewById(R.id.trek_question_one_answer_two);
            Button b3 = findViewById(R.id.trek_question_one_answer_three);
            Button b4 = findViewById(R.id.trek_question_one_answer_four);
            b1.setOnClickListener(MainActivity.this);
            b2.setOnClickListener(MainActivity.this);
            b3.setOnClickListener(MainActivity.this);
            b4.setOnClickListener(MainActivity.this);
        }
        if (trekwarsQ2answered) {
            String q2String = String.valueOf(trekwarsQ2Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q2String) {
                case "true":
                    setTrekWarsQuestionTwoCorrect();
                    break;
                case "false":
                    setTrekWarsQuestionTwoIncorrect();
                    break;
            }
        } else {
            Button b5 = findViewById(R.id.trek_question_two_answer_one);
            Button b6 = findViewById(R.id.trek_question_two_answer_two);
            Button b7 = findViewById(R.id.trek_question_two_answer_three);
            Button b8 = findViewById(R.id.trek_question_two_answer_four);
            b5.setOnClickListener(MainActivity.this);
            b6.setOnClickListener(MainActivity.this);
            b7.setOnClickListener(MainActivity.this);
            b8.setOnClickListener(MainActivity.this);
        }
        if (trekwarsQ3answered) {
            String q3String = String.valueOf(trekwarsQ3Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q3String) {
                case "true":
                    setTrekWarsQuestionThreeCorrect();
                    break;
                case "false":
                    setTrekWarsQuestionThreeIncorrect();
                    break;
            }
        } else {
            Button b9 = findViewById(R.id.trek_question_three_answer_one);
            Button b10 = findViewById(R.id.trek_question_three_answer_two);
            Button b11 = findViewById(R.id.trek_question_three_answer_three);
            Button b12 = findViewById(R.id.trek_question_three_answer_four);
            b9.setOnClickListener(MainActivity.this);
            b10.setOnClickListener(MainActivity.this);
            b11.setOnClickListener(MainActivity.this);
            b12.setOnClickListener(MainActivity.this);
        }
        if (trekwarsQ4answered) {
            String q4String = String.valueOf(trekwarsQ4Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q4String) {
                case "true":
                    setTrekWarsQuestionFourCorrect();
                    break;
                case "false":
                    setTrekWarsQuestionFourIncorrect();
                    break;
            }
        } else {
            Button b13 = findViewById(R.id.trek_question_four_answer_one);
            Button b14 = findViewById(R.id.trek_question_four_answer_two);
            b13.setOnClickListener(MainActivity.this);
            b14.setOnClickListener(MainActivity.this);
        }
        if (trekwarsQ5answered) {
            String q5String = String.valueOf(trekwarsQ5Correct);
            //switch statement checks for true/false value of question one to update the field views
            switch (q5String) {
                case "true":
                    setTrekWarsQuestionFiveCorrect();
                    break;
                case "false":
                    setTrekWarsQuestionFiveIncorrect();
                    break;
            }
        } else {
            Button b15 = findViewById(R.id.trek_question_five_answer_one);
            Button b16 = findViewById(R.id.trek_question_five_answer_two);
            Button b17 = findViewById(R.id.trek_question_five_answer_three);
            Button b18 = findViewById(R.id.trek_question_five_answer_four);
            b15.setOnClickListener(MainActivity.this);
            b16.setOnClickListener(MainActivity.this);
            b17.setOnClickListener(MainActivity.this);
            b18.setOnClickListener(MainActivity.this);
        }
    }

    /*
    * Process the particular updates for scoring categories
    * Display the values on the final sheet when requested from drawer navigation
    * TODO: Still need to add all the remaining scoring categories
    * TODO: Add logic to colorize the correct % if less than 50% (Red) or greater than 50% (colorPrimary)
    * TODO: Add logic to colorize the # of answered questions if != to the total
    * TODO: Add a grand total sumarization ... (Possible last minute addition or future enhancement)
    */
    private void updateScoringAllCategories() {
        setSciFiTelevisionScore();
        setSciFiTrekWarsScore();


    }

    /*
     * Field updates depending on correct/incorrect for each question
     * Pertaining specifically to SciFi Television
     */
    private void setSciFiQuestionOneCorrect() {
        TextView scifi_tv_question_one = findViewById(R.id.scifi_tv_question_one_result_on_click);
        LinearLayout scifi_tv_question_one_display = findViewById(R.id.scifi_tv_button_view_question_one);
        LinearLayout scifi_tv_question_one_display2 = findViewById(R.id.scifi_tv_button_view2_question_one);
        ImageView scifi_tv_question_one_image = findViewById(R.id.scifi_tv_imageview_question_one);
        scifi_tv_question_one_image.setVisibility(GONE);
        scifi_tv_question_one_display.setVisibility(GONE);
        scifi_tv_question_one_display2.setVisibility(GONE);
        scifi_tv_question_one.setVisibility(VISIBLE);
        scifi_tv_question_one.setTextColor(Color.GREEN);
        scifi_tv_question_one.setText("CORRECT");
    }
    private void setSciFiQuestionOneIncorrect() {
        TextView scifi_tv_question_one = findViewById(R.id.scifi_tv_question_one_result_on_click);
        LinearLayout scifi_tv_question_one_display = findViewById(R.id.scifi_tv_button_view_question_one);
        LinearLayout scifi_tv_question_one_display2 = findViewById(R.id.scifi_tv_button_view2_question_one);
        ImageView scifi_tv_question_one_image = findViewById(R.id.scifi_tv_imageview_question_one);
        scifi_tv_question_one_image.setVisibility(GONE);
        scifi_tv_question_one_display.setVisibility(GONE);
        scifi_tv_question_one_display2.setVisibility(GONE);
        scifi_tv_question_one.setVisibility(VISIBLE);
        scifi_tv_question_one.setTextColor(Color.RED);
        scifi_tv_question_one.setText("INCORRECT");
    }
    private void setSciFiQuestionTwoCorrect() {
        TextView scifi_tv_question_two = findViewById(R.id.scifi_tv_question_two_result_on_click);
        LinearLayout scifi_tv_question_two_display = findViewById(R.id.scifi_tv_button_view_question_two);
        LinearLayout scifi_tv_question_two_display2 = findViewById(R.id.scifi_tv_button_view2_question_two);
        ImageView scifi_tv_question_two_image = findViewById(R.id.scifi_tv_imageview_question_two);
        scifi_tv_question_two_image.setVisibility(GONE);
        scifi_tv_question_two_display.setVisibility(GONE);
        scifi_tv_question_two_display2.setVisibility(GONE);
        scifi_tv_question_two.setVisibility(VISIBLE);
        scifi_tv_question_two.setTextColor(Color.GREEN);
        scifi_tv_question_two.setText("CORRECT");
    }
    private void setSciFiQuestionTwoIncorrect() {
        TextView scifi_tv_question_two = findViewById(R.id.scifi_tv_question_two_result_on_click);
        LinearLayout scifi_tv_question_two_display = findViewById(R.id.scifi_tv_button_view_question_two);
        LinearLayout scifi_tv_question_two_display2 = findViewById(R.id.scifi_tv_button_view2_question_two);
        ImageView scifi_tv_question_two_image = findViewById(R.id.scifi_tv_imageview_question_two);
        scifi_tv_question_two_image.setVisibility(GONE);
        scifi_tv_question_two_display.setVisibility(GONE);
        scifi_tv_question_two_display2.setVisibility(GONE);
        scifi_tv_question_two.setVisibility(VISIBLE);
        scifi_tv_question_two.setTextColor(Color.RED);
        scifi_tv_question_two.setText("INCORRECT");
    }
    private void setSciFiQuestionThreeCorrect() {
        TextView scifi_tv_question_three = findViewById(R.id.scifi_tv_question_three_result_on_click);
        LinearLayout scifi_tv_question_three_display = findViewById(R.id.scifi_tv_button_view_question_three);
        LinearLayout scifi_tv_question_three_display2 = findViewById(R.id.scifi_tv_button_view2_question_three);
        ImageView scifi_tv_question_three_image = findViewById(R.id.scifi_tv_imageview_question_three);
        scifi_tv_question_three_image.setVisibility(GONE);
        scifi_tv_question_three_display.setVisibility(GONE);
        scifi_tv_question_three_display2.setVisibility(GONE);
        scifi_tv_question_three.setVisibility(VISIBLE);
        scifi_tv_question_three.setTextColor(Color.GREEN);
        scifi_tv_question_three.setText("CORRECT");
    }
    private void setSciFiQuestionThreeIncorrect() {
        TextView scifi_tv_question_three = findViewById(R.id.scifi_tv_question_three_result_on_click);
        LinearLayout scifi_tv_question_three_display = findViewById(R.id.scifi_tv_button_view_question_three);
        LinearLayout scifi_tv_question_three_display2 = findViewById(R.id.scifi_tv_button_view2_question_three);
        ImageView scifi_tv_question_three_image = findViewById(R.id.scifi_tv_imageview_question_three);
        scifi_tv_question_three_image.setVisibility(GONE);
        scifi_tv_question_three_display.setVisibility(GONE);
        scifi_tv_question_three_display2.setVisibility(GONE);
        scifi_tv_question_three.setVisibility(VISIBLE);
        scifi_tv_question_three.setTextColor(Color.RED);
        scifi_tv_question_three.setText("INCORRECT");
    }
    private void setSciFiQuestionFourCorrect() {
        TextView scifi_tv_question_four = findViewById(R.id.scifi_tv_question_four_result_on_click);
        LinearLayout scifi_tv_question_four_display = findViewById(R.id.scifi_tv_button_view_question_four);
        LinearLayout scifi_tv_question_four_display2 = findViewById(R.id.scifi_tv_button_view2_question_four);
        ImageView scifi_tv_question_four_image = findViewById(R.id.scifi_tv_imageview_question_four);
        scifi_tv_question_four_image.setVisibility(GONE);
        scifi_tv_question_four_display.setVisibility(GONE);
        scifi_tv_question_four_display2.setVisibility(GONE);
        scifi_tv_question_four.setVisibility(VISIBLE);
        scifi_tv_question_four.setTextColor(Color.GREEN);
        scifi_tv_question_four.setText("CORRECT");
    }
    private void setSciFiQuestionFourIncorrect() {
        TextView scifi_tv_question_four = findViewById(R.id.scifi_tv_question_four_result_on_click);
        LinearLayout scifi_tv_question_four_display = findViewById(R.id.scifi_tv_button_view_question_four);
        LinearLayout scifi_tv_question_four_display2 = findViewById(R.id.scifi_tv_button_view2_question_four);
        ImageView scifi_tv_question_four_image = findViewById(R.id.scifi_tv_imageview_question_four);
        scifi_tv_question_four_image.setVisibility(GONE);
        scifi_tv_question_four_display.setVisibility(GONE);
        scifi_tv_question_four_display2.setVisibility(GONE);
        scifi_tv_question_four.setVisibility(VISIBLE);
        scifi_tv_question_four.setTextColor(Color.RED);
        scifi_tv_question_four.setText("INCORRECT");
    }
    private void setSciFiQuestionFiveCorrect() {
        TextView scifi_tv_question_five = findViewById(R.id.scifi_tv_question_five_result_on_click);
        LinearLayout scifi_tv_question_five_display = findViewById(R.id.scifi_tv_button_view_question_five);
        LinearLayout scifi_tv_question_five_display2 = findViewById(R.id.scifi_tv_button_view2_question_five);
        ImageView scifi_tv_question_five_image = findViewById(R.id.scifi_tv_imageview_question_five);
        scifi_tv_question_five_image.setVisibility(GONE);
        scifi_tv_question_five_display.setVisibility(GONE);
        scifi_tv_question_five_display2.setVisibility(GONE);
        scifi_tv_question_five.setVisibility(VISIBLE);
        scifi_tv_question_five.setTextColor(Color.GREEN);
        scifi_tv_question_five.setText("CORRECT");
    }
    private void setSciFiQuestionFiveIncorrect() {
        TextView scifi_tv_question_five = findViewById(R.id.scifi_tv_question_five_result_on_click);
        LinearLayout scifi_tv_question_five_display = findViewById(R.id.scifi_tv_button_view_question_five);
        LinearLayout scifi_tv_question_five_display2 = findViewById(R.id.scifi_tv_button_view2_question_five);
        ImageView scifi_tv_question_five_image = findViewById(R.id.scifi_tv_imageview_question_five);
        scifi_tv_question_five_image.setVisibility(GONE);
        scifi_tv_question_five_display.setVisibility(GONE);
        scifi_tv_question_five_display2.setVisibility(GONE);
        scifi_tv_question_five.setVisibility(VISIBLE);
        scifi_tv_question_five.setTextColor(Color.RED);
        scifi_tv_question_five.setText("INCORRECT");
    }
    private void setSciFiTelevisionScore() {
        TextView scifi_questions_answered = findViewById(R.id.scifi_tv_answered_questions);
        TextView scifi_score_total_display = findViewById(R.id.scifi_tv_percentage_score);
        double scififinalscore = 0.0;
        if (scifiQ1Correct) {
            if (scifiQ1scored) {

            } else {
                scififinalscore = scififinalscore + 1.0;
                scifiQ1scored = true;
            }
        }
        if (scifiQ2Correct) {
            if (scifiQ2scored) {

            } else {
                scififinalscore = scififinalscore + 1.0;
                scifiQ2scored = true;
            }
        }
        if (scifiQ3Correct) {
            if (scifiQ3scored) {

            } else {
                scififinalscore = scififinalscore + 1.0;
                scifiQ3scored = true;
            }
        }
        if (scifiQ4Correct) {
            if (scifiQ4scored) {

            } else {
                scififinalscore = scififinalscore + 1.0;
                scifiQ4scored = true;
            }
        }
        if (scifiQ5Correct) {
            if (scifiQ5scored) {

            } else {
                scififinalscore = scififinalscore + 1.0;
                scifiQ5scored = true;
            }
        }
        Log.i(String.valueOf(scififinalscore), "updateScoringAllCategories: final score");
        NumberFormat numberformat = NumberFormat.getNumberInstance();
        numberformat.setMinimumFractionDigits(2);
        String scifitext = numberformat.format((scififinalscore * 100) / 5.0);
        Log.i(String.valueOf(result), "updateScoringAllCategories: double result ");
        scifitext = scifitext + "%";
        scifi_score_total_display.setText(scifitext);

        if (scifiQ1answered) {
            sciFiTelevisionScore = sciFiTelevisionScore +1;
        }
        if (scifiQ2answered) {
            sciFiTelevisionScore = sciFiTelevisionScore +1;
        }
        if (scifiQ3answered) {
            sciFiTelevisionScore = sciFiTelevisionScore +1;
        }
        if (scifiQ4answered) {
            sciFiTelevisionScore = sciFiTelevisionScore +1;
        }
        if (scifiQ5answered) {
            sciFiTelevisionScore = sciFiTelevisionScore +1;
        }
        String scifiquestionsanswered = String.valueOf(sciFiTelevisionScore);
        scifiquestionsanswered = scifiquestionsanswered + " / 5";
        scifi_questions_answered.setText(scifiquestionsanswered);
    }

    /*
     * Field updates depending on correct/incorrect for each question
     * Pertaining specifically to SciFi Trek / Wars
     */
    private void setTrekWarsQuestionOneCorrect() {
        TextView scifi_trek_question_one = findViewById(R.id.trek_question_one_result_on_click);
        LinearLayout scifi_trek_question_one_display = findViewById(R.id.trek_button_view_question_one);
        LinearLayout scifi_trek_question_one_display2 = findViewById(R.id.trek_button_view2_question_one);
        ImageView scifi_trek_question_one_image = findViewById(R.id.trek_imageview_question_one);
        scifi_trek_question_one_image.setVisibility(GONE);
        scifi_trek_question_one_display.setVisibility(GONE);
        scifi_trek_question_one_display2.setVisibility(GONE);
        scifi_trek_question_one.setVisibility(VISIBLE);
        scifi_trek_question_one.setTextColor(Color.GREEN);
        scifi_trek_question_one.setText("CORRECT");
    }
    private void setTrekWarsQuestionOneIncorrect() {
        TextView scifi_trek_question_one = findViewById(R.id.trek_question_one_result_on_click);
        LinearLayout scifi_trek_question_one_display = findViewById(R.id.trek_button_view_question_one);
        LinearLayout scifi_trek_question_one_display2 = findViewById(R.id.trek_button_view2_question_one);
        ImageView scifi_trek_question_one_image = findViewById(R.id.trek_imageview_question_one);
        scifi_trek_question_one_image.setVisibility(GONE);
        scifi_trek_question_one_display.setVisibility(GONE);
        scifi_trek_question_one_display2.setVisibility(GONE);
        scifi_trek_question_one.setVisibility(VISIBLE);
        scifi_trek_question_one.setTextColor(Color.RED);
        scifi_trek_question_one.setText("INCORRECT");
    }
    private void setTrekWarsQuestionTwoCorrect() {
        TextView scifi_trek_question_two = findViewById(R.id.trek_question_two_result_on_click);
        LinearLayout scifi_trek_question_two_display = findViewById(R.id.trek_button_view_question_two);
        LinearLayout scifi_trek_question_two_display2 = findViewById(R.id.trek_button_view2_question_two);
        ImageView scifi_trek_question_two_image = findViewById(R.id.trek_imageview_question_two);
        scifi_trek_question_two_image.setVisibility(GONE);
        scifi_trek_question_two_display.setVisibility(GONE);
        scifi_trek_question_two_display2.setVisibility(GONE);
        scifi_trek_question_two.setVisibility(VISIBLE);
        scifi_trek_question_two.setTextColor(Color.GREEN);
        scifi_trek_question_two.setText("CORRECT");
    }
    private void setTrekWarsQuestionTwoIncorrect() {
        TextView scifi_trek_question_two = findViewById(R.id.trek_question_two_result_on_click);
        LinearLayout scifi_trek_question_two_display = findViewById(R.id.trek_button_view_question_two);
        LinearLayout scifi_trek_question_two_display2 = findViewById(R.id.trek_button_view2_question_two);
        ImageView scifi_trek_question_two_image = findViewById(R.id.trek_imageview_question_two);
        scifi_trek_question_two_image.setVisibility(GONE);
        scifi_trek_question_two_display.setVisibility(GONE);
        scifi_trek_question_two_display2.setVisibility(GONE);
        scifi_trek_question_two.setVisibility(VISIBLE);
        scifi_trek_question_two.setTextColor(Color.RED);
        scifi_trek_question_two.setText("INCORRECT");
    }
    private void setTrekWarsQuestionThreeCorrect() {
        TextView scifi_trek_question_three = findViewById(R.id.trek_question_three_result_on_click);
        LinearLayout scifi_trek_question_three_display = findViewById(R.id.trek_button_view_question_three);
        LinearLayout scifi_trek_question_three_display2 = findViewById(R.id.trek_button_view2_question_three);
        ImageView scifi_trek_question_three_image = findViewById(R.id.trek_imageview_question_three);
        scifi_trek_question_three_image.setVisibility(GONE);
        scifi_trek_question_three_display.setVisibility(GONE);
        scifi_trek_question_three_display2.setVisibility(GONE);
        scifi_trek_question_three.setVisibility(VISIBLE);
        scifi_trek_question_three.setTextColor(Color.GREEN);
        scifi_trek_question_three.setText("CORRECT");
    }
    private void setTrekWarsQuestionThreeIncorrect() {
        TextView scifi_trek_question_three = findViewById(R.id.trek_question_three_result_on_click);
        LinearLayout scifi_trek_question_three_display = findViewById(R.id.trek_button_view_question_three);
        LinearLayout scifi_trek_question_three_display2 = findViewById(R.id.trek_button_view2_question_three);
        ImageView scifi_trek_question_three_image = findViewById(R.id.trek_imageview_question_three);
        scifi_trek_question_three_image.setVisibility(GONE);
        scifi_trek_question_three_display.setVisibility(GONE);
        scifi_trek_question_three_display2.setVisibility(GONE);
        scifi_trek_question_three.setVisibility(VISIBLE);
        scifi_trek_question_three.setTextColor(Color.RED);
        scifi_trek_question_three.setText("INCORRECT");
    }
    private void setTrekWarsQuestionFourCorrect() {
        TextView scifi_trek_question_four = findViewById(R.id.trek_question_four_result_on_click);
        LinearLayout scifi_trek_question_four_display = findViewById(R.id.trek_button_view_question_four);
        ImageView scifi_trek_question_four_image = findViewById(R.id.trek_imageview_question_four);
        scifi_trek_question_four_image.setVisibility(GONE);
        scifi_trek_question_four_display.setVisibility(GONE);
        scifi_trek_question_four.setVisibility(VISIBLE);
        scifi_trek_question_four.setTextColor(Color.GREEN);
        scifi_trek_question_four.setText("CORRECT");
    }
    private void setTrekWarsQuestionFourIncorrect() {
        TextView scifi_trek_question_four = findViewById(R.id.trek_question_four_result_on_click);
        LinearLayout scifi_trek_question_four_display = findViewById(R.id.trek_button_view_question_four);
        ImageView scifi_trek_question_four_image = findViewById(R.id.trek_imageview_question_four);
        scifi_trek_question_four_image.setVisibility(GONE);
        scifi_trek_question_four_display.setVisibility(GONE);
        scifi_trek_question_four.setVisibility(VISIBLE);
        scifi_trek_question_four.setTextColor(Color.RED);
        scifi_trek_question_four.setText("INCORRECT");
    }
    private void setTrekWarsQuestionFiveCorrect() {
        TextView scifi_trek_question_five = findViewById(R.id.trek_question_five_result_on_click);
        LinearLayout scifi_trek_question_five_display = findViewById(R.id.trek_button_view_question_five);
        LinearLayout scifi_trek_question_five_display2 = findViewById(R.id.trek_button_view2_question_five);
        ImageView scifi_trek_question_five_image = findViewById(R.id.trek_imageview_question_five);
        scifi_trek_question_five_image.setVisibility(GONE);
        scifi_trek_question_five_display.setVisibility(GONE);
        scifi_trek_question_five_display2.setVisibility(GONE);
        scifi_trek_question_five.setVisibility(VISIBLE);
        scifi_trek_question_five.setTextColor(Color.GREEN);
        scifi_trek_question_five.setText("CORRECT");
    }
    private void setTrekWarsQuestionFiveIncorrect() {
        TextView scifi_trek_question_five = findViewById(R.id.trek_question_five_result_on_click);
        LinearLayout scifi_trek_question_five_display = findViewById(R.id.trek_button_view_question_five);
        LinearLayout scifi_trek_question_five_display2 = findViewById(R.id.trek_button_view2_question_five);
        ImageView scifi_trek_question_five_image = findViewById(R.id.trek_imageview_question_five);
        scifi_trek_question_five_image.setVisibility(GONE);
        scifi_trek_question_five_display.setVisibility(GONE);
        scifi_trek_question_five_display2.setVisibility(GONE);
        scifi_trek_question_five.setVisibility(VISIBLE);
        scifi_trek_question_five.setTextColor(Color.RED);
        scifi_trek_question_five.setText("INCORRECT");
    }
    private void setSciFiTrekWarsScore() {
        TextView scifi_trekwars_questions_answered = findViewById(R.id.scifi_trekwars_answered_questions);
        TextView scifi_trekwars_score_total_display = findViewById(R.id.scifi_trekwars_percentage_score);
        double trekwarsfinalscore = 0.0;
        if (trekwarsQ1Correct) {
            trekwarsfinalscore = trekwarsfinalscore + 1.0;
        }
        if (trekwarsQ2Correct) {
            trekwarsfinalscore = trekwarsfinalscore + 1.0;
        }
        if (trekwarsQ3Correct) {
            trekwarsfinalscore = trekwarsfinalscore + 1.0;
        }
        if (trekwarsQ4Correct) {
            trekwarsfinalscore = trekwarsfinalscore + 1.0;
        }
        if (trekwarsQ5Correct) {
            trekwarsfinalscore = trekwarsfinalscore + 1.0;
        }
        NumberFormat numberformat = NumberFormat.getNumberInstance();
        numberformat.setMinimumFractionDigits(2);
        String trekwarstext = numberformat.format((trekwarsfinalscore * 100) / 5.0);
        trekwarstext = trekwarstext + "%";
        scifi_trekwars_score_total_display.setText(trekwarstext);

        if (trekwarsQ1answered) {
            if (trekwarsQ1scored) {
            } else {
                sciFiTrekWarsScore = sciFiTrekWarsScore + 1;
                trekwarsQ1scored = true;
            }
        }
        if (trekwarsQ2answered) {
            if (trekwarsQ2scored) {
            } else {
                sciFiTrekWarsScore = sciFiTrekWarsScore + 1;
                trekwarsQ2scored = true;
            }
        }
        if (trekwarsQ3answered) {
            if (trekwarsQ3scored) {
            } else {
                sciFiTrekWarsScore = sciFiTrekWarsScore + 1;
                trekwarsQ3scored = true;
            }
        }
        if (trekwarsQ4answered) {
            if (trekwarsQ4scored) {
            } else {
                sciFiTrekWarsScore = sciFiTrekWarsScore + 1;
                trekwarsQ4scored = true;
            }
        }
        if (trekwarsQ5answered) {
            if (trekwarsQ5scored) {
            } else {
                sciFiTrekWarsScore = sciFiTrekWarsScore + 1;
                trekwarsQ5scored = true;
            }
        }
        String trekwarsquestionsanswered = String.valueOf(sciFiTrekWarsScore);
        trekwarsquestionsanswered = trekwarsquestionsanswered + " / 5";
        scifi_trekwars_questions_answered.setText(trekwarsquestionsanswered);
    }


    /*
    * Defines the floating action button and what happens when it is clicked
    * Outcome for onClick changes depending on which activity is currently inflated
    * TODO: Add the remaining cases for Toast display
    */
    private void fillFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fab.setBackgroundColor(Color.WHITE);
        fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_lightbulb_outline).actionBar().color(Color.BLACK));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentView = result.getCurrentSelectedPosition();
                Log.i(String.valueOf(currentView), "onClick: view ID is -- ");
                switch (currentView) {
                    case 1:
                        Toast.makeText(MainActivity.this, "Hint: This category is all about television shows", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Hint: This category is all about Star Trek and Star Wars", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /**
     * This particular onClick listener focuses on the button clicks across views.
     * Since the layouts are inflated within the NestedScrollView, the button clicks are always within this view.
     * TODO: Add the remaining onClick ids for TrekWars (and eventually remaining categories)
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int clicker = v.getId();
        switch (clicker) {
            case R.id.start_quiz_button:
                EditText nameEntryStartGame = findViewById(R.id.user_name_declaration);
                if (nameEntryStartGame.getText().toString().equals("") || nameEntryStartGame.getText().toString().equals(" ")) {
                    Toast.makeText(this, "Please enter your name to play", Toast.LENGTH_SHORT).show();
                } else {
                    String player_name = nameEntryStartGame.getText().toString();
                    headerResult.addProfiles(new ProfileDrawerItem().withName(player_name).withEmail("Current Nerd Challenger").withSelectedColor(getResources().getColor(R.color.colorPrimary)).withIcon(GoogleMaterial.Icon.gmd_stars));
                    result.openDrawer();
                }
                break;
            //SCI-FI TELEVISION BUTTONS
            case R.id.scifi_tv_question_one_answer_one:
                scifiQ1answered = true;
                scifiQ1Correct = false;
                setSciFiQuestionOneIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_one_answer_two:
                scifiQ1answered = true;
                scifiQ1Correct = true;
                setSciFiQuestionOneCorrect();
                SciFiTelevisionCorrect = SciFiTelevisionCorrect + 1;
                break;
            case R.id.scifi_tv_question_one_answer_three:
                scifiQ1answered = true;
                scifiQ1Correct = false;
                setSciFiQuestionOneIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_one_answer_four:
                scifiQ1answered = true;
                scifiQ1Correct = false;
                setSciFiQuestionOneIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_two_answer_one:
                scifiQ2answered = true;
                scifiQ2Correct = true;
                setSciFiQuestionTwoCorrect();
                SciFiTelevisionCorrect = SciFiTelevisionCorrect + 1;
                break;
            case R.id.scifi_tv_question_two_answer_two:
                scifiQ2answered = true;
                scifiQ2Correct = false;
                setSciFiQuestionTwoIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_two_answer_three:
                scifiQ2answered = true;
                scifiQ2Correct = false;
                setSciFiQuestionTwoIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_two_answer_four:
                scifiQ2answered = true;
                scifiQ2Correct = false;
                setSciFiQuestionTwoIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_three_answer_one:
                scifiQ3answered = true;
                scifiQ3Correct = false;
                setSciFiQuestionThreeIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_three_answer_two:
                scifiQ3answered = true;
                scifiQ3Correct = false;
                setSciFiQuestionThreeIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_three_answer_three:
                scifiQ3answered = true;
                scifiQ3Correct = true;
                setSciFiQuestionThreeCorrect();
                SciFiTelevisionCorrect = SciFiTelevisionCorrect + 1;
                break;
            case R.id.scifi_tv_question_three_answer_four:
                scifiQ3answered = true;
                scifiQ3Correct = false;
                setSciFiQuestionThreeIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_four_answer_one:
                scifiQ4answered = true;
                scifiQ4Correct = false;
                setSciFiQuestionFourIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_four_answer_two:
                scifiQ4answered = true;
                scifiQ4Correct = true;
                setSciFiQuestionFourCorrect();
                SciFiTelevisionCorrect = SciFiTelevisionCorrect + 1;
                break;
            case R.id.scifi_tv_question_four_answer_three:
                scifiQ4answered = true;
                scifiQ4Correct = false;
                setSciFiQuestionFourIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_four_answer_four:
                scifiQ4answered = true;
                scifiQ4Correct = false;
                setSciFiQuestionFourIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_five_answer_one:
                scifiQ5answered = true;
                scifiQ5Correct = true;
                setSciFiQuestionFiveCorrect();
                SciFiTelevisionCorrect = SciFiTelevisionCorrect + 1;
                break;
            case R.id.scifi_tv_question_five_answer_two:
                scifiQ5answered = true;
                scifiQ5Correct = false;
                setSciFiQuestionFiveIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_five_answer_three:
                scifiQ5answered = true;
                scifiQ5Correct = false;
                setSciFiQuestionFiveIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;
            case R.id.scifi_tv_question_five_answer_four:
                scifiQ5answered = true;
                scifiQ5Correct = false;
                setSciFiQuestionFiveIncorrect();
                SciFiTelevisionIncorrect = SciFiTelevisionIncorrect + 1;
                break;

            //STAR TREK / STAR WARS BUTTONS
            case R.id.trek_question_one_answer_one:
                trekwarsQ1answered = true;
                trekwarsQ1Correct = false;
                setTrekWarsQuestionOneIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_one_answer_two:
                trekwarsQ1answered = true;
                trekwarsQ1Correct = true;
                setTrekWarsQuestionOneCorrect();
                SciFiTrekWarsCorrect = SciFiTrekWarsCorrect + 1;
                break;
            case R.id.trek_question_one_answer_three:
                trekwarsQ1answered = true;
                trekwarsQ1Correct = false;
                setTrekWarsQuestionOneIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_one_answer_four:
                trekwarsQ1answered = true;
                trekwarsQ1Correct = false;
                setTrekWarsQuestionOneIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_two_answer_one:
                trekwarsQ2answered = true;
                trekwarsQ2Correct = false;
                setTrekWarsQuestionTwoIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_two_answer_two:
                trekwarsQ2answered = true;
                trekwarsQ2Correct = false;
                setTrekWarsQuestionTwoIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_two_answer_three:
                trekwarsQ2answered = true;
                trekwarsQ2Correct = false;
                setTrekWarsQuestionTwoIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_two_answer_four:
                trekwarsQ2answered = true;
                trekwarsQ2Correct = true;
                setTrekWarsQuestionTwoCorrect();
                SciFiTrekWarsCorrect = SciFiTrekWarsCorrect + 1;
                break;
            case R.id.trek_question_three_answer_one:
                trekwarsQ3answered = true;
                trekwarsQ3Correct = false;
                setTrekWarsQuestionThreeIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_three_answer_two:
                trekwarsQ3answered = true;
                trekwarsQ3Correct = false;
                setTrekWarsQuestionThreeIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_three_answer_three:
                trekwarsQ3answered = true;
                trekwarsQ3Correct = false;
                setTrekWarsQuestionThreeIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_three_answer_four:
                trekwarsQ3answered = true;
                trekwarsQ3Correct = true;
                setTrekWarsQuestionThreeCorrect();
                SciFiTrekWarsCorrect = SciFiTrekWarsCorrect + 1;
                break;
            case R.id.trek_question_four_answer_one:
                trekwarsQ4answered = true;
                trekwarsQ4Correct = true;
                setTrekWarsQuestionFourCorrect();
                SciFiTrekWarsCorrect = SciFiTrekWarsCorrect + 1;
                break;
            case R.id.trek_question_four_answer_two:
                trekwarsQ4answered = true;
                trekwarsQ4Correct = false;
                setTrekWarsQuestionFourIncorrect();
                SciFiTrekWarsCorrect = SciFiTrekWarsCorrect + 1;
                break;
            case R.id.trek_question_five_answer_one:
                trekwarsQ5answered = true;
                trekwarsQ5Correct = false;
                setTrekWarsQuestionFiveIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_five_answer_two:
                trekwarsQ5answered = true;
                trekwarsQ5Correct = false;
                setTrekWarsQuestionFiveIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.trek_question_five_answer_three:
                trekwarsQ5answered = true;
                trekwarsQ5Correct = true;
                setTrekWarsQuestionFiveCorrect();
                SciFiTrekWarsCorrect = SciFiTrekWarsCorrect + 1;
                break;
            case R.id.trek_question_five_answer_four:
                trekwarsQ5answered = true;
                trekwarsQ5Correct = false;
                setTrekWarsQuestionFiveIncorrect();
                SciFiTrekWarsIncorrect = SciFiTrekWarsIncorrect + 1;
                break;
            case R.id.reset_scores:
                onRequestResetScores();
                break;
            case R.id.next_category:
                onRequestResetScores();
                break;
        }
    }
    public void onRequestResetScores() {
        //Reset the quiz and all parameters
    }
}
