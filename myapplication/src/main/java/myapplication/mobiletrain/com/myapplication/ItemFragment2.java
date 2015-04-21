package myapplication.mobiletrain.com.myapplication;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

import myapplication.mobiletrain.com.myapplication.bean.PinDao;

public class ItemFragment2 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RequestQueue requestQueue;
    private ListView list;
    private ArrayList<PinDao> data;


    public static ItemFragment2 newInstance(String param1, String param2) {
        ItemFragment2 fragment = new ItemFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemFragment2() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        final View v =inflater.inflate(R.layout.fragment_item_fragment2, container, false);
        final View view = inflater.inflate(R.layout.list_item, container, false);


        list = (ListView) v.findViewById(R.id.listView);
        list.setAdapter(new ArrayAdapter<String>(
                getActivity()
                ,R.layout.list_item
                ,R.id.tex1,
                new String[]{
                        getString(R.string.video_list1),
                        getString(R.string.video_list2),
                        getString(R.string.video_list3),
                        getString(R.string.video_list4),
                        getString(R.string.video_list5),
                        getString(R.string.video_list6)
                }));
//        list.addView(view);

        requestQueue = Volley.newRequestQueue(getActivity());
        String path="http://api.le123.com/kuaikan/apilist_json.so?vt=1&code=7884b0bd86607a87&uuid=232c136c-f2ed-3401-87af-7aa18aa0e84b&version=1.6.3&pagesize=18&pageindex=1&platform=Le123Plat101360&plattype=aphone";
        StringRequest stringRequest = new StringRequest(path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                JSONObject object=null;
//                try {
//                    object = new JSONObject(response);
//                    JSONArray array = object.getJSONArray("data");
//                    for (int i = 0; i <array.length() ; i++) {
//                        JSONObject jb = array.getJSONObject(i);
//                        jb.getString()
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        final String imagepath = "http://i1.letvimg.com/vrs/201412/31/115dac56-b785-4b42-8860-aae121d0e2cb.jpg";
        requestQueue.add(stringRequest);
        final HashMap<String,SoftReference<Bitmap>> cache = new HashMap<String,SoftReference<Bitmap>>();

        int memory = (int) Runtime.getRuntime().maxMemory();
        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(memory / 8){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                if (evicted){
                    SoftReference<Bitmap> st = new SoftReference<Bitmap>(oldValue);
                    cache.put(key,st);
                }
            }
        };


        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                Bitmap map = lruCache.get(url);
                if (map!=null) {
                    return map;
                }
                SoftReference<Bitmap> sf = cache.get(url);
                if (sf!=null){
                   map= sf.get();
                }
                return  map;
            }


            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url,bitmap);
            }
        });
        imageLoader.get(imagepath, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap bitmap = response.getBitmap();
                ImageView image = (ImageView) view.findViewById(R.id.imageV);
                image.setImageBitmap(bitmap);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        NetworkImageView networkImageView = (NetworkImageView)
                view.findViewById(R.id.imageV);
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        networkImageView.setImageUrl(imagepath, imageLoader);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case 0:
                        Intent disnhi=new Intent(getActivity(),DianShiActivity.class);
                        startActivity(disnhi);
                        Toast.makeText(getActivity(), "电视剧列表", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent move=new Intent(getActivity(),MoveActivity.class);
                        startActivity(move);
                        Toast.makeText(getActivity(), "电影列表", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent dongman=new Intent(getActivity(),DongManActivity.class);
                        startActivity(dongman);
                        Toast.makeText(getActivity(), "动漫列表", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Intent zongyi=new Intent(getActivity(),DianShiActivity.class);
                        startActivity(zongyi);
                        Toast.makeText(getActivity(), "我的收藏", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        view.setOnClickListener(this);

        return  v;



    }

    @Override
    public void onClick(View v) {

    }

//    class MyAdapter extends BaseAdapter{
//
//        private ArrayList<PinDao> data;
//        private LayoutInflater inflater;
//        private Context context;
//        String s[]={"电视剧(8942)","电影","动漫","综艺","纪录片","直播"};
//        String str[]={"电视剧","(8942)","何以笙箫默 甄嬛传 四大名捕"};
//
//        public MyAdapter(ArrayList<PinDao> data, Context context) {
//            this.data = data;
//            this.context = context;
//            this.inflater=LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            if (data!=null){
//                return data.size();
//            }
//            return 0;
//        }
//
//        @Override
//        public PinDao getItem(int position) {
//            return data.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//
//            ViewHolder holder=null;
//            if (convertView==null){
//                convertView = inflater.inflate(R.layout.list_item, null);
//                holder=new ViewHolder();
//                holder.text1= (TextView) convertView.findViewById(R.id.tex1);
//                holder.text2= (TextView) convertView.findViewById(R.id.tex2);
//                holder.text3= (TextView) convertView.findViewById(R.id.tex3);
//                holder.image= (ImageView) convertView.findViewById(R.id.imageV);
//                convertView.setTag(holder);
//            }else{
//                holder= (ViewHolder) convertView.getTag();
//            }
//
//
//
//            return convertView;
//        }
//    }


    class ViewHolder{
        TextView text1;
        TextView text2;
        TextView text3;
        ImageView image;
    }


}
