package com.dxg.msns.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "dynamics",type = "docs",shards = 1,replicas = 0)
public class Dynamics {
    @Id
    private Integer id;  //动态id
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String all;  //所有的查询条件
    private String typeId; //分类id
    private Date publishDate; //发布时间
    private Date recentCommentDate; //最新评论时间
    @Field(type = FieldType.Keyword,index =  false)
    private String dynamics; //List<Dynamic>的json结构
}
