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
import com.example.finalproject.databinding.RvCommentItemBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvCommentAdapter extends RecyclerView.Adapter {

    private ArrayList<Comment> list;
    private int limit;

    private boolean voted;
    private boolean isExpanded = false;

    public RvCommentAdapter(ArrayList<Comment> list, int limit) {
        this.list = list;
        this.limit = limit;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvCommentItemBinding binding = RvCommentItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MyHolder)holder).binding.tvName.setText(list.get(position).name);
        ((MyHolder)holder).binding.tvText.setText(list.get(position).content);
        ((MyHolder)holder).binding.tvPositiveVotes.setText(""+list.get(position).positive_votes);
        ((MyHolder)holder).binding.tvNegativeVotes.setText(""+list.get(position).negative_votes);
        ((MyHolder)holder).binding.tvTime.setText(list.get(position).created_at);

        setupLikeAndDislikeButton(holder, position);

        ((MyHolder)holder).binding.tvRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isExpanded) {
                    ((MyHolder) holder).binding.typeCommentLayout.setVisibility(View.VISIBLE);
                    isExpanded = true;

                    ((MyHolder)holder).binding.tvSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(((MyHolder)holder).binding.etName.getText().length() > 2 && ((MyHolder)holder).binding.etComment.getText().length() > 0) {
                                leaveComment(holder, position);
                            }
                            else{
                                Toast.makeText(((MyHolder)holder).itemView.getContext(), "Polja ne smeju biti prazna!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    ((MyHolder) holder).binding.typeCommentLayout.setVisibility(View.GONE);
                    isExpanded = false;
                }
            }
        });

        if(list.get(position).children != null && list.get(position).children.size() > 0){
            ((MyHolder)holder).binding.tvSee.setVisibility(View.VISIBLE);

            ((MyHolder)holder).binding.tvSee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MyHolder)holder).binding.rv.setVisibility(View.VISIBLE);
                    ((MyHolder)holder).binding.tvSee.setVisibility(View.GONE);

                    ((MyHolder)holder).binding.rv.setLayoutManager(new LinearLayoutManager(((MyHolder)holder).itemView.getContext()));
                    ((MyHolder)holder).binding.rv.setAdapter(new RvCommentChildAdapter(list.get(position).children));
                }
            });
        }
    }

    private void leaveComment(RecyclerView.ViewHolder holder, int position) {
        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        Comment comment = new Comment();
        comment.negative_votes = 0;
        comment.positive_votes = 0;
        comment.parent_comment = list.get(position).id;
        comment.children = null;
        comment.news = list.get(position).news;
        comment.content = ((MyHolder)holder).binding.etComment.getText().toString();
        comment.name = ((MyHolder)holder).binding.etName.getText().toString();
        comment.created_at = date + "|" + time;

        service.addComment(comment).enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                Toast.makeText(holder.itemView.getContext(), "Komentarisano!", Toast.LENGTH_SHORT).show();

                ((MyHolder) holder).binding.typeCommentLayout.setVisibility(View.GONE);
                isExpanded = false;
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                Toast.makeText(holder.itemView.getContext(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                ((MyHolder) holder).binding.typeCommentLayout.setVisibility(View.GONE);
                isExpanded = false;
            }
        });
    }

    private void setupLikeAndDislikeButton(RecyclerView.ViewHolder holder, int position) {

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        //comment id will be key for saving in sharedpreferences
        String commentId = ""+list.get(position).id;
        voted = MySharedPreferences.getInstance(holder.itemView.getContext()).getIsVoted(commentId);
        list.get(position).voted = voted;

        if(list.get(position).voted){
            ((MyHolder)holder).binding.btnPlus.setEnabled(false);
            ((MyHolder)holder).binding.btnMinus.setEnabled(false);
        }
        else {
            ((MyHolder)holder).binding.btnPlus.setEnabled(true);
            ((MyHolder)holder).binding.btnMinus.setEnabled(true);

            ((MyHolder)holder).binding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommentVote vote = new CommentVote();
                    vote.setComment(list.get(position).id);
                    vote.setVote(true);

                    service.addVote(vote).enqueue(new Callback<VoteResponse>() {
                        @Override
                        public void onResponse(Call<VoteResponse> call, Response<VoteResponse> response) {
                            Toast.makeText(holder.itemView.getContext(), "Liked", Toast.LENGTH_SHORT).show();

                            ((MyHolder)holder).binding.btnPlus.setEnabled(false);
                            ((MyHolder)holder).binding.btnMinus.setEnabled(false);

                            list.get(position).voted = true;

                            //saving state in sharedprefs
                            MySharedPreferences.getInstance(holder.itemView.getContext()).addIsVoted(""+list.get(position).id);
                        }

                        @Override
                        public void onFailure(Call<VoteResponse> call, Throwable t) {
                            Toast.makeText(holder.itemView.getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            ((MyHolder)holder).binding.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommentVote vote = new CommentVote();
                    vote.setComment(list.get(position).id);
                    vote.setVote(true);

                    service.addVote(vote).enqueue(new Callback<VoteResponse>() {
                        @Override
                        public void onResponse(Call<VoteResponse> call, Response<VoteResponse> response) {
                            Toast.makeText(holder.itemView.getContext(), "Disliked", Toast.LENGTH_SHORT).show();

                            ((MyHolder)holder).binding.btnPlus.setEnabled(false);
                            ((MyHolder)holder).binding.btnMinus.setEnabled(false);

                            list.get(position).voted = true;

                            //saving state in sharedprefs
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

        if(list == null) {
            return 0;
        }
        else {
            if(limit == 0) {
                return list.size();
            }
            else{
                if(list.size() > limit){
                    return limit;
                }
                else {
                    return list.size();
                }
            }
        }
    }

    public void setList(ArrayList<Comment>list)
    {
        this.list = list;
        this.limit = 0;
        notifyDataSetChanged();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private RvCommentItemBinding binding;

        public MyHolder(@NonNull RvCommentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
