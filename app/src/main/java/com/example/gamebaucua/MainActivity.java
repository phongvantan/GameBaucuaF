package com.example.gamebaucua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.health.TimerStat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Custom_Griew_banco adapter;
    Integer[]dsHinh={R.drawable.nai,R.drawable.bau,R.drawable.ga,R.drawable.ca,R.drawable.cua,R.drawable.tom};
    AnimationDrawable Cdxingau1,Cdxingau2,Cdxingau3;
    ImageView hinhxingau1,hinhxingau2,hinhxingau3;
    Random rd;
    int gtXingau1,gtXingau2,gtXingau3;
    public static Integer[]gtDatcuoc= new Integer[6];
    int tongtiencu,tongtiennew;
    TextView tvTien;
    Timer timer= new Timer();
    Handler handler;
    int tienthuong,kiemtra;
    SharedPreferences luutru;
    public void LuuDuLieuNguoiDung(int tienthuong){
        SharedPreferences.Editor editor=luutru.edit();
        tongtiennew=tongtiencu + tienthuong;
        editor.putInt("TongTien",tongtiennew);
        editor.commit();
        Log.d("Kết quả","tiền thưởng " +tienthuong +" Tổng tiền "+tongtiencu);

    }
    Handler.Callback callback= new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg) {
            Randomxingau1();
            Randomxingau2();
            Randomxingau3();



            for (int i=0 ;i<gtDatcuoc.length;i++){
                if(gtDatcuoc[i] !=0){
                    if (i == gtXingau1){
                        tienthuong += gtDatcuoc[i];

                    }
                    if (i==gtXingau2){
                        tienthuong +=gtDatcuoc[i];
                    }
                    if (i==gtXingau3){
                        tienthuong +=gtDatcuoc[i];
                    }
                    if (i!=gtXingau1 && i!=gtXingau2 && i!=gtXingau3){
                        tienthuong -=gtDatcuoc[i];
                    }



                }
            }
            if (tienthuong>0){
                Toast.makeText(getApplicationContext(),"Bạn trúng được"+ " "+tienthuong,Toast.LENGTH_SHORT).show();
                LuuDuLieuNguoiDung(tienthuong);
                tvTien.setText(String.valueOf(tongtiennew));
            }else if (tienthuong == 0){
                Toast.makeText(getApplicationContext(),"Chơi ngu mém chết!"+" "+tienthuong,Toast.LENGTH_SHORT).show();
                LuuDuLieuNguoiDung(tienthuong);
                tvTien.setText(String.valueOf(tongtiennew));
            }else if (tienthuong < 0){
                Toast.makeText(getApplicationContext(),"Mày hả bưởi"+" "+tienthuong +" "+"rồi!",Toast.LENGTH_SHORT).show();
                tvTien.setText(String.valueOf(tongtiennew));
            }



            return false;


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hinhxingau1=(ImageView)findViewById(R.id.xingau1);
        hinhxingau2=(ImageView)findViewById(R.id.xingau2);
        hinhxingau3=(ImageView)findViewById(R.id.xingau3);
        tvTien= (TextView)findViewById(R.id.tvTien);


        gridView= (GridView)findViewById(R.id.grbanco);
        adapter= new Custom_Griew_banco(this,R.layout.custom_banco,dsHinh);
        gridView.setAdapter(adapter);
        luutru =getSharedPreferences("luutruthongtin", Context.MODE_PRIVATE);



        tongtiencu=luutru.getInt("TongTien",10000);
        tvTien.setText(String.valueOf(tongtiencu));

        handler = new Handler(callback);
    }

    public void Lacxingau(View v) {
        hinhxingau1.setImageResource(R.drawable.hinhdongbaucua);
        hinhxingau2.setImageResource(R.drawable.hinhdongbaucua);
        hinhxingau3.setImageResource(R.drawable.hinhdongbaucua);

        Cdxingau1 = (AnimationDrawable) hinhxingau1.getDrawable();
        Cdxingau2 = (AnimationDrawable) hinhxingau2.getDrawable();
        Cdxingau3 = (AnimationDrawable) hinhxingau3.getDrawable();

        kiemtra = 0;

        for (int i = 0; i < gtDatcuoc.length; i++) {
            kiemtra += gtDatcuoc[i];
        }
        if (kiemtra == 0) {
            Toast.makeText(getApplicationContext(), "Bạn vui lòng đặt cược !", Toast.LENGTH_SHORT).show();
        }
        else if (kiemtra!=0) {
            if (kiemtra > tongtiencu) {
                Toast.makeText(getApplicationContext(), "Bạn không đủ tiền  đặt cược !", Toast.LENGTH_SHORT).show();
            } else {

                Cdxingau1.start();
                Cdxingau2.start();
                Cdxingau3.start();

                tienthuong = 0;
                timer.schedule(new LacXingau(), 1000);
            }

        }
    }



    class LacXingau extends TimerTask{
        @Override
        public void run(){
            handler.sendEmptyMessage(0);


        }
    }
    public void Randomxingau1(){
        rd= new Random();
        int rd1=rd.nextInt(6);
        switch (rd1){
            case 0:
                hinhxingau1.setImageResource(dsHinh[0]);
                gtXingau1=rd1;
                break;

            case 1:
                hinhxingau1.setImageResource(dsHinh[1]);
                gtXingau1=rd1;
                break;

            case 2:
                hinhxingau1.setImageResource(dsHinh[2]);
                gtXingau1=rd1;
                break;

            case 3:
                hinhxingau1.setImageResource(dsHinh[3]);
                gtXingau1=rd1;

                break;

            case 4:
                hinhxingau1.setImageResource(dsHinh[4]);
                gtXingau1=rd1;
                break;
            case 5:
                hinhxingau1.setImageResource(dsHinh[5]);
                gtXingau1=rd1;
                break;



        }

    }
    public void Randomxingau2(){
        rd= new Random();
        int rd1=rd.nextInt(6);
        switch (rd1){
            case 0:
                hinhxingau2.setImageResource(dsHinh[0]);
                gtXingau2=rd1;
                break;

            case 1:
                hinhxingau2.setImageResource(dsHinh[1]);
                gtXingau2=rd1;
                break;

            case 2:
                hinhxingau2.setImageResource(dsHinh[2]);
                gtXingau2=rd1;
                break;

            case 3:
                hinhxingau2.setImageResource(dsHinh[3]);
                gtXingau2=rd1;

                break;

            case 4:
                hinhxingau2.setImageResource(dsHinh[4]);
                gtXingau2=rd1;
                break;
            case 5:
                hinhxingau2.setImageResource(dsHinh[5]);
                gtXingau2=rd1;
                break;



        }

    }
    public void Randomxingau3(){
        rd= new Random();
        int rd1=rd.nextInt(6);
        switch (rd1){
            case 0:
                hinhxingau3.setImageResource(dsHinh[0]);
                gtXingau3=rd1;
                break;

            case 1:
                hinhxingau3.setImageResource(dsHinh[1]);
                gtXingau3=rd1;
                break;

            case 2:
                hinhxingau3.setImageResource(dsHinh[2]);
                gtXingau3=rd1;
                break;

            case 3:
                hinhxingau3.setImageResource(dsHinh[3]);
                gtXingau3=rd1;

                break;

            case 4:
                hinhxingau3.setImageResource(dsHinh[4]);
                gtXingau3=rd1;
                break;
            case 5:
                hinhxingau3.setImageResource(dsHinh[5]);
                gtXingau3=rd1;
                break;



        }

    }
}

