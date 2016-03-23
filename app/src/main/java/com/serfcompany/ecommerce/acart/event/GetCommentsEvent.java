package com.serfcompany.ecommerce.acart.event;

import com.serfcompany.ecommerce.acart.model.comment.Comment;

/**
 * Created by serfcompany on 22.03.16.
 */
public class GetCommentsEvent {
    private Comment comment;

    public GetCommentsEvent(Comment comment){
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }
}
