package com.example.asset_storage_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asset_storage_.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    private int index = 0 ;
    private ArrayList<StoryModel> listStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()) ; 
        setContentView(binding.getRoot());
        initViews() ;  
    }

    private void initViews() {
        initPhotoTopic();
        readStoryFile() ;
        binding.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++ ;
                if(index > listStory.size()-1)
                {
                    index = 0 ;
                }
                SetStories();
            }
        });
        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index-- ;
                if(index < 0)
                {
                    index = listStory.size()-1 ;
                }
                SetStories();
            }
        });
    }

    private void readStoryFile() {
        AssetManager astMgr = getAssets() ;
      listStory = new ArrayList<>() ;
        try{
            InputStream in = astMgr.open("story/Ai vẽ.txt") ;
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8) ;
            BufferedReader reader = new BufferedReader(isr) ;
            String name = null ;
            StringBuilder content = new StringBuilder() ;
            String line = reader.readLine() ;

            while(line!=null)
            {
                if(name == null)
                {
                    name = line ;
                }else if(line.contains("','0');")) {
                    StoryModel storyModel = new StoryModel(name,content.toString()) ;
                    listStory.add(storyModel) ;
                    name = null ;
                    content = new StringBuilder() ;
                }else if(!line.isEmpty())
                {
                    content.append(line).append("\n") ;
                }
                line = reader.readLine() ;
            }
            in.close();
            reader.close();
            isr.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
            SetStories();


    }

    private void SetStories() {
        binding.NameStories.setText(listStory.get(index).getName());
        binding.contentStories.setText(listStory.get(index).getContent());
    }

    private void initPhotoTopic() {
        AssetManager ast = getAssets() ;
        try{
            String[] StringList = ast.list("photo/") ;
            binding.lnTopic.removeAllViews();
            for (String index : StringList
            ) {
                InputStream in = ast.open("photo/"+ index) ;
                Bitmap bmp = BitmapFactory.decodeStream(in) ;
                View view = LayoutInflater.from(this).inflate(R.layout.topic_stories,null) ;
                TextView tv_Name = view.findViewById(R.id.tv_name) ;
                ImageView ig_Photo = view.findViewById(R.id.ig_photo) ;
                tv_Name.setText(index.replace(".png",""));
                ig_Photo.setImageBitmap(bmp);

                binding.lnTopic.addView(view);


            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}