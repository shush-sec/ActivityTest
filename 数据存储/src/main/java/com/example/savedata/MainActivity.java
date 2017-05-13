package com.example.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loatFromSD();
    }

    public void onClickLogin(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.cbSave);
        EditText etUserName = (EditText) findViewById(R.id.etUserName);
        EditText etUserPwd = (EditText) findViewById(R.id.etUserPwd);


        if (checkBox.isChecked()) {
            //saveToFile(etUserName, etUserPwd);
            saveToSD(etUserName, etUserPwd);
        }

    }

    private void saveToSD(EditText etUserName, EditText etUserPwd) {
        //1.获取需要的数据
        String user = etUserName.getText().toString();
        String pass = etUserPwd.getText().toString();

        //获取SD卡状态
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_UNMOUNTED)){
            Toast.makeText(this, "请插入SD卡", Toast.LENGTH_SHORT).show();
            return ;
        }else{
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir.getPath()+"/info.txt");

            try {
                FileOutputStream fileOutputStream =
                        new FileOutputStream(file);
                byte[] bytes = (user+"##"+pass).getBytes();
                fileOutputStream.write(bytes);
                fileOutputStream.close();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //从SDcard读取数据
    private void loatFromSD(){
        EditText etUserName = (EditText) findViewById(R.id.etUserName);
        EditText etUserPwd = (EditText) findViewById(R.id.etUserPwd);

        //获取SD卡状态
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_UNMOUNTED)){
            Toast.makeText(this, "请插入SD卡", Toast.LENGTH_SHORT).show();
            return ;
        }else{
            File dir = Environment.getExternalStorageDirectory();
            File file = new File(dir.getPath()+"/info.txt");
            //判断文件是否存在
            if(file.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bytes = new byte[(int)file.length()];
                    int nlen = fileInputStream.read(bytes);

                    if (nlen != -1){
                        String text = new String(bytes);
                        String contents[] = text.split("##");
                        String user = contents[0];
                        String pass = contents[1];

                        etUserName.setText(user);
                        etUserPwd.setText(pass);


                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    //保存到文件
    private void saveToFile(EditText etUserName, EditText etUserPwd) {
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
