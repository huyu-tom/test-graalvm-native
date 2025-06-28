package com.huyu.testgraalvmlogmaven.factory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

public class ArticlePO {


  private Long id; // bigint NOT NULL AUTO_INCREMENT

  @NotNull // NOT NULL
  @Size(max = 255) // varchar(255)
  private String title; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL

  @NotNull // NOT NULL
  @Size(max = 255) // varchar(255)
  private String summary; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL

  @Size(max = 255) // varchar(255)
  private String keywords; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL

  @NotNull // NOT NULL
  @Size(max = 255) // varchar(255)
  private String cover; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL


  @NotNull // NOT NULL
  private List<Long> categoryIds; // json NOT NULL - 映射为 Long 列表

  @NotNull // NOT NULL
  private String categoryIdsJSON;

  @NotNull // NOT NULL
  private List<Long> tagIds; // json NOT NULL - 映射为 Long 列表

  @NotNull // NOT NULL
  private String tagIdsJSON;

  @NotNull // NOT NULL
  private List<Long> seriesIds; // json NOT NULL - 映射为 Long 列表

  @NotNull // NOT NULL
  private String seriesIdsJSON; // json NOT NULL - 映射为 Long 列表

  @NotNull // NOT NULL
  private Integer readType; // tinyint NOT NULL - 映射为 Integer 或 Byte，Integer 更常用

  @Size(max = 255) // varchar(255)
  private String password; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL

  @NotNull // NOT NULL
  private Boolean topIs; // bit(1) NOT NULL - 映射为 Boolean

  @NotNull // NOT NULL
  private Boolean originalIs; // bit(1) NOT NULL - 映射为 Boolean

  @NotNull // NOT NULL
  private Boolean recommendIs; // bit(1) NOT NULL - 映射为 Boolean

  @NotNull // NOT NULL
  private Boolean appreciationIs; // bit(1) NOT NULL - 映射为 Boolean

  @NotNull // NOT NULL
  private Boolean commentEnabledIs; // bit(1) NOT NULL - 映射为 Boolean

  @NotNull // NOT NULL (注意：根据之前的分析，此字段可能应为 nullable，但按 DDL 严格映射)
  @Size(max = 255) // varchar(255)
  private String originalUrl; // varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL

  @NotNull // NOT NULL
  @Min(0) // 次数不能为负数
  private Integer likes; // int NOT NULL - 映射为 Integer

  @NotNull // NOT NULL
  @Min(0) // 次数不能为负数
  private Integer views; // int NOT NULL - 映射为 Integer

  @NotNull // NOT NULL
  @Min(0) // 热度值通常不为负数
  private Double hotValue; // int NOT NULL - 映射为 Integer

  @NotNull // NOT NULL
  @Min(0) // 字数不能为负数
  private Integer words; // int NOT NULL - 映射为 Integer

  @NotNull // NOT NULL
  @Min(0) // 时长不能为负数
  private Integer readTime; // int NOT NULL - 映射为 Integer

  private Long userId; // bigint NULL DEFAULT NULL - 映射为 Long (注意是 nullable)

  @NotNull // NOT NULL
  private Timestamp createTime; // datetime NOT NULL - 映射为 LocalDateTime (Java 8+)

  @NotNull // NOT NULL
  private Timestamp updateTime; // datetime NOT NULL - 映射为 LocalDateTime (Java 8+)

  @NotNull // NOT NULL
  private Integer publishStatus; // tinyint NOT NULL - 映射为 Integer

  @NotNull // NOT NULL
  private Boolean carouselIs; // bit(1) NOT NULL - 映射为 Boolean

  // 文本
  private String content;


  private String contentMd;

  @NotNull // NOT NULL
  private Integer rootComments;

  @NotNull // NOT NULL
  private Integer totalComments;

  public Long getId() {
    return id;
  }

  public ArticlePO setId(Long id) {
    this.id = id;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public ArticlePO setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public ArticlePO setSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getKeywords() {
    return keywords;
  }

  public ArticlePO setKeywords(String keywords) {
    this.keywords = keywords;
    return this;
  }

  public String getCover() {
    return cover;
  }

  public ArticlePO setCover(String cover) {
    this.cover = cover;
    return this;
  }

  public List<Long> getCategoryIds() {
    return categoryIds;
  }

  public ArticlePO setCategoryIds(List<Long> categoryIds) {
    this.categoryIds = categoryIds;
    return this;
  }

  public List<Long> getTagIds() {
    return tagIds;
  }

  public ArticlePO setTagIds(List<Long> tagIds) {
    this.tagIds = tagIds;
    return this;
  }

  public List<Long> getSeriesIds() {
    return seriesIds;
  }

  public ArticlePO setSeriesIds(List<Long> seriesIds) {
    this.seriesIds = seriesIds;
    return this;
  }

  public Integer getReadType() {
    return readType;
  }

  public ArticlePO setReadType(Integer readType) {
    this.readType = readType;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public ArticlePO setPassword(String password) {
    this.password = password;
    return this;
  }

  public Boolean getTopIs() {
    return topIs;
  }

  public ArticlePO setTopIs(Boolean topIs) {
    this.topIs = topIs;
    return this;
  }

  public Boolean getOriginalIs() {
    return originalIs;
  }

  public ArticlePO setOriginalIs(Boolean originalIs) {
    this.originalIs = originalIs;
    return this;
  }

  public Boolean getRecommendIs() {
    return recommendIs;
  }

  public ArticlePO setRecommendIs(Boolean recommendIs) {
    this.recommendIs = recommendIs;
    return this;
  }

  public Boolean getAppreciationIs() {
    return appreciationIs;
  }

  public ArticlePO setAppreciationIs(Boolean appreciationIs) {
    this.appreciationIs = appreciationIs;
    return this;
  }

  public Boolean getCommentEnabledIs() {
    return commentEnabledIs;
  }

  public ArticlePO setCommentEnabledIs(Boolean commentEnabledIs) {
    this.commentEnabledIs = commentEnabledIs;
    return this;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public ArticlePO setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
    return this;
  }

  public Integer getLikes() {
    return likes;
  }


  public ArticlePO setLikes(Integer likes) {
    this.likes = likes;
    return this;
  }

  public Integer getViews() {
    return views;
  }

  public ArticlePO setViews(Integer views) {
    this.views = views;
    return this;
  }

  public Double getHotValue() {
    return hotValue;
  }

  public ArticlePO setHotValue(Double hotValue) {
    this.hotValue = hotValue;
    return this;
  }

  public Integer getWords() {
    return words;
  }

  public ArticlePO setWords(Integer words) {
    this.words = words;
    return this;
  }

  public Integer getReadTime() {
    return readTime;
  }

  public ArticlePO setReadTime(Integer readTime) {
    this.readTime = readTime;
    return this;
  }

  public Long getUserId() {
    return userId;
  }

  public ArticlePO setUserId(Long userId) {
    this.userId = userId;
    return this;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public ArticlePO setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
    return this;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public ArticlePO setUpdateTime(Timestamp updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public Integer getPublishStatus() {
    return publishStatus;
  }

  public ArticlePO setPublishStatus(Integer publishStatus) {
    this.publishStatus = publishStatus;
    return this;
  }

  public Boolean getCarouselIs() {
    return carouselIs;
  }

  public ArticlePO setCarouselIs(Boolean carouselIs) {
    this.carouselIs = carouselIs;
    return this;
  }

  public String getContent() {
    return content;
  }

  public ArticlePO setContent(String content) {
    this.content = content;
    return this;
  }

  public String getCategoryIdsJSON() {
    return categoryIdsJSON;
  }

  public ArticlePO setCategoryIdsJSON(String categoryIdsJSON) {
    this.categoryIdsJSON = categoryIdsJSON;
    return this;
  }

  public String getTagIdsJSON() {
    return tagIdsJSON;
  }

  public ArticlePO setTagIdsJSON(String tagIdsJSON) {
    this.tagIdsJSON = tagIdsJSON;
    return this;
  }

  public String getSeriesIdsJSON() {
    return seriesIdsJSON;
  }

  public ArticlePO setSeriesIdsJSON(String seriesIdsJSON) {
    this.seriesIdsJSON = seriesIdsJSON;
    return this;
  }

  public String getContentMd() {
    return contentMd;
  }

  public ArticlePO setContentMd(String contentMd) {
    this.contentMd = contentMd;
    return this;
  }

  public Integer getRootComments() {
    return rootComments;
  }

  public ArticlePO setRootComments(Integer rootComments) {
    this.rootComments = rootComments;
    return this;
  }

  public Integer getTotalComments() {
    return totalComments;
  }

  public ArticlePO setTotalComments(Integer totalComments) {
    this.totalComments = totalComments;
    return this;
  }
}
