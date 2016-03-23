package com.serfcompany.ecommerce.acart.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.serfcompany.ecommerce.acart.HTTPHolders.GetCommentByPostID;
import com.serfcompany.ecommerce.acart.event.GetCommentsEvent;
import com.serfcompany.ecommerce.acart.model.comment.Comment;
import com.serfcompany.ecommerce.acart.parser.CommentsParser;

import java.io.IOException;

/**
 * Created by serfcompany on 22.03.16.
 */
public class CommentsActivityPresenter extends AbstractPresenter{

    private Context context;
    private Comment comment;
    private GetCommentsEvent getCommentsEvent;

    private CommentsActivityPresenter(Context context){this.context = context;}

    public void loadComments(final String id, final String parentID, final String username, final String password){
        if (isNetworkAvailable(context)){
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    GetCommentByPostID con = new GetCommentByPostID();
                    CommentsParser parser = new CommentsParser();
                    try {
                        setComment(parser.parse(con.getComments(id, parentID, username, password)));
                    } catch (Exception e){
                        e.printStackTrace();
                        setComment(new Comment());
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    getCommentsEvent = new GetCommentsEvent(getComment());

                }
            }.execute();
        }
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
