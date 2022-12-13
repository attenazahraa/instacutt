package android.meta.instacutt;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.meta.instacutt.helper.Helper;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView imgTelegram;
    ImageView imgInstagram;
    ImageView imgWhatsapp;
    ImageView imgCall;
    ImageView imgtst;
    CardView start_CardView;
    Intent ShareIntent;


    String MyTelegramId="Alinr2022";
   String Opentelegram="https://t.me/"+MyTelegramId;
   String TelegramPackageName="org.telegram.messenger";
   String MyketUrl = "myket://comment?id=android.meta.instacutt";
   String MyInstagramProfileAdress="https://www.instagram.com/_u/metaverse_design30";
   String InstagramPackageName="com.instagram.android";
   String myketUrl="https://www.myket.ir";
   String cantFindWhatsApp="cantFindWhatsapp!";
   String cantFindMyket="مایکت را نصب کنیذ!";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {


    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_about,container,false);
        imgtst=view.findViewById(R.id.e);



      imgTelegram=view.findViewById(R.id.telegram_iconid);

      imgTelegram.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              try {
                 ShareIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(Opentelegram));
                  ShareIntent.setPackage(TelegramPackageName);
                  startActivity(ShareIntent);
              } catch (Exception e) {
                  e.printStackTrace();

                  ShareIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(Opentelegram));

                  startActivity(ShareIntent);

              }

          }
      });

        imgInstagram=view.findViewById(R.id.instagram_iconid);

        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            try {

            ShareIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(MyInstagramProfileAdress));
            ShareIntent.setPackage(InstagramPackageName);
            startActivity(ShareIntent);



            }catch (Exception e){

                ShareIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(MyInstagramProfileAdress));
                startActivity(ShareIntent);

            e.printStackTrace();



            }

            }
        });

        imgWhatsapp=view.findViewById(R.id.whatsapp_iconid);

        imgWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact = "+98 09045350092"; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                try {
                    PackageManager pm = getActivity().getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getActivity(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });

        imgCall=view.findViewById(R.id.call_iconid);
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         ShareIntent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:(09045350092)"));
                startActivity(ShareIntent);
            }
        });

        start_CardView=view.findViewById(R.id.star_CardviewId);
        start_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(MyketUrl));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), cantFindMyket, Toast.LENGTH_SHORT).show();
                    ShareIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(myketUrl));
                    startActivity(ShareIntent);


                }
            }
        });




        // Inflate the layout for this fragment    )
        return view;
    }
}