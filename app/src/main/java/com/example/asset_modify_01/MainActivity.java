package com.example.asset_modify_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asset_modify_01.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    static int index = 0 ;
    private ArrayList<StoriesModel> listStories ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
                initPhotoTopic() ;
                readFileStories();
                binding.btBack.setOnClickListener(v-> gotoStoriesBack());
                binding.btNext.setOnClickListener(v -> gotoStoriesNext());


    }

    private void gotoStoriesNext() {
        index++ ;
        if(index > listStories.size()-1)
        {
            index = 0  ;
        }
        setTtories();

    }

    private void gotoStoriesBack() {
        index-- ;
        if(index < 0)
        {
            index = listStories.size()-1 ;

        }
        setTtories() ;

    }

    private void readFileStories() {
        AssetManager Asst = getAssets() ;
        listStories = new ArrayList<>() ;
        try{
                InputStream in = Asst.open("story/Ai vẽ.txt") ;
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8) ;
            BufferedReader bfRder = new BufferedReader(isr)  ;
            String name = null ;
            StringBuilder content = new StringBuilder() ;
                String line = bfRder.readLine() ;
                while(line != null)
                {
                        if(name == null) name = line ;
                        else if(line.contains("','0');"))
                        {
                            StoriesModel storiesModel = new StoriesModel(name, content.toString()) ;
                            listStories.add(storiesModel) ;
                            name = null ;
                            content = new StringBuilder() ;
                        }else if(!line.isEmpty())
                        {
                                content.append(line).append("\n") ;
                        }
                        line = bfRder.readLine() ;
                }
                in.close();
                bfRder.close();
                in.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
            setTtories();

    }

    private void initPhotoTopic() {

        AssetManager ast = getAssets() ;
        try{
            String[] listStory =ast.list("photo/") ;
                binding.lnTopic.removeAllViews();
            for (String stories: listStory
                 ) {
                        InputStream in = ast.open("photo/" + stories );
                Bitmap bmp = BitmapFactory.decodeStream(in) ;
                View view = LayoutInflater.from(this).inflate(R.layout.ln_topic,null)  ;
                    TextView tv_Story = view.findViewById(R.id.tv_topicStories) ;
                ImageView ig_Story = view.findViewById(R.id.iv_photo) ;
                tv_Story.setText(stories.replace(".png",""));
                ig_Story.setImageBitmap(bmp);


                binding.lnTopic.addView(view);
            }


        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    private void setTtories()
    {
            binding.tvNameStories.setText(listStories.get(index).getName());
            binding.tvContentStories.setText(listStories.get(index).getContent());
    }
}