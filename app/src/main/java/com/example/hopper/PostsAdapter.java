package com.example.hopper;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        private TextView tvDeal;
        private TextView tvLocation;
        private TextView tvCountdown;
        private CountDownTimer countDownTimer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBName = itemView.findViewById(R.id.tvBName);
            ivPicture = itemView.findViewById(R.id.ivPicture);
            tvDeal = itemView.findViewById(R.id.tvDeal);
//            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvCountdown = itemView.findViewById(R.id.tvCountdownPlaceholder);



        }


        public void bind(Post post) {
            tvBName.setText(post.getUser());
            ParseFile image = post.getImage();
            if (image != null) {
                GlideApp.with(context).load(image.getUrl()).into(ivPicture);
            }
            tvDeal.setText(post.getDescription());
            Date date = new Date();
            long timeMilli = date.getTime();
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DDTHH:mm:ss.SSSZ");
            Date end = post.getEndDate();
            Log.d("Date Pulled: ", end.toString());

            startTimer(end.getTime() - timeMilli);
        }

        public void startTimer(long END_DATE) {
            countDownTimer = new CountDownTimer(END_DATE, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String timeRemaining = String.format("%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), // The change is in this line
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                    tvCountdown.setText("Time remaining: " + timeRemaining);

                }

                @Override
                public void onFinish() {
                    tvCountdown.setText("Deal expired");

                }
            }.start();
        }





    }


}