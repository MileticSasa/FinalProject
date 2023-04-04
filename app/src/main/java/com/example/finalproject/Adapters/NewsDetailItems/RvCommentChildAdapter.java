package com.example.finalproject.Adapters.NewsDetailItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Model.Comment;
import com.example.finalproject.Model.CommentVote;
import com.example.finalproject.MySharedPreferences;
import com.example.finalproject.Response.CommentsResponse;
import com.example.finalproject.Response.VoteResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.RvChildCommentItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvCommentChildAdapter extends RecyclerView.Adapter<RvCommentChildAdapter.MyHolderChild> {

    private ArrayList<Comment>list;

    private boolean voted;
    private boolean isExpanded = false;

    public RvCommentChildAdapter(ArrayList<Comment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvChildCommentItemBinding binding = RvChildCommentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyHolderChild(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderChild holder, int position) {

        holder.binding.tvName.setText(list.get(position).name);
        holder.binding.tvText.setText(list.get(position).content);
        holder.binding.tvTime.setText(list.get(position).created_at);
        holder.binding.tvPositiveVotes.setText(""+list.get(position).positive_votes);
        holder.binding.tvNegativeVotes.setText(""+list.get(position).negative_votes);

        setupLikeAndDislikeButton(holder, position);

        holder.binding.tvRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isExpanded) {
                    holder.binding.typeCommentLayout.setVisibility(View.VISIBLE);
                    isExpanded = true;

                    holder.binding.tvSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(holder.binding.etName.getText().length() > 2 && holder.binding.etComment.getText().length() > 0) {
                                leaveComment(holder, position);
                            }
                            else{
                                Toast.makeText(holder.itemView.getContext(), "Polja ne smeju biti prazna!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    holder.binding.typeCommentLayout.setVisibility(View.GONE);
                    isExpanded = false;
                }
            }
        });

        if (list.get(position).children != null && list.get(position).children.size() > 0) {
            holder.binding.tvSee.setVisibility(View.VISIBLE);

            holder.binding.tvSee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.binding.rv.setVisibility(View.VISIBLE);
                    holder.binding.rv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
                    holder.binding.tvSee.setVisibility(View.GONE);
                    holder.binding.rv.setAdapter(new RvCommentChildAdapter(list.get(position).children));
                }
            });
        }
    }

    private void leaveComment(MyHolderChild holder, int position) {
        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        Comment comment = new Comment();
        comment.negative_votes = 0;
        comment.positive_votes = 0;
        comment.parent_comment = list.get(position).id;
        comment.children = null;
        comment.news = list.get(position).news;
        comment.content = holder.binding.etComment.getText().toString();
        comment.name = holder.binding.etName.getText().toString();
        comment.created_at = date + "|" + time;

        service.addComment(comment).enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                Toast.makeText(holder.itemView.getContext(), "Komentarisano!", Toast.LENGTH_SHORT).show();

                holder.binding.typeCommentLayout.setVisibility(View.GONE);
                isExpanded = false;
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                Toast.makeText(holder.itemView.getContext(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                holder.binding.typeCommentLayout.setVisibility(View.GONE);
                isExpanded = false;
            }
        });
    }

    private void setupLikeAndDislikeButton(MyHolderChild holder, int position) {

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        //comment id will be key for saving in sharedpreferences
        String commentId = ""+list.get(position).id;
        voted = MySharedPreferences.getInstance(holder.itemView.getContext()).getIsVoted(commentId);
        list.get(position).voted = voted;

        if(list.get(position).voted){
            holder.binding.btnPlus.setEnabled(false);
            holder.binding.btnMinus.setEnabled(false);
        }
        else {
            holder.binding.btnPlus.setEnabled(true);
            holder.binding.btnMinus.setEnabled(true);

            holder.binding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommentVote vote = new CommentVote();
                    vote.setComment(list.get(position).id);
                    vote.setVote(true);

                    service.addVote(vote).enqueue(new Callback<VoteResponse>() {
                        @Override
                        public void onResponse(Call<VoteResponse> call, Response<VoteResponse> response) {
                            Toast.makeText(holder.itemView.getContext(), "Liked", Toast.LENGTH_SHORT).show();

                            holder.binding.btnPlus.setEnabled(false);
                            holder.binding.btnMinus.setEnabled(false);

                            list.get(position).voted = true;

                            //saving state into sharedprefs
                            MySharedPreferences.getInstance(holder.itemView.getContext()).addIsVoted(""+list.get(position).id);
                        }

                        @Override
                        public void onFailure(Call<VoteResponse> call, Throwable t) {
                            Toast.makeText(holder.itemView.getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            holder.binding.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommentVote vote = new CommentVote();
                    vote.setComment(list.get(position).id);
                    vote.setVote(true);

                    service.addVote(vote).enqueue(new Callback<VoteResponse>() {
                        @Override
                        public void onResponse(Call<VoteResponse> call, Response<VoteResponse> response) {
                            Toast.makeText(holder.itemView.getContext(), "Disliked", Toast.LENGTH_SHORT).show();

                            holder.binding.btnPlus.setEnabled(false);
                            holder.binding.btnMinus.setEnabled(false);

                            list.get(position).voted = true;

                            //saving state into sharedprefs
                            MySharedPreferences.getInstance(holder.itemView.getContext()).addIsVoted(""+list.get(position).id);
                        }

                        @Override
                        public void onFailure(Call<VoteResponse> call, Throwable t) {
                            Toast.makeText(holder.itemView.getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyHolderChild extends RecyclerView.ViewHolder {

        private RvChildCommentItemBinding binding;

        public MyHolderChild(@NonNull RvChildCommentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
