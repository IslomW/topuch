package com.sharipov.topuch.entity;



public class Image   {

    Long imageId;
    Integer  ts;
    Long  postId;
    String imageAddress;




//    CREATE TABLE Images  (
//            id SERIAL PRIMARY KEY,
//            ts INTEGER,
//            post_id INTEGER REFERENCES Posts(id),
//            image_address TEXT
//);
}
