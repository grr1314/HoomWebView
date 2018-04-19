package hoomsun.com.lc.hoomwebview.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hoomsun.com.lc.hoomwebview.R;

/**
 * Created by hoomsun on 2018/4/19.
 */

public class IndexActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndexActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
