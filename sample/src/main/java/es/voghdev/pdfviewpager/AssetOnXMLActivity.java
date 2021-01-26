/*
 * Copyright (C) 2016 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.pdfviewpager;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.BasePDFPagerAdapter;

public class AssetOnXMLActivity extends BaseSampleActivity {
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static int REQUEST_PERMISSION_CODE = 1;//请求状态码
    RemotePDFLayout remotePDFLayout;
    Button btChange;
    Button btChangeBig;
    private static final String TAG = "AssetOnXMLActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.asset_on_xml);
        setContentView(R.layout.activity_asset_on_xml);

        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);

        btChange = findViewById(R.id.btChange);
        btChangeBig = findViewById(R.id.btChangeBig);
        //pdfViewPager = findViewById(R.id.pdfViewPager);
        remotePDFLayout = findViewById(R.id.remotePDFLayout);
        //String fileUrl = Environment.getExternalStorageDirectory().getAbsolutePath()+"/" + "乐课云课堂" + "/1.pdf";
        remotePDFLayout.loadPDF("https://lec-t-bh.oss-cn-beijing.aliyuncs.com//2021/1/11/doc/1772e28b-4972-484d-bf44-a21c0b839f35_trans.pdf",11);


        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) remotePDFLayout.getLayoutParams();
                linearParams.height = 400;
                linearParams.width = 500;
                remotePDFLayout.setLayoutParams(linearParams);
                Log.d(TAG, "onClick: 当前页数" + remotePDFLayout.getCurrentNumPage() );
                remotePDFLayout.refreshCurrentViewPager();



            }
        });
        btChangeBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) remotePDFLayout.getLayoutParams();
                linearParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
                linearParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                remotePDFLayout.setLayoutParams(linearParams);
                Log.d(TAG, "onClick: 总页数" + remotePDFLayout.getSumNumPage() );
                Log.d(TAG, "onClick: 当前页数" + remotePDFLayout.getCurrentNumPage() );
                remotePDFLayout.refreshCurrentViewPager();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        remotePDFLayout.release();
        //((BasePDFPagerAdapter) pdfViewPager.getAdapter()).close();
    }
}