package com.example.finalproject.Adapters.NewsDetailItems;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.finalproject.Adapters.NewsDetailsAdapter;
import com.example.finalproject.Model.Comment;
import com.example.finalproject.Response.CommentsResponse;
import com.example.finalproject.Retrofit.RetrofitClient;
import com.example.finalproject.Retrofit.RetrofitService;
import com.example.finalproject.databinding.RvItemDetailCommentBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvItemDetailComment implements DetailsAdapterItem {

    private ArrayList<Comment> items;
    private int commentCount, newsId;


    private RvCommentAdapter adapter;
    private boolean isExpanded = false;

    public RvItemDetailComment(ArrayList<Comment> items, int commentCount, int newsId) {
        this.items = items;
        this.commentCount = commentCount;
        this.newsId = newsId;
        adapter = new RvCommentAdapter(items, 4);
    }

    @Override
    public int getType() {
        return 5;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void bind(NewsDetailsAdapter.MyHolder holder) {

        RvItemDetailCommentBinding binding = (RvItemDetailCommentBinding) holder.binding;

        LinearLayoutManager manager = new LinearLayoutManager(holder.itemView.getContext());
        binding.rv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(
                binding.rv.getContext(), manager.getOrientation());

        binding.rv.setAdapter(adapter);
        binding.rv.addItemDecoration(decoration);

        binding.tv.setText("" + commentCount);


        binding.llHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpanded) {
                    binding.typeCommentLayout.setVisibility(View.VISIBLE);
                    isExpanded = true;

                    binding.tvSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(binding.etName.getText().length() > 2 && binding.etComment.getText().length() > 0) {
                                leaveComment(binding, holder.getAdapterPosition());
                            }
                            else{
                                Toast.makeText(holder.itemView.getContext(), "Polja ne smeju biti prazna!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    binding.typeCommentLayout.setVisibility(View.GONE);
                    isExpanded = false;
                }
            }
        });

        binding.llFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setList(items);
            }
        });
    }

    private void leaveComment(RvItemDetailCommentBinding binding, int position) {

        RetrofitService service = RetrofitClient.getInstance().getRetrofit().create(RetrofitService.class);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        Comment comment = new Comment();
        comment.negative_votes = 0;
        comment.positive_votes = 0;
        comment.parent_comment = 0;
        comment.children = null;
        comment.news = newsId;
        comment.content = binding.etComment.getText().toString();
        comment.name = binding.etName.getText().toString();
        comment.created_at = date + "|" + time;

        service.addComment(comment).enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                Toast.makeText(binding.getRoot().getContext(), "Komentarisano!", Toast.LENGTH_SHORT).show();

                binding.typeCommentLayout.setVisibility(View.GONE);
                isExpanded = false;
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                Toast.makeText(binding.getRoot().getContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                binding.typeCommentLayout.setVisibility(View.GONE);
                isExpanded = false;
            }
        });
    }
}
