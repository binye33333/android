package com.teach.yo.recyleviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：chenyo on 2015/12/20 22:07
 */
public class TestActivity extends AppCompatActivity {

    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_test_layout);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(manager);
        recycleView.setAdapter(new InnerAdapter());
    }


    class InnerAdapter extends RecyclerView.Adapter<InnerViewHolder> {

        @Override
        public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_recycle_view, null);
            return new InnerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(InnerViewHolder holder, int position) {
            holder.textView.setText("position" + position);
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }


    class InnerViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public InnerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView) ;
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

    }
}
