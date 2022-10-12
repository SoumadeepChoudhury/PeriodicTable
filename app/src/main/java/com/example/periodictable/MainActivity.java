package com.example.periodictable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static  String[] ELEMENTS = {"Hydrogen","Helium","Lithium","Beryllium","Boron","Carbon","Nitrogen","Oxygen","Fluorine","Neon","Sodium","Magnesium","Aluminium","Silicon","Phosphorus",
            "Sulphur","Chlorine","Argon","Potassium","Calcium","Scandium","Titanium","Vanadium","Chromium","Manganese","Iron","Cobalt","Nickle","Copper","Zinc",
            "Gallium","Germanium","Arsenic","Selenium","Bromine","Krypton","Rubidium","Strontium","Yttrium","Zirconium","Niobium","Molybdenum","Technetium",
            "Ruthenium","Rhodium","Palladium","Silver","Cadmium","Indium","Tin","Antimony","Tellurium","Iodine","Xenon","Cesium","Barium","Lanthanum","Cerium",
            "Praseodymium","Neodymium","Promethium","Samarium","Europium","Gadolinium","Terbium","Dysprosium","Holmium","Erbium","Thulium","Ytterbium","Lutetium",
            "Hafmium","Tantalum","Tungsten","Rhenium","Osmium","Iridium","Platinum","Gold","Mercury","Thalium","Lead","Bismuth","Polonium","Astatine","Radon","Francium",
            "Radium","Actinium","Thorium","Protactinium","Uranium","Neptunium","Plutonium","Americium","Curium","Berkelium","Californium","Einsteinium","Fermium",
            "Mendelevium","Nobelium","Lawrencium","Rutherfordium","Dubnium","Seaborgium","Bohrium","Hassium","Meitnerium","Darmstadtium","Roentgenium","Copernicium","Nihonium",
            "Flerovium","Moscovium","Livermorium","Tennesin","Oganesson"};

    private static String[] SYMBOLS={"H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu",
                                    "Zn","Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs","Ba","La","Ce",
                                    "Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn"
                                    ,"Fr","Ra","Ac","Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Cn","Nh","Fl",
                                    "Mc","Lv","Ts","Og"};
    private static int[] NOBLE={2,10,18,36,54,86,118};
    private static String QUESTION="";
    private static int questionId=0;
    TextView ques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ques=(TextView) findViewById(R.id.questionView);
        setQuestion();
    }

    public void click(View view) {
        int val=Integer.valueOf(getResources().getResourceEntryName(view.getId()).substring(1));
        String str=GrpPeriod(val);
        Toast.makeText(getApplicationContext(),"You Clicked on "+ELEMENTS[val-1]+". Symbol is "+SYMBOLS[val-1]+". Z="+val+" "+str,Toast.LENGTH_LONG).show();
        TextView textView=(TextView) findViewById(view.getId());
        if(val==this.questionId){
            textView.setBackgroundColor(Color.GREEN);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setLayout(val,textView);
                    setQuestion();
                }
            },2000);
        }else{
            textView.setBackgroundColor(Color.RED);
            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setLayout(val,textView);
                }
            },1000);
        }
    }

    public String GrpPeriod(int val){
        int GROUP=1;
        int PERIOD=1;
        int x=0;
        if(val<=2){
            PERIOD=1;
        }
        for(int i=0;i<7;i++){
            if(val>NOBLE[i] && val<=NOBLE[i+1]){
                PERIOD=i+2;
                x=NOBLE[i+1];
            }
        }
        if (val==1){
            GROUP=1;
        }
        else if(val==2){
            GROUP=18;
        }
        else if(val<=18){
            GROUP=val+8-x;
        }
        else  if ((val>57 && val<=71)||(val>89 && val<=103)){
            GROUP=3;
        }
        else {
            GROUP=val+18-x;
        }
        if (GROUP<0){
            GROUP=val+32-x;
        }
        return ("Group= "+GROUP+". Period= "+PERIOD);
    }

    public void setLayout(int val,TextView textView){
        if((val%2==0 && (val>=58 && val<=71)) || (val%2!=0 && (val>=90 && val<=103))){
            textView.setBackgroundResource(R.drawable.border);
        }
        else {
            textView.setBackgroundColor(Color.WHITE);
        }
    }

    public void setQuestion(){
        Random rand=new Random();
        int questionId;
        while (true) {
            questionId = rand.nextInt(119);
            if (questionId!=0) {
                break;
            }
        }
        QUESTION=ELEMENTS[questionId-1];
        ques.setText("Select "+String.valueOf(QUESTION)+" !");
//        return questionId;
        this.questionId=questionId;
    }
}