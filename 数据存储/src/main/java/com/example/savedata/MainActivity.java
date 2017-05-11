package com.example.savedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClickLogin(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.cbSave);
        EditText etUserName = (EditText) findViewById(R.id.etUserName);
        EditText etUserPwd = (EditText) findViewById(R.id.etUserPwd);


        if (checkBox.isChecked()) {
            //1.获取需要的数据
            String user = etUserName.getText().toString();
            String pass = etUserPwd.getText().toString();

            //2.创建文件对象
            String fileName = getFilesDir() + "/info.txt";
            File file = new File(fileName);
            try {
                //3.根据文件对象，创建写入文件对象（包含写入权限）
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bytes = (user+"##"+pass+"##").getBytes();
                //4.保存文件到对应目录下
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
