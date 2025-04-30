package androidhive.info.materialdesign.activity;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import androidhive.info.materialdesign.adapter.ExammodeAdapter;
import androidhive.info.materialdesign.adapter.ResultAdapter;
import androidhive.info.materialdesign.adapter.ShowReviewAdapter;
import androidhive.info.materialdesign.adapter.StudyAdapter;
import androidhive.info.materialdesign.data.Quizdata;
import androidhive.info.materialdesign.data.ResultData;
import androidhive.info.materialdesign.parser.QuizJSONParser;
import androidhive.info.materialdesign.R;
//import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ExamFragment extends Fragment {
    TextView proName,proGroup,knAria;
    ExamFragment frag;
    Context context;
    ArrayList<Quizdata> data;
    ListView list;
    int count;
    int iNext = 1;
    ListView reviewListview;
    ListView resultListview;
    View showInfoView;
    View reviewView;
    View resultView;
    AlertDialog alertDialog;
    RelativeLayout linearLayout;
    RadioGroup radioGroup;
    int device ;
    Quizdata dataSource;
    Button btnreTake,btnreTest,review;
    ArrayList<Quizdata> retakedata;
    View seakBarlayout;
    TextView textEnd,textStart,textGoalStart,textGoalend,textTimer;
    SeekBar seekBar,seekGoal;
    boolean pCheck;
    boolean nCheck;
    String url;
    Button priv;
    Button next,exp;
    ResultData resData;
    int check = 0;
    String urlFinish = "http://jmbok.avantgoutrestaurant.com/and/exam_result.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        frag = this;

    }
    public ExamFragment(){

    }
    public ExamFragment(String url){
        this.url = url;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        resData = new ResultData();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        float smallestWidth = Math.min(widthDp, heightDp);
        View rootView = null;
        if (smallestWidth > 720) {
            device = 10;
        }
        else if (smallestWidth >= 600) {
            device = 7; //Device is a 7" tablet
            rootView = inflater.inflate(R.layout.exam_mode_seven, container,
                    false);
        }else {
            rootView = inflater.inflate(R.layout.exam_mode, container,
                    false);
        }



        textTimer = (TextView)rootView.findViewById(R.id.timer);
        textTimer.setTextColor(Color.RED);
        LayoutInflater seakLayout = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//		 View layout = inflater.inflate(R.layout.seakbar_dialog, (ViewGroup)findViewById(R.id.your_dialog_root_element));
        seakBarlayout = seakLayout.inflate(R.layout.seakbar_dialog_layout,container, false);
        textEnd = (TextView)seakBarlayout.findViewById(R.id.countend);
        textStart = (TextView)seakBarlayout.findViewById(R.id.countstart);

        textGoalStart = (TextView)seakBarlayout.findViewById(R.id.goalstart);
        textGoalend = (TextView)seakBarlayout.findViewById(R.id.goalend);

        seekBar = (SeekBar)seakBarlayout.findViewById(R.id.que_seekbar);
        seekGoal = (SeekBar)seakBarlayout.findViewById(R.id.goal_seekbar);
        seekGoal.setMax(100);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {

            }

            public void onStartTrackingTouch(SeekBar arg0) {

            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

//	            	String progressString = String.valueOf(arg1 * 10);
                textStart.setText(String.valueOf(arg1));
                count = arg1;
            }
        });

        seekGoal.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {


            }

            public void onStartTrackingTouch(SeekBar arg0) {

            }

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

//	            	String progressString = String.valueOf(arg1 * 10);
                textGoalStart.setText(String.valueOf(arg1)+"%");

            }
        });
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        reviewView = mInflater.inflate(R.layout.show_review,container, false);

        reviewListview = (ListView)reviewView.findViewById(R.id.reviewListview);

        resultView = mInflater.inflate(R.layout.finish_layout,container, false);


        resultListview = (ListView)resultView.findViewById(R.id.resultListview);
        btnreTake = (Button)resultView.findViewById(R.id.retake);
        btnreTest = (Button)resultView.findViewById(R.id.reTest);
        review = (Button)resultView.findViewById(R.id.review);

        showInfoView = mInflater.inflate(R.layout.show_info,container, false);

        proName =(TextView)showInfoView.findViewById(R.id.proname);
        proGroup =(TextView)showInfoView.findViewById(R.id.progroup);
        knAria =(TextView)showInfoView.findViewById(R.id.knArea);

        btnreTake.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reTake();

            }
        });

        btnreTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reTest();
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                priview();
            }
        });

        context = container.getContext();
        String urls = url;//"http://www.jmbok.avantgoutrestaurant.com/and/all.php?knowledgearea=Projectriskmanagement&group=SelectAll&processname=SelectAll&difficulty=SelectAll";
        AsyncTaskCall ask = new AsyncTaskCall(getActivity(),this, urls,"exam");
        ask.execute(urls);

        priv = (Button)rootView.findViewById(R.id.prev);
        next = (Button)rootView.findViewById(R.id.next);
        exp = (Button)rootView.findViewById(R.id.exp);
        list = (ListView)rootView.findViewById(R.id.listView1);
        list.setVisibility(View.GONE);
        priv.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        exp.setVisibility(View.GONE);
        priv.setEnabled(false);
        exp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Explanation");
                alert.setMessage(dataSource.getDescription());


                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        // Do something with value!
                    }
                });


                alert.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (next.getText().toString().equals("Next")) {
                    priv.setEnabled(true);
                    if (iNext == 0) {
                        iNext = 1;
                        pCheck = true;

                    }
                    if (nCheck) {
                        iNext++;
                    }
                    if (iNext < count) {
                        dataSource = data.get(iNext);
                        ExammodeAdapter adapter = new ExammodeAdapter(context, dataSource,iNext,device,frag,resData);
                        list.setAdapter(adapter);
                        priv.setEnabled(true);
                        iNext++;
                        pCheck = true;
                        nCheck = false;

                    }
                    if (iNext == count){
//						next.setEnabled(false);
                        next.setText("Finish");
                        iNext = count-1;
                        pCheck = false;
                    }


                }else{
                    float proportionCorrect = ((float) resData.getCorrectAnswers()) / ((float) count);
//	        	  double perInt =  resData.getCorrectAnswers() / count;
                    int percentage = (int) (proportionCorrect * 100);
                    resData.setPercentage(percentage);
                    if (percentage >= 50) {
                        resData.setResult("Pass");
                    }else{
                        resData.setResult("Fail");
                    }
                    resData.setTotalQuestion(count);


                    getresult();
                    System.out.println("count  " + count +"perInt   " + proportionCorrect+ "  percentage  "+ percentage + "  answer  " + resData.getCorrectAnswers());
                }


            }


        });

        priv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click 
                if (iNext == count) {
                    iNext = count-1;
                }
                if (pCheck) {
                    iNext--;
                }
                if (iNext > 0) {
                    iNext--;
                    dataSource = data.get(iNext);
                    ExammodeAdapter adapter = new ExammodeAdapter(context, dataSource,iNext,device,frag,resData);
                    list.setAdapter(adapter);
                    next.setEnabled(true);
                    next.setText("Next");
                    nCheck =true;
                    pCheck = false;

                }
                if (iNext == 0){
                    priv.setEnabled(false);
                    iNext = 0;
                    nCheck =false;

                }


            }
        });

        return rootView;

    }

    public void Json(JSONObject json) throws JSONException{
        try {
            QuizJSONParser jsonParser = new QuizJSONParser();
            jsonParser.jsonArrayName =  "knowledgearea";
            data = jsonParser.testJsonParsing(json);
            retakedata = jsonParser.testJsonParsing(json);
            if (data.size() > 0) {
                count = data.size();
                for (int i = 0; i < data.size(); i++) {
                    Quizdata list = data.get(i);
                    System.out.println("" + list.getQuestion());

                }
                if (data.size() > 0) {
                    dataSource = data.get(0);
                    ExammodeAdapter adapter = new ExammodeAdapter(context, dataSource,0,device,frag,resData);
                    list.setAdapter(adapter);
                }
                show();
            }else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("No records found for your search !! ");

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        getActivity().finish();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        } catch (Exception e) {
            // TODO: handle exception
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("No records found for your search !! ");

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    getActivity().finish();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }
    public void show()
    {

        RelativeLayout linearLayout = new RelativeLayout(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(550, 550);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(seakBarlayout,numPicerParams);
        textEnd.setText(String.valueOf(data.size()));

        seekBar.setMax(data.size());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Select the number of Question out of " + String.valueOf(data.size())+"\n");
        alertDialogBuilder.setView(linearLayout);
//        alertDialogBuilder.setIcon(R.id.icon);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                int min = 60*1000;
                                CountDown timer = new CountDown(count*min, 1000);
                                timer.start();
                                list.setVisibility(View.VISIBLE);
                                priv.setVisibility(View.VISIBLE);
                                next.setVisibility(View.VISIBLE);
                                exp.setVisibility(View.VISIBLE);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                                getActivity().finish();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }



    public void showReview(){

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        ShowReviewAdapter adapter = new ShowReviewAdapter(context,data,count,device,frag);
        reviewListview.setAdapter(adapter);

        linearLayout = new RelativeLayout(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(width, height);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);

        linearLayout.addView(reviewView,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                alertDialog.dismiss();
                                linearLayout.removeView(reviewView);
                            }

                        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void reviewNavication(int id){

        if (data.size() > 0) {
            iNext = id;
            dataSource = data.get(id);
            ExammodeAdapter adapter = new ExammodeAdapter(context, dataSource,id,device,frag,resData);
            list.setAdapter(adapter);
        }
        alertDialog.dismiss();
        linearLayout.removeView(reviewView);
        next.setText("Next");
    }

    public void showResult(){

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        ResultAdapter adapter = new ResultAdapter(context,resData,count,device,frag);
        resultListview.setAdapter(adapter);

        linearLayout = new RelativeLayout(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(width, height);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);

        linearLayout.addView(resultView,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(linearLayout);

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        check = 0;
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_BACK){
                    if (check == 0) {
                        ((QuizActivity)getActivity()).onBackPressed();
                    }
                    check = 1;
                }
                return false;


            }
        });

    }


    public void showInfo(){

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        linearLayout = new RelativeLayout(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(550, 550);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(550, 550);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);

        linearLayout.addView(showInfoView, numPicerParams);

        proName.setText(dataSource.getProcessname());
        proName.setTextColor(Color.parseColor("#000000"));
        proGroup.setText(dataSource.getProcessgroup());
        proGroup.setTextColor(Color.parseColor("#000000"));
        knAria.setText(dataSource.getKnowledgeArea());
        knAria.setTextColor(Color.parseColor("#000000"));

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                alertDialog.dismiss();
                                linearLayout.removeView(showInfoView);
                            }

                        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void getresult(){
        String testID = UUID.randomUUID().toString();
        String timer = textTimer.getText().toString();
        StringBuilder qID = new StringBuilder();
        StringBuilder option = new StringBuilder();
        for (int i = 0; i < count; i++) {
            Quizdata list = data.get(i);
            qID.append(list.getQuestionID());
            qID.append(",");
            option.append(list.getExamAnswer());
            option.append(",");
        }
        String questionid = qID.toString();
        String optionVlaue = option.toString();

        System.out.println("mess " + questionid);
        System.out.println("mess " + optionVlaue);

        System.out.println("resData.getTotalQuestion()  " + resData.getTotalQuestion() +"resData.getAttemptQuestions()   " + resData.getAttemptQuestions()+ "  resData.getCorrectAnswers()  "+ resData.getCorrectAnswers() + "  resData.getMarkedReview()  " + resData.getMarkedReview()
        );

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Totalquestion", String.valueOf(resData.getTotalQuestion())));
        params.add(new BasicNameValuePair("Attemptquestions", String.valueOf(resData.getAttemptQuestions())));
        params.add(new BasicNameValuePair("Correctanswers", String.valueOf(resData.getCorrectAnswers())));
        params.add(new BasicNameValuePair("Markedreview", String.valueOf(resData.getMarkedReview())));
        params.add(new BasicNameValuePair("Percentage", String.valueOf(resData.getPercentage())));
        params.add(new BasicNameValuePair("Result", resData.getResult()));
        params.add(new BasicNameValuePair("Timespent", timer));
        params.add(new BasicNameValuePair("userid", "android@gmail.com")); //TODO: get from preference
        params.add(new BasicNameValuePair("testid", testID));
        params.add(new BasicNameValuePair("questionid", questionid));
        params.add(new BasicNameValuePair("options", optionVlaue));


        AsyncTaskCall ask = new AsyncTaskCall(context, "result",params);
        ask.examFragment = this;
        ask.execute(urlFinish);
    }



    public void refersh(){
        list.invalidateViews();
    }

    public void priview(){
        Intent intent = new Intent(getActivity(), PreviewActivity.class);
        intent.putExtra("data", data);
        intent.putExtra("count", count);
        startActivity(intent);
    }
    public void reTake(){

        data.clear();
        data = new ArrayList<Quizdata>(retakedata);
//		data = retakedata;
        if (data.size() > 0) {
            dataSource = data.get(0);
            ExammodeAdapter adapter = new ExammodeAdapter(context, dataSource,0,device,frag,resData);
//			 adapter.resData = resData;
            list.setAdapter(adapter);
            alertDialog.dismiss();
            linearLayout.removeView(resultView);
            next.setText("Next");
        }
    }
    public void reTest(){
        linearLayout.removeView(resultView);
        alertDialog.dismiss();
        getActivity().finish();

    }

    //countdown class
    public class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            textTimer.setText(hms);
        }

        @Override
        public void onFinish() {
            textTimer.setText("Time Up!!");
        }
    }


}
