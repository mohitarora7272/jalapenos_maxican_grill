package com.serfcompany.ecommerce.acart.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serfcompany.ecommerce.acart.model.comment.Comment;

/**
 * Created by serfcompany on 22.03.16.
 */
public class CommentsParser {

    public Comment parse(String JSON){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-dd-MM HH:mm:ss");
        Gson gson = gsonBuilder.create();

        Comment comment = gson.fromJson(JSON, Comment.class);
        return comment;
    }
}
