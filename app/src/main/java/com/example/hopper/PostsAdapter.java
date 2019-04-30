package com.example.hopper;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        viewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> postList) {
        posts.addAll(postList);
        notifyDataSetChanged();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBName;
        private ImageView ivPicture;
        private TextView tvCountdown;
        private TextView tvDeal;
        private TextView tvLocation;
        private CountDownTimer countDownTimer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBName = itemView.findViewById(R.id.tvBName);
            ivPicture = itemView.findViewById(R.id.ivPicture);
//            tvCountdown = itemView.findViewById(R.id.tvCountdownPlaceholder);
            tvDeal = itemView.findViewById(R.id.tvDeal);
//            tvLocation = itemView.findViewById(R.id.tvLocation);

        }


        public void bind(Post post) {
            tvBName.setText(post.getUser());
            ParseFile image = post.getImage();
            if (image != null) {
                GlideApp.with(context).load(image.getUrl()).into(ivPicture);
            }
            tvDeal.setText(post.getDescription());
        }



        public void startTimer() {
            countDownTimer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvCountdown.setText("seconds remaining: " + millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    tvCountdown.setText("done!");

                }
            }.start();
        }



    }


}