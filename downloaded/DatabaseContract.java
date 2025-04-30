package com.developer.sparsh.baseapplication.Helpers;

import android.net.Uri;

/**
 * Created by utkarshnath on 04/01/17.
 */

public class DatabaseContract {
    // Content Uri for Content Provider
    public static final String CONTENT_AUTHORITY = "com.developer.sparsh.baseapplication.Helpers";

    // Base Content Uri using the Content Authority
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INVITEE_TABLE = "inviteetable";
    public static final String PATH_POST_TABLE = "friends_table";
    public static final String PATH_COMMENTS_TABLE = "commentstable";
    public static final String PATH_LIKES_TABLE = "linkestble";
    public static final String PATH_COMMENTS_LIKES_TABLE = "commentslikestable";
    public static final String PATH_COMMENTS_REPLY_TABLE = "commentsreplytable";


    public static final Uri INVITEE_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_INVITEE_TABLE).build();
    public static final String INVITEE_CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_INVITEE_TABLE;
    public static final String INVITEE_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_INVITEE_TABLE;
    public static final String INVITEE_TABLE_NAME = "INVITEETABLE";


    public static final Uri POST_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POST_TABLE).build();
    public static final String POST_CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_POST_TABLE;
    public static final String POST_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_POST_TABLE;
    public static final String POST_TABLE_NAME = "POSTTABLE";

    public static final Uri COMMENT_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENTS_TABLE).build();
    public static final String COMMENT_CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS_TABLE;
    public static final String COMMENT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS_TABLE;
    public static final String COMMENT_TABLE_NAME = "COMMENTTABLE";

    public static final Uri LIKES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LIKES_TABLE).build();
    public static final String LIKES_CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_LIKES_TABLE;
    public static final String LIKES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_LIKES_TABLE;
    public static final String LIKES_TABLE_NAME = "LIKESTABLE";

    public static final Uri COMMENTS_LIKES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POST_TABLE).build();
    public static final String COMMENTS_LIKES_CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS_LIKES_TABLE;
    public static final String COMMENTS_LIKES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS_LIKES_TABLE;
    public static final String COMMENTS_LIKES_TABLE_NAME = "COMMENTSLIKESTABLE";

    public static final Uri COMMENTS_REPLIES_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENTS_REPLY_TABLE).build();
    public static final String COMMENTS_REPLIES_CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS_REPLY_TABLE;
    public static final String COMMENTS_REPLIES_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_COMMENTS_REPLY_TABLE;
    public static final String COMMENTS_REPLIES_TABLE_NAME = "COMMENTSREPLIESTABLE";
}
